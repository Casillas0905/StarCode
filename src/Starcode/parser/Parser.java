package Starcode.parser;

import Starcode.scanner.Scanner;
import Starcode.scanner.Token;
import Starcode.scanner.TokenKind;
import Starcode.parser.ast.*;

import java.util.Vector;

import static Starcode.scanner.TokenKind.*;

/**
 * The Parser class is responsible for syntactic analysis in the compilation process.
 * It processes tokens provided by the Scanner and constructs an abstract syntax tree (AST)
 * based on the grammar rules of the language.
 */
public class Parser
{
    private Scanner scanner; // Scanner instance for tokenizing the input
    private Token currentTerminal; // The current token being processed

    /**
    * Constructs a Parser with a given Scanner instance.
    *
    * @param scanner The Scanner instance to tokenize the input.
    */
    public Parser(Scanner scanner)
    {
        this.scanner = scanner;

        currentTerminal = scanner.scan();
    }

    /**
     * Parses the entire program and returns a Program AST node.
     *
     * @return The root Program node of the abstract syntax tree.
     */
    public Program parseProgram()
    {
        ProgramBlock block = parseProgramBlock();
        if(currentTerminal.kind != EOT)
            System.out.println( "Tokens found after end of program" );

        return new Program(block);
    }

    /**
     * Parses a program block, including declarations and a primary statement.
     *
     * @return A ProgramBlock node representing the parsed block.
     */
    private ProgramBlock parseProgramBlock() {
        Declarations declarations = null;
        SupernovaStatement statement = null;

        accept(LEFTKEY); // Start of the program block
        declarations = parseDeclarations(); // Parse declarations
        if (currentTerminal.kind == EXPLODE) // Parse the primary statement if it exists
            statement = parseSupernovaStatement();
        accept(RIGHTKEY); // End of the program block
        return new ProgramBlock(declarations, statement);
    }

    /**
     * Parses a sequence of declarations.
     *
     * @return A Declarations node containing all parsed declarations.
     */
    private Declarations parseDeclarations() {
        Vector<OneDeclaration> declarations = new Vector<>();

        // Parse declarations as long as the current token matches a valid declaration kind
        while (currentTerminal.kind == STAR ||
                currentTerminal.kind == COMMET ||
                currentTerminal.kind == SUPERNOVA) {
            declarations.add(parseOneDeclaration());
        }

        return new Declarations(declarations);
    }

    /**
     * Parses a single declaration based on its type.
     *
     * @return A OneDeclaration node representing the parsed declaration.
     */
    private OneDeclaration parseOneDeclaration() {
        OneDeclaration declaration = null;
        switch (currentTerminal.kind) {
            case STAR:
                declaration = parseStarDeclaration();
                break;
            case COMMET:
                declaration = parseCometDeclaration();
                break;
            case SUPERNOVA:
                declaration = parseSupernovaDeclaration();
                break;
        }
        return declaration;
    }

    /**
     * Parses a StarDeclaration.
     *
     * @return A StarDeclaration node representing the parsed declaration.
     */
    private StarDeclaration parseStarDeclaration() {
        accept(STAR);
        Identifier identifier = parseIdentifier();
        boolean isArray = false;
        CometLiteral cometLiteral = null;

        // Handle array syntax
        if (currentTerminal.kind == LEFTBRACKET) {
            accept(LEFTBRACKET);
            cometLiteral = parseCometLiteral();
            accept(RIGHTBRACKET);
            isArray = true;
        }
        accept(SEMICOLON);

        int parsedLiteral = 0;
        if (cometLiteral != null) {
            parsedLiteral = Integer.parseInt(cometLiteral.spelling);
        }

        return new StarDeclaration(identifier, isArray, parsedLiteral);
    }

    /**
     * Parses a CometDeclaration.
     *
     * @return A CometDeclaration node representing the parsed declaration.
     */
    private CometDeclaration parseCometDeclaration() {
        accept(COMMET);
        Identifier identifier = parseIdentifier();
        boolean isArray = false;
        CometLiteral cometLiteral = null;

        // Handle array syntax
        if (currentTerminal.kind == LEFTBRACKET) {
            accept(LEFTBRACKET);
            cometLiteral = parseCometLiteral();
            accept(RIGHTBRACKET);
            isArray = true;
        }
        accept(SEMICOLON);

        int parsedLiteral = 0;
        if (cometLiteral != null) {
            parsedLiteral = Integer.parseInt(cometLiteral.spelling);
        }
        return new CometDeclaration(identifier, isArray, parsedLiteral);
    }

