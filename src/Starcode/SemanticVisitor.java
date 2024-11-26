package Starcode;

import Starcode.ast.*;

import java.util.Vector;

public class SemanticVisitor implements IVisitor
{
    private DeclarationsTable declarationsTable = new DeclarationsTable();
    private InitializationTable initializationTable = new InitializationTable();

    @Override
    public Object visitProgram(Program program, Object arg) {
        program.block.visit(this, null);
        return null;
    }

    @Override
    public Object visitProgramBlock(ProgramBlock programBlock, Object arg) {
        programBlock.declarations.visit(this, null);
        programBlock.statement.visit(this, null);
        return null;
    }

    @Override
    public Object visitDeclarations(Declarations declarations, Object arg) {
        for(OneDeclaration declaration : declarations.declarations)
        {
            declaration.visit(this, null);
        }

        return null;
    }

    @Override
    public Object visitStarDeclaration(StarDeclaration starDeclaration, Object arg) {
        String id = (String) starDeclaration.identifier.visit(this, null);
        declarationsTable.addDeclaration(id, starDeclaration);
        return null;
    }

    @Override
    public Object visitCometDeclaration(CometDeclaration cometDeclaration, Object arg) {
        String id = (String) cometDeclaration.identifier.visit(this, null);
        declarationsTable.addDeclaration(id, cometDeclaration);

        return null;
    }

    @Override
    public Object visitSupernovaDeclaration(SupernovaDeclaration supernovaDeclaration, Object arg) {
        supernovaDeclaration.returnType.visit(null, this);

        String id = (String) supernovaDeclaration.identifier.visit(this, null);
        declarationsTable.addDeclaration(id, supernovaDeclaration);

        supernovaDeclaration.idList.visit(this, null);
        supernovaDeclaration.typeList.visit(this, supernovaDeclaration.idList.identifiers);

        supernovaDeclaration.supernovaBlock.visit(this, supernovaDeclaration.returnType);
        return null;
    }

    @Override
    public Object visitBlock(Block block, Object arg) {
        block.statements.visit(this, null);
        return null;
    }

    @Override
    public Object visitArrayAccess(ArrayAccess arrayAccess, Object arg) {
        arrayAccess.identifier.visit(this, null);
        return null;
    }

    @Override
    public Object visitCometLiteral(CometLiteral cometLiteral, Object arg) {
        return cometLiteral.spelling;
    }

    @Override
    public Object visitEclipseStatement(EclipseStatement eclipseStatement, Object arg) {
        for(Operator operator : eclipseStatement.expression.operators)
        {
            if(operator.spelling.equals("="))
            {
                System.out.println("Value assignment operations are not allowed in eclipse statements");
            }
        }

        eclipseStatement.expression.visit(this, null);
        eclipseStatement.block.visit(this, null);
        return null;
    }

    @Override
    public Object visitExpression(Expression expression, Object arg) {
        if(!expression.operators.isEmpty())
        {
            if(expression.operators.get(0).spelling.equals("="))
            {
                //TODO Implement:
                /*
                * A variable can only be assigned a value that matches its corresponding literal type or the declared and initialized ReturnType.
                * Array -> CometLiteral
                * ArrayAccess ->
                * */
                initializationTable.addInitialization(expression.primary.identifier.spelling, null);
            }
        }

        expression.primary.visit(this, null);

        for(Primary primary : expression.primaries)
        {
            primary.visit(this, null);
        }

        for(Operator operator : expression.operators)
        {
            operator.visit(this, null);
        }

        return null;
    }

    @Override
    public Object visitExpressionStatement(ExpressionStatement expressionStatement, Object arg)
    {
        expressionStatement.expression.visit(this, null);
        return null;
    }

