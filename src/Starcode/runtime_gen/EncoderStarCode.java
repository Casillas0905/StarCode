package Starcode.runtime_gen;

import Starcode.core.IVisitor;
import Starcode.parser.ast.*;
import TAM.Machine;

/**
 * The EncoderStarCode class is responsible for traversing the abstract syntax tree (AST)
 * and encoding the program into TAM (Triangle Abstract Machine) instructions.
 * It implements the Visitor pattern to process each node of the AST.
 */
public class EncoderStarCode implements IVisitor {

    private TamEncoder encoder;  // The TAM encoder for generating instructions
    private int currentLevel;   // Tracks the current scope or nesting level

    /**
     * Constructor initializes the encoder.
     */
    public EncoderStarCode() {
        encoder = new TamEncoder();
    }

    /**
     * Saves the encoded program to a file.
     *
     * @param fileName The name of the file to save the program to.
     */
    public void saveToFile(String fileName) {
        encoder.saveProgramToFile(fileName);
    }

    @Override
    public Object visitProgram(Program program, Object arg) {
        currentLevel = 0; // Set the current level to the global scope
        program.block.visit(this, new Address()); // Visit the program's main block
        return null;
    }

    @Override
    public Object visitProgramBlock(ProgramBlock programBlock, Object arg) {
        int beforeAddress = encoder.nextInstructionAddress;

        // Add a jump instruction to skip over declarations
        encoder.setInstruction(Machine.JUMPop, 0, Machine.CodeBase, 0);

        // Visit declarations and calculate the memory size required
        int size = ((Integer) programBlock.declarations.visit(this, arg)).intValue();

        // Update the jump address to point after the declarations
        encoder.setInstructionOperand(beforeAddress, encoder.nextInstructionAddress);

        // Allocate memory for the declarations
        if (size > 0) {
            encoder.setInstruction(Machine.PUSHop, 0, 0, size);
        }

        // Visit the main statement block
        programBlock.statement.visit(this, null);
        return null;
    }

    @Override
    public Object visitDeclarations(Declarations declarations, Object arg) {
        int startDisplacement = ((Address) arg).displacement;

        // Visit each declaration and update the displacement
        for (OneDeclaration declaration : declarations.declarations) {
            arg = declaration.visit(this, arg);
        }

        Address address = (Address) arg;
        int size = address.displacement - startDisplacement; // Calculate the size of declarations

        return Integer.valueOf(size);
    }

    @Override
    public Object visitStarDeclaration(StarDeclaration starDeclaration, Object arg) {
        // Assign an address to the declaration
        starDeclaration.address = (Address) arg;
        starDeclaration.identifier.visit(this, starDeclaration.address);

        // Determine the increment for memory allocation
        int increment = !starDeclaration.isArray
                ? 1
                : starDeclaration.arrayLenght;

        return new Address((Address) arg, increment); // Return the updated address
    }

    @Override
    public Object visitCometDeclaration(CometDeclaration cometDeclaration, Object arg) {
        // Assign an address to the declaration
        cometDeclaration.address = (Address) arg;
        cometDeclaration.identifier.visit(this, cometDeclaration.address);

        // Determine the increment for memory allocation
        int increment = !cometDeclaration.isArray
                ? 1
                : cometDeclaration.arrayLenght;

        return new Address((Address) arg, increment); // Return the updated address
    }

    @Override
    public Object visitSupernovaDeclaration(SupernovaDeclaration supernovaDeclaration, Object arg) {
        // Assign an address for the function
        supernovaDeclaration.address = new Address(currentLevel, encoder.nextInstructionAddress);
        supernovaDeclaration.identifier.visit(this, supernovaDeclaration.address);

        ++currentLevel; // Increment the current level for the function scope

        Address address = new Address((Address) arg);

        // Visit the type list and calculate its size
        int size = ((Integer) supernovaDeclaration.typeList.visit(this, address)).intValue();

        // Visit the parameter list and function body
        supernovaDeclaration.idList.visit(this, new Address(address, -size));
        supernovaDeclaration.supernovaBlock.visit(this, new Address(address, Machine.linkDataSize));

        // Visit the return statement with the size of local variables
        supernovaDeclaration.supernovaBlock.returnStatement.visit(this, size);

        --currentLevel; // Decrement the current level after exiting the function scope

        return null;
    }

    @Override
    public Object visitStatements(Statements statements, Object arg) {
        // Visit each statement in the list and process it
        for (OneStatement statement : statements.statements) {
            statement.visit(this, null);
        }
        return null;
    }

    @Override
    public Object visitExpressionStatement(ExpressionStatement expressionStatement, Object arg) {
        // Visit the expression in the statement
        expressionStatement.expression.visit(this, null);
        return null;
    }

    @Override
    public Object visitEclipseStatement(EclipseStatement eclipseStatement, Object arg) {
        // Visit the condition expression
        eclipseStatement.expression.visit(this, arg);

        // Add a conditional jump instruction, leaving the target address placeholder
        int falseJump = encoder.nextInstructionAddress;
        encoder.setInstruction(Machine.JUMPIFop, 0, Machine.CodeBase, 0);

        // Visit the block that executes if the condition is true
        eclipseStatement.block.visit(this, null);

        // Update the placeholder with the address to jump to if the condition is false
        encoder.setInstructionOperand(falseJump, encoder.nextInstructionAddress);

        return null;
    }