    /**
     * Parses a TypeList, which represents a collection of return types for function parameters or other constructs.
     *
     * @return A TypeList node containing the parsed return types.
     */
    private TypeList ParseTypeList() {
        accept(TILDE); // Expect the '~' token
        accept(GT);    // Expect the '>' token
        Vector<ReturnType> returnTypes = new Vector<>();
        returnTypes.add(parseReturnType()); // Parse the first return type

        // Parse additional return types separated by commas
        while (currentTerminal.kind == COMMA) {
            accept(COMMA);
            returnTypes.add(parseReturnType());
        }

        return new TypeList(returnTypes);
    }

    /**
     * Parses a SupernovaDeclaration, representing a function or method declaration.
     *
     * @return A SupernovaDeclaration node containing the function's signature and body.
     */
    private SupernovaDeclaration parseSupernovaDeclaration() {
        accept(SUPERNOVA); // Expect the 'SUPERNOVA' token (function declaration keyword)
        ReturnType returnType = parseReturnType(); // Parse the function's return type
        Identifier identifier = parseIdentifier(); // Parse the function's name
        accept(LEFTPARAN); // Expect '(' token
        IdList idList = parseIdList(); // Parse the parameter list
        accept(RIGHTPARAN); // Expect ')' token
        TypeList typeList = ParseTypeList(); // Parse the function's parameter types
        SupernovaBlock block = parseSupernovaBlock(); // Parse the function's body
        accept(SEMICOLON); // Expect ';' token

        return new SupernovaDeclaration(returnType, identifier, idList, block, typeList);
    }

    /**
     * Parses a ReturnType, which specifies the data type returned by a function or method.
     *
     * @return A ReturnType node containing the type and array information.
     */
    private ReturnType parseReturnType() {
        String type = null;
        boolean isArray = false;

        // Determine if the return type is a STAR or COMMET
        if (currentTerminal.kind == STAR) {
            type = currentTerminal.spelling;
            accept(STAR); // Accept the STAR token
        } else if (currentTerminal.kind == COMMET) {
            type = currentTerminal.spelling;
            accept(COMMET); // Accept the COMMET token
        } else {
            System.out.println("Expected STAR or COMMET"); // Handle unexpected token
            type = "???"; // Placeholder for error
        }

        // Check if the return type is an array (indicated by square brackets)
        if (currentTerminal.kind == LEFTBRACKET) {
            accept(LEFTBRACKET); // Accept '['
            accept(RIGHTBRACKET); // Accept ']'
            isArray = true;
        }

        return new ReturnType(type, isArray);
    }

    /**
     * Parses an IdList, which represents a list of identifiers for function parameters.
     * Note: All parameters must have the same type.
     *
     * @return An IdList node containing the parsed identifiers and array flag.
     */
    private IdList parseIdList() {
        Vector<Identifier> identifiers = new Vector<>();
        boolean areArrays = false;

        identifiers.add(parseIdentifier()); // Parse the first identifier

        // Parse additional identifiers separated by commas
        if (currentTerminal.kind == COMMA) {
            while (currentTerminal.kind == COMMA) {
                accept(COMMA); // Accept the comma
                identifiers.add(parseIdentifier()); // Parse the next identifier
            }
        }
        // Parse array identifiers if applicable
        else if (currentTerminal.kind == LEFTBRACKET) {
            accept(LEFTBRACKET); // Accept '['
            accept(RIGHTBRACKET); // Accept ']'
            while (currentTerminal.kind == COMMA) {
                accept(COMMA); // Accept the comma
                identifiers.add(parseIdentifier()); // Parse the next identifier
                accept(LEFTBRACKET); // Accept '['
                accept(RIGHTBRACKET); // Accept ']'
            }
            areArrays = true; // Set the array flag
        }

        return new IdList(identifiers, areArrays);
    }

    /**
     * Parses a SupernovaBlock, which represents a block of code consisting of statements
     * followed by a return statement.
     *
     * @return A SupernovaBlock node containing the parsed statements and return statement.
     */
    private SupernovaBlock parseSupernovaBlock() {
        accept(WHITEHOLE); // Expect the start of the block
        Statements statements = parseStatements(); // Parse the block's statements
        ReturnStatement returnStatement = parseReturnStatement(); // Parse the return statement
        accept(BLACKHOLE); // Expect the end of the block

        return new SupernovaBlock(statements, returnStatement);
    }

    /**
     * Parses a Block, which consists of a series of statements enclosed in block markers.
     *
     * @return A Block node containing the parsed statements.
     */
    private Block parseBlock() {
        accept(WHITEHOLE); // Expect the start of the block
        Statements statements = parseStatements(); // Parse the statements within the block
        accept(BLACKHOLE); // Expect the end of the block

        return new Block(statements);
    }

