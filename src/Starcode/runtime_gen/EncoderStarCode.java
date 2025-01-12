package Starcode.runtime_gen;

import Starcode.core.IVisitor;
import Starcode.parser.ast.*;
import TAM.Machine;

public class EncoderStarCode implements IVisitor {
    private TamEncoder encoder;
    private int currentLevel;

    public EncoderStarCode()
    {
        encoder = new TamEncoder();
    }

    public void saveToFile(String fileName)
    {
        encoder.saveProgramToFile(fileName);
    }

    @Override
    public Object visitProgram(Program program, Object arg) {
        currentLevel = 0;
        program.block.visit(this, new Address());
        return null;
    }

    @Override
    public Object visitProgramBlock(ProgramBlock programBlock, Object arg) {
        int beforeAddress = encoder.nextInstructionAddress;
        encoder.setInstruction(Machine.JUMPop, 0, Machine.CodeBase, 0);

        int size = ((Integer) programBlock.declarations.visit(this, arg)).intValue();

        encoder.setInstructionOperand(beforeAddress, encoder.nextInstructionAddress);

        // Allocating enough memory for the declarations
        if(size > 0)
        {
            encoder.setInstruction(Machine.PUSHop, 0, 0, size);
        }

        programBlock.statement.visit(this, null);
        return null;
    }


    @Override
    public Object visitDeclarations(Declarations declarations, Object arg) {
        int startDisplacement = ((Address) arg).displacement;

        for(OneDeclaration declaration : declarations.declarations)
        {
            arg = declaration.visit(this, arg);
        }

        Address address = (Address) arg;
        int size = address.displacement - startDisplacement;

        return Integer.valueOf(size);
    }

    @Override
    public Object visitStarDeclaration(StarDeclaration starDeclaration, Object arg) {
        starDeclaration.address = (Address) arg;
        starDeclaration.identifier.visit(this, starDeclaration.address);

        int increment = !starDeclaration.isArray
                ? 1
                : starDeclaration.arrayLenght;

        return new Address((Address) arg, increment);
    }


    @Override
    public Object visitCometDeclaration(CometDeclaration cometDeclaration, Object arg) {
        cometDeclaration.address = ((Address) arg);
        cometDeclaration.identifier.visit(this, cometDeclaration.address);

        int increment = !cometDeclaration.isArray
                ? 1
                : cometDeclaration.arrayLenght;

        return new Address((Address) arg, increment);
    }

    @Override
    public Object visitSupernovaDeclaration(SupernovaDeclaration supernovaDeclaration, Object arg) {
        supernovaDeclaration.address = new Address(currentLevel, encoder.nextInstructionAddress);
        supernovaDeclaration.identifier.visit(this, supernovaDeclaration.address);

        ++currentLevel;

        Address address = new Address((Address) arg);

        int size = ((Integer) supernovaDeclaration.typeList.visit(this, address)).intValue();
        supernovaDeclaration.idList.visit(this, new Address(address, -size));
        supernovaDeclaration.supernovaBlock.visit(this, new Address(address, Machine.linkDataSize));
        supernovaDeclaration.supernovaBlock.returnStatement.visit(this, size);

        currentLevel--;

        return null;
    }

    @Override
    public Object visitStatements(Statements statements, Object arg)
    {
        for(OneStatement statement : statements.statements)
        {
            statement.visit(this, null);
        }
        return null;
    }

    @Override
    public Object visitExpressionStatement(ExpressionStatement expressionStatement, Object arg) {
        expressionStatement.expression.visit(this, null);
        return null;
    }

    @Override
    public Object visitEclipseStatement(EclipseStatement eclipseStatement, Object arg) {
        eclipseStatement.expression.visit(this, arg);

        int falseJump = encoder.nextInstructionAddress;
        encoder.setInstruction(Machine.JUMPIFop, 0, Machine.CodeBase, 0);

        eclipseStatement.block.visit(this, null);

        encoder.setInstructionOperand(falseJump, encoder.nextInstructionAddress);

        return null;
    }

    @Override
    public Object visitOrbitStatement(OrbitStatement orbitStatement, Object arg) {
        orbitStatement.incrementalIdentifier.visit(this, arg);
        encoder.setInstruction(Machine.LOADLop, 0, 0, 0);

        int startAddress = encoder.nextInstructionAddress;

        orbitStatement.countIdentifier.visit(this, arg);
        encoder.setInstruction(Machine.CALLop, 0, 0, 0);

        int endJump = encoder.nextInstructionAddress;
        encoder.setInstruction(Machine.JUMPIFop, 0, Machine.CBr, 0);

        orbitStatement.block.visit(this, arg);

        encoder.setInstruction(Machine.JUMPop, 0, Machine.CBr, startAddress);
        encoder.setInstructionOperand(endJump, encoder.nextInstructionAddress);
        return null;
    }

