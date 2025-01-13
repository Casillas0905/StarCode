package Starcode.semantics;

import Starcode.core.IVisitor;
import Starcode.parser.ast.*;

import java.util.Vector;

/**
 * The SemanticVisitor class is responsible for performing semantic analysis of the AST.
 * It checks for correct variable declarations, initialization, and statement semantics.
 * It also uses DeclarationsTable and InitializationTable to keep track of declared variables and initialized variables.
 */
public class SemanticVisitor implements IVisitor {

    private DeclarationsTable declarationsTable = new DeclarationsTable(); // Table to store declared variables
    private InitializationTable initializationTable = new InitializationTable(); // Table to store initialized variables

    @Override
    public Object visitProgram(Program program, Object arg) {
        // Visit the program block
        program.block.visit(this, null);
        return null;
    }

    @Override
    public Object visitProgramBlock(ProgramBlock programBlock, Object arg) {
        // Visit the declarations and statement within the program block
        programBlock.declarations.visit(this, null);
        programBlock.statement.visit(this, null);
        return null;
    }

    @Override
    public Object visitDeclarations(Declarations declarations, Object arg) {
        // Visit each declaration in the declarations list
        for (OneDeclaration declaration : declarations.declarations) {
            declaration.visit(this, null);
        }
        return null;
    }

    @Override
    public Object visitStarDeclaration(StarDeclaration starDeclaration, Object arg) {
        // Visit the identifier of the declaration and add it to the declarations table
        String id = (String) starDeclaration.identifier.visit(this, null);
        declarationsTable.addDeclaration(id, starDeclaration);
        return null;
    }

    @Override
    public Object visitCometDeclaration(CometDeclaration cometDeclaration, Object arg) {
        // Visit the identifier of the declaration and add it to the declarations table
        String id = (String) cometDeclaration.identifier.visit(this, null);
        declarationsTable.addDeclaration(id, cometDeclaration);
        return null;
    }

    @Override
    public Object visitSupernovaDeclaration(SupernovaDeclaration supernovaDeclaration, Object arg) {
        // Visit the return type of the supernova declaration
        supernovaDeclaration.returnType.visit(null, this);

        // Visit the identifier and add it to the declarations table
        String id = (String) supernovaDeclaration.identifier.visit(this, null);
        declarationsTable.addDeclaration(id, supernovaDeclaration);

        // Visit the list of identifiers (parameters) and type list
        supernovaDeclaration.idList.visit(this, null);
        supernovaDeclaration.typeList.visit(this, supernovaDeclaration.idList.identifiers);

        // Visit the supernova block (the function body)
        supernovaDeclaration.supernovaBlock.visit(this, supernovaDeclaration.returnType);
        return null;
    }

    @Override
    public Object visitBlock(Block block, Object arg) {
        // Visit all statements in the block
        block.statements.visit(this, null);
        return null;
    }

    @Override
    public Object visitArrayAccess(ArrayAccess arrayAccess, Object arg) {
        // Visit the identifier in the array access (base of the array)
        arrayAccess.identifier.visit(this, null);
        return null;
    }

    @Override
    public Object visitCometLiteral(CometLiteral cometLiteral, Object arg) {
        // Return the spelling of the comet literal (usually a number or value)
        return cometLiteral.spelling;
    }

    @Override
    public Object visitEclipseStatement(EclipseStatement eclipseStatement, Object arg) {
        // Check if there are any assignment operations in the Eclipse statement (which are not allowed)
        for (Operator operator : eclipseStatement.expression.operators) {
            if (operator.spelling.equals("=")) {
                System.out.println("Value assignment operations are not allowed in eclipse statements");
            }
        }

        // Visit the expression and block in the eclipse statement
        eclipseStatement.expression.visit(this, null);
        eclipseStatement.block.visit(this, null);
        return null;
    }

    @Override
    public Object visitExpression(Expression expression, Object arg) {
        // Check if the first operator is an assignment operator ('=')
        if (!expression.operators.isEmpty()) {
            if (expression.operators.get(0).spelling.equals("=")) {
                // TODO: Implement semantic checks for assignment operations
                /*
                 * A variable can only be assigned a value that matches its corresponding literal type
                 * or the declared and initialized ReturnType.
                 * - Array -> CometLiteral (Assignment to an array should match a CometLiteral)
                 * - ArrayAccess -> (Consider additional checks for ArrayAccess assignments)
                 */

                // Mark the variable as initialized in the initialization table
                initializationTable.addInitialization(expression.primary.identifier.spelling, null);
            }
        }

        // Visit the primary expression (the left-hand side of the assignment or the first operand)
        expression.primary.visit(this, null);

        // Visit all the primary operands in the expression
        for (Primary primary : expression.primaries) {
            primary.visit(this, null);
        }

        // Visit all operators in the expression
        for (Operator operator : expression.operators) {
            operator.visit(this, null);
        }

        return null;
    }