    @Override
    public Object visitIdentifier(Identifier identifier, Object arg) {
        return identifier.spelling;
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
    public Object visitOperator(Operator operator, Object arg) {
        return operator.spelling;
    }

    @Override
    public Object visitOrbitStatement(OrbitStatement orbitStatement, Object arg) {
        String countId = (String)orbitStatement.countIdentifier.visit(this, null);
        String incrementalId = (String)orbitStatement.incrementalIdentifier.visit(this, null);

        OneDeclaration countIdDeclaration = declarationsTable.getDeclaration(countId);
        OneDeclaration incrementalIdDeclaration = declarationsTable.getDeclaration(incrementalId);

        InitializedId countIdInitialization = initializationTable.getInitializedId(countId);
        InitializedId incrementalIdInitialization = initializationTable.getInitializedId(countId);

        if(!(countIdDeclaration instanceof CometDeclaration))
        {
            System.out.println("Count identifier in the orbit statement must be of type Comet");
        }

        if(!(incrementalIdDeclaration instanceof CometDeclaration))
        {
            System.out.println("Incremental identifier in the orbit statement must be of type Comet");
        }

        if(countIdDeclaration == null)
        {
            System.out.println("Missing declaration statement for identifier with id: " + countId);
        }

        if(countIdInitialization == null)
        {
            System.out.println("Missing initialization statement for identifier with id: " + countId);
        }

        if(incrementalIdDeclaration == null)
        {
            System.out.println("Missing declaration statement for identifier with id: " + incrementalId);
        }

        if(incrementalIdInitialization == null)
        {
            System.out.println("Missing initialization statement for identifier with id: " + incrementalId);
        }

        orbitStatement.block.visit(this, null);
        return null;
    }

    @Override
    public Object visitPrimary(Primary primary, Object arg)
    {
        if(primary.identifier != null)
        {
            String id = (String) primary.identifier.visit(this, null);
            if(declarationsTable.getDeclaration(id) == null)
            {
                System.out.println("Missing declaration statement for primary with id: " + id);
            }

            if(initializationTable.getInitializedId(id) == null)
            {
                System.out.println("Missing initialization statement for primary with id: " + id);
            }
        }

        if(primary.arrayAccess.identifier != null)
        {
            String id = (String) primary.arrayAccess.identifier.visit(this, null);
            if(declarationsTable.getDeclaration(id) == null)
            {
                System.out.println("Missing declaration statement for primary with id: " + id);
            }

            if(initializationTable.getInitializedId(id) == null)
            {
                System.out.println("Missing initialization statement for primary with id: " + id);
            }
        }

        if(primary.starLiteral != null)
        {
            primary.starLiteral.visit(this, null);
        }

        if(primary.cometLiteral != null)
        {
            primary.cometLiteral.visit(this, null);
        }

        return null;
    }

    @Override
    public Object visitReturnStatement(ReturnStatement returnStatement, Object arg)
    {
        ReturnType originalReturnType = (ReturnType) arg;
        String id = (String)returnStatement.identifier.visit(this, null);
        OneDeclaration declaredIdentifier = declarationsTable.getDeclaration(id);

        if(declaredIdentifier == null)
        {
            System.out.println("Missing declaration statement for primary with id: " + id);
        }

        if(initializationTable.getInitializedId(id) == null)
        {
            System.out.println("Missing initialization statement for primary with id: " + id);
        }

        switch (originalReturnType.type)
        {
            case "star":
                if(!(declaredIdentifier instanceof StarDeclaration) && (originalReturnType.isArray != ((StarDeclaration)declaredIdentifier).isArray)){
                    System.out.println("Returned variable with id: " + id + " does not match the declared return type of the supernova subroutine");
                }
                break;
            case "comet":
                if(!(declaredIdentifier instanceof CometDeclaration) && (originalReturnType.isArray != ((CometDeclaration)declaredIdentifier).isArray)){
                    System.out.println("Returned variable with id: " + id + " does not match the declared return type of the supernova subroutine");
                }
                break;
            default:
                System.out.println("Unrecognized type in a return statement");
        }

        return null;
    }

    @Override
    public Object visitReturnType(ReturnType returnType, Object arg) {
        return returnType.type;
    }

    @Override
    public Object visitStarLiteral(StarLiteral starLiteral, Object arg) {
        return starLiteral.spelling;
    }

    @Override
    public Object visitStatements(Statements statements, Object arg) {
        for(OneStatement statement : statements.statements)
        {
            statement.visit(this, null);
        }
        return null;
    }

    @Override
    public Object visitSupernovaBlock(SupernovaBlock supernovaBlock, Object arg) {
        supernovaBlock.statements.visit(this, null);
        supernovaBlock.returnStatement.visit(this, arg);
        return null;
    }

    @Override
    public Object visitSupernovaStatement(SupernovaStatement supernovaStatement, Object arg) {
        String id = (String) supernovaStatement.identifier.visit(this, null);
        OneDeclaration supernovaDeclaration = declarationsTable.getDeclaration(id);

        for(Primary primary : supernovaStatement.parameters)
        {
            primary.visit(this, null);
        }

        if(supernovaDeclaration == null)
        {
            System.out.println("Missing declaration statement for supernova statement with id: " + id);
        }
        else
        {
            int amountOfParameters = supernovaStatement.parameters.size();
            int amountOfDeclaredParameters = ((SupernovaDeclaration)supernovaDeclaration).typeList.returnTypes.size();

            if(amountOfDeclaredParameters != amountOfParameters)
            {
                System.out.println("The number of parameters passed to the supernova does not match the parameters declared in its definition. Supernova ID:" + id);
            }
        }
        return null;
    }

    @Override
    public Object visitTypeList(TypeList typeList, Object arg) {
        Vector<Identifier> identifiers = (Vector<Identifier>)arg;

        if(identifiers.size() != typeList.returnTypes.size())
        {
            System.out.println("Number of parameters does not match the number of types");
        }

        for(ReturnType returnType : typeList.returnTypes)
        {
            returnType.visit(this, null);
        }

        return null;
    }
}