    @Override
    public Object visitBlock(Block block, Object arg) {
        block.statements.visit(this, null);
        return null;
    }

    @Override
    public Object visitCometLiteral(CometLiteral cometLiteral, Object arg) {
        int parsedLiteral = Integer.parseInt(cometLiteral.spelling);
        encoder.setInstruction(Machine.LOADLop, 1, 0, parsedLiteral);
        return null;
    }


    @Override
    public Object visitArrayAccess(ArrayAccess arrayAccess, Object arg) {
        int displacement = Integer.parseInt(arrayAccess.cometLiteral.spelling);
        arrayAccess.identifier.visit(this, new Address(arrayAccess.identifier.address, displacement));
        return null;
    }

    @Override
    public Object visitExpression(Expression expression, Object arg) {
        expression.primary.visit(this, arg);

        for (int i = 0; i < expression.operators.size(); i++) {
            expression.primaries.get(i).visit(this, arg);
            Operator operator = expression.operators.get(i);

            if (operator.spelling.equals("=")) {
                Address adr = (Address) expression.primary.visit(this, null);
                if(adr != null)
                {
                    expression.primaries.get(i).visit(this, null);

                    int register = encoder.getRegisterByDepth(currentLevel, adr.level);
                    encoder.setInstruction(Machine.STOREop, 1, register, adr.displacement);
                }
            } else {
                encoder.setInstruction(Machine.CALLop, 0, Machine.PBr, operator.getOpCode());
            }
        }
        return null;
    }

    @Override
    public Object visitIdentifier(Identifier identifier, Object arg) {
        int register = encoder.getRegisterByDepth(currentLevel, identifier.address.level);
        encoder.setInstruction(Machine.LOADop, 1, register, ((Address)arg).displacement);
        return null;
    }

    @Override
    public Object visitIdList(IdList idList, Object arg) {
        for(Identifier identifier : idList.identifiers)
        {
            identifier.visit(this, null);
        }
        return null;
    }

    @Override
    public Object visitPrimary(Primary primary, Object arg) {
        if (primary.identifier != null) {
            primary.identifier.visit(this, arg);
            return primary.identifier.address;
        } else if (primary.cometLiteral != null) {
            primary.cometLiteral.visit(this, arg);
        } else if (primary.starLiteral != null) {
            primary.starLiteral.visit(this, arg);
        } else if (primary.arrayAccess != null) {
            primary.arrayAccess.visit(this, arg);
            int displacement = Integer.parseInt(primary.cometLiteral.spelling);
            return new Address(primary.arrayAccess.identifier.address, displacement);
        }
        return null;
    }


    @Override
    public Object visitReturnStatement(ReturnStatement returnStatement, Object arg) {
        returnStatement.identifier.visit(this, arg);
        int size = (int)arg;
        encoder.setInstruction(Machine.RETURNop, 1, 0, size);
        return null;
    }


    @Override
    public Object visitStarLiteral(StarLiteral starLiteral, Object arg) {
        for(int i = starLiteral.spelling.length() - 1; i >= 0; i--)
        {
            encoder.setInstruction(Machine.LOADLop, 1, 0, starLiteral.spelling.toCharArray()[i]);
        }
        return null;
    }

    @Override
    public Object visitSupernovaBlock(SupernovaBlock supernovaBlock, Object arg) {
        supernovaBlock.statements.visit(this, null);
        supernovaBlock.returnStatement.visit(this, null);
        return null;
    }


    @Override
    public Object visitSupernovaStatement(SupernovaStatement supernovaStatement, Object arg) {
        supernovaStatement.identifier.visit(this, null);
        for(Primary primary : supernovaStatement.parameters){
            primary.visit(this, null);
        }

        return null;
    }

    @Override
    public Object visitTypeList(TypeList typeList, Object arg) {
        int size = 0;
        for (ReturnType returnType : typeList.returnTypes) {
            size += returnType.isArray ? Machine.addressSize : Machine.integerSize;
        }
        return size;
    }

    @Override
    public Object visitReturnType(ReturnType returnType, Object arg) {
        return null;
    }

    @Override
    public Object visitOperator(Operator operator, Object arg) {
        return null;
    }
}