    @Override
    public Object visitExpressionStatement(ExpressionStatement expressionStatement, Object arg) {
        // Visit the expression inside the expression statement
        expressionStatement.expression.visit(this, null);
        return null;
    }

    @Override
    public Object visitIdentifier(Identifier identifier, Object arg) {
        // Return the spelling (name) of the identifier
        return identifier.spelling;
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
    public Object visitOperator(Operator operator, Object arg) {
        // Return the operator's spelling (symbol)
        return operator.spelling;
    }

    @Override
    public Object visitOrbitStatement(OrbitStatement orbitStatement, Object arg) {
        // Visit and retrieve the names of the count and incremental identifiers
        String countId = (String) orbitStatement.countIdentifier.visit(this, null);
        String incrementalId = (String) orbitStatement.incrementalIdentifier.visit(this, null);

        // Retrieve the declarations for the count and incremental identifiers
        OneDeclaration countIdDeclaration = declarationsTable.getDeclaration(countId);
        OneDeclaration incrementalIdDeclaration = declarationsTable.getDeclaration(incrementalId);

        // Retrieve the initializations for the count and incremental identifiers
        InitializedId countIdInitialization = initializationTable.getInitializedId(countId);
        InitializedId incrementalIdInitialization = initializationTable.getInitializedId(countId);

        // Check that the count identifier is of type Comet
        if (!(countIdDeclaration instanceof CometDeclaration)) {
            System.out.println("Count identifier in the orbit statement must be of type Comet");
        }

        // Check that the incremental identifier is of type Comet
        if (!(incrementalIdDeclaration instanceof CometDeclaration)) {
            System.out.println("Incremental identifier in the orbit statement must be of type Comet");
        }

        // Check that the count identifier is declared
        if (countIdDeclaration == null) {
            System.out.println("Missing declaration statement for identifier with id: " + countId);
        }

        // Check that the count identifier is initialized
        if (countIdInitialization == null) {
            System.out.println("Missing initialization statement for identifier with id: " + countId);
        }

        // Check that the incremental identifier is declared
        if (incrementalIdDeclaration == null) {
            System.out.println("Missing declaration statement for identifier with id: " + incrementalId);
        }

        // Check that the incremental identifier is initialized
        if (incrementalIdInitialization == null) {
            System.out.println("Missing initialization statement for identifier with id: " + incrementalId);
        }

        // Visit the block of statements inside the OrbitStatement
        orbitStatement.block.visit(this, null);
        return null;
    }

    @Override
    public Object visitPrimary(Primary primary, Object arg) {
        // Check if the primary is an identifier
        if (primary.identifier != null) {
            // Get the identifier from the primary and check if it is declared and initialized
            String id = (String) primary.identifier.visit(this, null);
            if (declarationsTable.getDeclaration(id) == null) {
                System.out.println("Missing declaration statement for primary with id: " + id);
            }
            if (initializationTable.getInitializedId(id) == null) {
                System.out.println("Missing initialization statement for primary with id: " + id);
            }
        }

        // Check if the primary is an array access
        if (primary.arrayAccess.identifier != null) {
            // Get the identifier from the array access and check if it is declared and initialized
            String id = (String) primary.arrayAccess.identifier.visit(this, null);
            if (declarationsTable.getDeclaration(id) == null) {
                System.out.println("Missing declaration statement for primary with id: " + id);
            }
            if (initializationTable.getInitializedId(id) == null) {
                System.out.println("Missing initialization statement for primary with id: " + id);
            }
        }

        // Check if the primary is a star literal
        if (primary.starLiteral != null) {
            primary.starLiteral.visit(this, null);  // Visit the star literal
        }

        // Check if the primary is a comet literal
        if (primary.cometLiteral != null) {
            primary.cometLiteral.visit(this, null);  // Visit the comet literal
        }

        return null;
    }

    @Override
    public Object visitReturnStatement(ReturnStatement returnStatement, Object arg) {
        // Get the expected return type of the supernova subroutine
        ReturnType originalReturnType = (ReturnType) arg;

        // Get the identifier being returned
        String id = (String) returnStatement.identifier.visit(this, null);

        // Retrieve the declaration for the returned identifier
        OneDeclaration declaredIdentifier = declarationsTable.getDeclaration(id);

        // Check if the identifier is declared
        if (declaredIdentifier == null) {
            System.out.println("Missing declaration statement for primary with id: " + id);
        }

        // Check if the identifier is initialized
        if (initializationTable.getInitializedId(id) == null) {
            System.out.println("Missing initialization statement for primary with id: " + id);
        }

        // Perform type checking for the returned value and ensure it matches the expected return type
        switch (originalReturnType.type) {
            case "star":
                // Check if the returned variable matches the declared return type (StarDeclaration)
                if (!(declaredIdentifier instanceof StarDeclaration) ||
                        originalReturnType.isArray != ((StarDeclaration) declaredIdentifier).isArray) {
                    System.out.println("Returned variable with id: " + id +
                            " does not match the declared return type of the supernova subroutine");
                }
                break;
            case "comet":
                // Check if the returned variable matches the declared return type (CometDeclaration)
                if (!(declaredIdentifier instanceof CometDeclaration) ||
                        originalReturnType.isArray != ((CometDeclaration) declaredIdentifier).isArray) {
                    System.out.println("Returned variable with id: " + id +
                            " does not match the declared return type of the supernova subroutine");
                }
                break;
            default:
                System.out.println("Unrecognized type in a return statement");
        }

        return null;
    }

    @Override
    public Object visitReturnType(ReturnType returnType, Object arg) {
        // Return the type of the return type
        return returnType.type;
    }

    @Override
    public Object visitStarLiteral(StarLiteral starLiteral, Object arg) {
        // Return the spelling of the star literal
        return starLiteral.spelling;
    }

    @Override
    public Object visitStatements(Statements statements, Object arg) {
        // Visit each statement in the statements list
        for (OneStatement statement : statements.statements) {
            statement.visit(this, null);
        }
        return null;
    }

    @Override
    public Object visitSupernovaBlock(SupernovaBlock supernovaBlock, Object arg) {
        // Visit all statements in the supernova block
        supernovaBlock.statements.visit(this, null);

        // Visit the return statement of the supernova block
        supernovaBlock.returnStatement.visit(this, arg);
        return null;
    }

    @Override
    public Object visitSupernovaStatement(SupernovaStatement supernovaStatement, Object arg) {
        // Retrieve the identifier of the supernova (function or method)
        String id = (String) supernovaStatement.identifier.visit(this, null);

        // Retrieve the supernova declaration from the declarations table
        OneDeclaration supernovaDeclaration = declarationsTable.getDeclaration(id);

        // Visit each parameter of the supernova statement (arguments passed to the function)
        for (Primary primary : supernovaStatement.parameters) {
            primary.visit(this, null);
        }

        // Check if the supernova declaration exists
        if (supernovaDeclaration == null) {
            // Error: Supernova is not declared
            System.out.println("Missing declaration statement for supernova statement with id: " + id);
        } else {
            // Check if the number of parameters passed matches the number of parameters declared
            int amountOfParameters = supernovaStatement.parameters.size();
            int amountOfDeclaredParameters = ((SupernovaDeclaration) supernovaDeclaration).typeList.returnTypes.size();

            if (amountOfDeclaredParameters != amountOfParameters) {
                // Error: Number of parameters passed does not match the declaration
                System.out.println("The number of parameters passed to the supernova does not match the parameters declared in its definition. Supernova ID:" + id);
            }
        }

        return null;
    }

    @Override
    public Object visitTypeList(TypeList typeList, Object arg) {
        // The argument is a vector of identifiers (parameters) passed to the supernova
        Vector<Identifier> identifiers = (Vector<Identifier>) arg;

        // Check if the number of identifiers matches the number of return types
        if (identifiers.size() != typeList.returnTypes.size()) {
            // Error: Number of parameters does not match the number of types
            System.out.println("Number of parameters does not match the number of types");
        }

        // Visit each return type in the type list
        for (ReturnType returnType : typeList.returnTypes) {
            returnType.visit(this, null);
        }

        return null;
    }
}