    /**
     * Parses a sequence of statements.
     *
     * @return A Statements node containing a collection of parsed statements.
     */
    private Statements parseStatements() {
        Vector<OneStatement> statements = new Vector<>();

        // Continue parsing while the current token matches a valid statement kind
        while (currentTerminal.kind == IDENTIFIER ||
                currentTerminal.kind == COMETLITERAL ||
                currentTerminal.kind == STARLITERAL ||
                currentTerminal.kind == EXPLODE ||
                currentTerminal.kind == ECLIPSE ||
                currentTerminal.kind == ORBIT ||
                currentTerminal.kind == RETURN) {
            statements.add(parseOneStatement());
        }

        return new Statements(statements);
    }

    /**
     * Parses a single statement.
     *
     * @return A OneStatement node representing the parsed statement.
     */
    private OneStatement parseOneStatement() {
        OneStatement statement = null;

        // Determine the type of statement based on the current token
        switch (currentTerminal.kind) {
            case IDENTIFIER:
            case COMETLITERAL:
            case STARLITERAL:
                statement = parseExpressionStatement();
                break;
            case ECLIPSE:
                statement = parseEclipseStatement();
                break;
            case ORBIT:
                statement = parseOrbitStatement();
                break;
            case EXPLODE:
                statement = parseSupernovaStatement();
                break;
            case RETURN:
                statement = parseReturnStatement();
                break;
        }

        return statement;
    }

    /**
     * Parses an ExpressionStatement, which is an expression followed by a semicolon.
     *
     * @return An ExpressionStatement node representing the parsed statement.
     */
    private ExpressionStatement parseExpressionStatement() {
        Expression expression = parseExpression(); // Parse the expression
        accept(SEMICOLON); // Expect the semicolon
        return new ExpressionStatement(expression);
    }

    /**
     * Parses an EclipseStatement, which represents a conditional statement with an associated block.
     *
     * @return An EclipseStatement node representing the parsed statement.
     */
    private EclipseStatement parseEclipseStatement() {
        accept(ECLIPSE); // Expect the 'ECLIPSE' keyword
        accept(LEFTPARAN); // Expect '('
        Expression expression = parseExpression(); // Parse the conditional expression
        accept(RIGHTPARAN); // Expect ')'
        Block block = parseBlock(); // Parse the associated block

        return new EclipseStatement(expression, block);
    }

    /**
     * Parses an OrbitStatement, which represents a loop with an incremental identifier and a count.
     *
     * @return An OrbitStatement node representing the parsed loop statement.
     */
    private OrbitStatement parseOrbitStatement() {
        accept(ORBIT); // Expect the 'ORBIT' keyword
        accept(LEFTPARAN); // Expect '('
        Identifier incrementalIdentifier = parseIdentifier(); // Parse the incremental identifier
        accept(COMMA); // Expect ','
        Identifier countIdentifier = parseIdentifier(); // Parse the count identifier
        accept(RIGHTPARAN); // Expect ')'
        Block block = parseBlock(); // Parse the associated block

        return new OrbitStatement(incrementalIdentifier, countIdentifier, block);
    }

    /**
     * Parses a SupernovaStatement, which represents a function or method call statement.
     *
     * @return A SupernovaStatement node containing the function's name and parameters.
     */
    private SupernovaStatement parseSupernovaStatement() {
        Vector<Primary> parameters = new Vector<>();

        accept(EXPLODE); // Expect the 'EXPLODE' token (function call keyword)
        Identifier identifier = parseIdentifier(); // Parse the function's name
        accept(LEFTPARAN); // Expect '(' token
        parameters.add(parsePrimary()); // Parse the first parameter

        // Parse additional parameters separated by commas
        while (currentTerminal.kind == COMMA) {
            accept(COMMA); // Accept the comma
            parameters.add(parsePrimary()); // Parse the next parameter
        }
        accept(RIGHTPARAN); // Expect ')' token

        return new SupernovaStatement(identifier, parameters);
    }

    /**
     * Parses a ReturnStatement, which represents a return statement in the program.
     *
     * @return A ReturnStatement node containing the identifier to return.
     */
    private ReturnStatement parseReturnStatement() {
        accept(RETURN); // Expect the 'RETURN' keyword
        Identifier identifier = parseIdentifier(); // Parse the identifier to return
        accept(SEMICOLON); // Expect the ';' token

        return new ReturnStatement(identifier);
    }

    /**
     * Parses an Expression, which consists of a primary value followed by operators and additional primaries.
     *
     * @return An Expression node containing the primary, operators, and additional primaries.
     */
    private Expression parseExpression() {
        Vector<Operator> operators = new Vector<>();
        Vector<Primary> primaries = new Vector<>();

        Primary primary = parsePrimary(); // Parse the first primary value
        while (currentTerminal.kind == OPERATOR) { // Continue while operators are present
            operators.add(parseOperator()); // Parse the operator
            primaries.add(parsePrimary()); // Parse the next primary value
        }

        return new Expression(primary, primaries, operators);
    }