    @Override
    public Object visitOrbitStatement(OrbitStatement orbitStatement, Object arg) {
        // Visit the incremental identifier (loop variable)
        orbitStatement.incrementalIdentifier.visit(this, arg);

        // Initialize the loop variable with a starting value of 0
        encoder.setInstruction(Machine.LOADLop, 0, 0, 0);

        // Store the start address of the loop
        int startAddress = encoder.nextInstructionAddress;

        // Visit the count identifier and load its value
        orbitStatement.countIdentifier.visit(this, arg);

        // Add a comparison instruction to check the loop condition
        encoder.setInstruction(Machine.CALLop, 0, 0, 0);

        // Add a conditional jump instruction to exit the loop if the condition is false
        int endJump = encoder.nextInstructionAddress;
        encoder.setInstruction(Machine.JUMPIFop, 0, Machine.CBr, 0);

        // Visit the block to execute within the loop
        orbitStatement.block.visit(this, arg);

        // Add a jump instruction to return to the start of the loop
        encoder.setInstruction(Machine.JUMPop, 0, Machine.CBr, startAddress);

        // Update the placeholder with the address to jump to after the loop
        encoder.setInstructionOperand(endJump, encoder.nextInstructionAddress);
        return null;
    }

    @Override
    public Object visitBlock(Block block, Object arg) {
        // Visit all statements in the block
        block.statements.visit(this, null);
        return null;
    }

    @Override
    public Object visitCometLiteral(CometLiteral cometLiteral, Object arg) {
        // Parse the comet literal value and load it as a constant
        int parsedLiteral = Integer.parseInt(cometLiteral.spelling);
        encoder.setInstruction(Machine.LOADLop, 1, 0, parsedLiteral);
        return null;
    }

    @Override
    public Object visitArrayAccess(ArrayAccess arrayAccess, Object arg) {
        // Calculate the displacement using the array index (comet literal)
        int displacement = Integer.parseInt(arrayAccess.cometLiteral.spelling);

        // Visit the identifier and adjust its address using the displacement
        arrayAccess.identifier.visit(this, new Address(arrayAccess.identifier.address, displacement));
        return null;
    }

    @Override
    public Object visitExpression(Expression expression, Object arg) {
        // Visit the primary expression (first operand)
        expression.primary.visit(this, arg);

        // Process operators and additional operands in the expression
        for (int i = 0; i < expression.operators.size(); i++) {
            // Visit the next operand
            expression.primaries.get(i).visit(this, arg);

            // Get the current operator
            Operator operator = expression.operators.get(i);

            // Handle assignment operator '='
            if (operator.spelling.equals("=")) {
                Address adr = (Address) expression.primary.visit(this, null);
                if (adr != null) {
                    expression.primaries.get(i).visit(this, null);

                    int register = encoder.getRegisterByDepth(currentLevel, adr.level);
                    encoder.setInstruction(Machine.STOREop, 1, register, adr.displacement);
                }
            } else {
                // For other operators, generate a call to the operator's function
                encoder.setInstruction(Machine.CALLop, 0, Machine.PBr, operator.getOpCode());
            }
        }
        return null;
    }

    @Override
    public Object visitIdentifier(Identifier identifier, Object arg) {
        // Get the appropriate register based on the identifier's scope level
        int register = encoder.getRegisterByDepth(currentLevel, identifier.address.level);

        // Generate a LOAD instruction for the identifier
        encoder.setInstruction(Machine.LOADop, 1, register, ((Address)arg).displacement);
        return null;
    }

    @Override
    public Object visitIdList(IdList idList, Object arg) {
        // Visit each identifier in the list
        for (Identifier identifier : idList.identifiers) {
            identifier.visit(this, null);
        }
        return null;
    }

    @Override
    public Object visitPrimary(Primary primary, Object arg) {
        // Determine the type of the primary and visit the appropriate component
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
        // Visit the return value (identifier)
        returnStatement.identifier.visit(this, arg);

        // Generate a RETURN instruction with the size of local variables
        int size = (int)arg;
        encoder.setInstruction(Machine.RETURNop, 1, 0, size);
        return null;
    }

    @Override
    public Object visitStarLiteral(StarLiteral starLiteral, Object arg) {
        // Load the characters of the star literal onto the stack in reverse order
        for (int i = starLiteral.spelling.length() - 1; i >= 0; i--) {
            encoder.setInstruction(Machine.LOADLop, 1, 0, starLiteral.spelling.toCharArray()[i]);
        }
        return null;
    }

    @Override
    public Object visitSupernovaBlock(SupernovaBlock supernovaBlock, Object arg) {
        // Visit the statements in the block
        supernovaBlock.statements.visit(this, null);

        // Visit the return statement in the block
        supernovaBlock.returnStatement.visit(this, null);
        return null;
    }


    @Override
    public Object visitSupernovaStatement(SupernovaStatement supernovaStatement, Object arg) {
        // Visit the identifier of the method or function being called
        supernovaStatement.identifier.visit(this, null);

        // Visit each parameter and push their values onto the stack
        for (Primary primary : supernovaStatement.parameters) {
            primary.visit(this, null);
        }

        // Generate a CALL instruction using the function's address
        encoder.setInstruction(Machine.CALLop, 0, Machine.PBr, supernovaStatement.identifier.address.displacement);

        return null;
    }

    @Override
    public Object visitTypeList(TypeList typeList, Object arg) {
        int size = 0;

        // Calculate the total memory size required for the types in the list
        for (ReturnType returnType : typeList.returnTypes) {
            // If the type is an array, add address size; otherwise, add integer size
            size += returnType.isArray ? Machine.addressSize : Machine.integerSize;
        }

        // Return the calculated size
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