    /**
     * Parses a Primary, which can be an identifier, a literal, or an array access.
     *
     * @return A Primary node representing the parsed element.
     */
    private Primary parsePrimary() {
        Primary primary = null;

        // Determine the type of primary based on the current token
        switch (currentTerminal.kind) {
            case IDENTIFIER:
                Identifier identifier = parseIdentifier(); // Parse the identifier
                if (currentTerminal.kind == LEFTBRACKET) { // Check for array access
                    primary = new Primary(parseArrayAccess(identifier));
                } else {
                    primary = new Primary(identifier);
                }
                break;
            case COMETLITERAL:
                primary = new Primary(parseCometLiteral()); // Parse a comet literal
                accept(COMETLITERAL);
                break;
            case QUOTE:
                primary = new Primary(parseStarLiteral()); // Parse a star literal
                break;
        }
        return primary;
    }

    /**
     * Parses an ArrayAccess, which represents an array access operation.
     *
     * @param identifier The identifier of the array being accessed.
     * @return An ArrayAccess node representing the parsed operation.
     */
    private ArrayAccess parseArrayAccess(Identifier identifier) {
        accept(LEFTBRACKET); // Expect '[' token
        CometLiteral cometLiteral = parseCometLiteral(); // Parse the index value
        accept(RIGHTBRACKET); // Expect ']' token
        return new ArrayAccess(identifier, cometLiteral);
    }

    /**
     * Parses an Identifier, which represents a variable or function name.
     *
     * @return An Identifier node containing the name.
     */
    private Identifier parseIdentifier() {
        if (currentTerminal.kind == IDENTIFIER) { // Check if the current token is an identifier
            Identifier identifier = new Identifier(currentTerminal.spelling);
            accept(IDENTIFIER); // Accept the identifier token
            return identifier;
        } else {
            System.out.println("Identifier expected"); // Handle unexpected token
            return new Identifier("????"); // Placeholder for error recovery
        }
    }

    /**
     * Parses a CometLiteral, which represents a numeric literal in the language.
     *
     * @return A CometLiteral node containing the parsed value, or a placeholder if the expected token is missing.
     */
    private CometLiteral parseCometLiteral() {
        if (currentTerminal.kind == COMETLITERAL) { // Check if the current token is a comet literal
            CometLiteral literal = new CometLiteral(currentTerminal.spelling); // Create a new CometLiteral node
            accept(COMETLITERAL); // Consume the comet literal token
            return literal;
        } else {
            System.out.println("CometLiteral expected"); // Handle unexpected token
            return new CometLiteral("????"); // Placeholder for error recovery
        }
    }

    /**
     * Parses a StarLiteral, which represents a quoted string in the language.
     *
     * @return A StarLiteral node containing the parsed value, or a placeholder if the expected token is missing.
     */
    private StarLiteral parseStarLiteral() {
        if (currentTerminal.kind == QUOTE) { // Check if the current token is a quote
            accept(QUOTE); // Consume the opening quote
            StarLiteral literal = new StarLiteral(currentTerminal.spelling); // Create a new StarLiteral node
            accept(IDENTIFIER); // Consume the identifier inside the quotes
            accept(QUOTE); // Consume the closing quote
            return literal;
        } else {
            System.out.println("StarLiteral expected"); // Handle unexpected token
            return new StarLiteral("????"); // Placeholder for error recovery
        }
    }

    /**
     * Parses an Operator, which represents a mathematical or logical operator in the language.
     *
     * @return An Operator node containing the parsed operator, or a placeholder if the expected token is missing.
     */
    private Operator parseOperator() {
        if (currentTerminal.kind == OPERATOR) { // Check if the current token is an operator
            Operator operator = new Operator(currentTerminal.spelling); // Create a new Operator node
            accept(OPERATOR); // Consume the operator token
            return operator;
        } else {
            System.out.println("Operator expected"); // Handle unexpected token
            return new Operator("????"); // Placeholder for error recovery
        }
    }

    /**
     * Validates that the current token matches the expected kind and advances to the next token.
     * If the token does not match, an error message is displayed, and parsing continues.
     *
     * @param expected The expected TokenKind.
     */
    private void accept(TokenKind expected) {
        if (currentTerminal.kind == expected) {
            currentTerminal = scanner.scan(); // Advance to the next token
        } else {
            System.out.println("Expected token of kind " + expected); // Print error message
        }
    }
}
