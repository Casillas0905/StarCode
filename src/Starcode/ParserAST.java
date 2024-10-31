package Starcode;

import Starcode.ast.*;

import java.util.GregorianCalendar;
import java.util.Vector;

import static Starcode.TokenKind.*;

public class ParserAST
{
    private Scanner scanner;
    private Token currentTerminal;

    public ParserAST(Scanner scanner)
    {
        this.scanner = scanner;

        currentTerminal = scanner.scan();
    }

    public Program parseProgram()
    {
        ProgramBlock block = parseProgramBlock();
        if(currentTerminal.kind != EOT)
            System.out.println( "Tokens found after end of program" );

        return new Program(block);
    }

    private ProgramBlock parseProgramBlock()
    {
        Declarations declarations = null;
        SupernovaStatement statement = null;

        accept(LEFTKEY);
        declarations = parseDeclarations();
        if(currentTerminal.kind == EXPLODE)
            statement = parseSupernovaStatement();
        accept(RIGHTKEY);
        return new ProgramBlock(declarations, statement);
    }

    private Declarations parseDeclarations()
    {
        Vector<OneDeclaration> declarations = new Vector<>();

        while(currentTerminal.kind == STAR ||
                currentTerminal.kind == COMMET ||
                currentTerminal.kind == SUPERNOVA)
            declarations.add(parseOneDeclaration());

        return new Declarations(declarations);
    }

    private OneDeclaration parseOneDeclaration()
    {
        OneDeclaration declaration = null;
        switch(currentTerminal.kind)
        {
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

    private StarDeclaration parseStarDeclaration()
    {
        accept(STAR);

        Identifier identifier = parseIdentifier();
        boolean isArray = false;

        if(currentTerminal.kind == LEFTBRACKET)
        {
            accept(LEFTBRACKET);
            accept(RIGHTBRACKET);
            isArray = true;
        }
        accept(SEMICOLON);

        return new StarDeclaration(identifier, isArray);
    }

    private CometDeclaration parseCometDeclaration()
    {
        accept(COMMET);

        Identifier identifier = parseIdentifier();
        boolean isArray = false;

        if(currentTerminal.kind == LEFTBRACKET)
        {
            accept(LEFTBRACKET);
            accept(RIGHTBRACKET);
            isArray = true;
        }
        accept(SEMICOLON);

        return new CometDeclaration(identifier, isArray);
    }

    private TypeList ParseTypeList(){
        accept(TILDE);
        accept(GT);
        Vector<ReturnType> returnTypes = new Vector<>();
        returnTypes.add(parseReturnType());
        while (currentTerminal.kind == COMMA){
            accept(COMMA);
            returnTypes.add(parseReturnType());
        }
        return new TypeList(returnTypes);
    }

    private SupernovaDeclaration parseSupernovaDeclaration()
    {
        accept(SUPERNOVA);
        ReturnType returnType = parseReturnType();
        Identifier identifier = parseIdentifier();
        accept(LEFTPARAN);
        IdList idList = parseIdList();
        accept(RIGHTPARAN);
        TypeList typeList = ParseTypeList();
        SupernovaBlock block = parseSupernovaBlock();
        accept(SEMICOLON);

        return new SupernovaDeclaration(returnType, identifier, idList, block, typeList);
    }

    private ReturnType parseReturnType()
    {
        String type = null;
        boolean isArray = false;

        if(currentTerminal.kind == STAR)
        {
            type = currentTerminal.spelling;
            accept(STAR);
        }
        else if(currentTerminal.kind == COMMET)
        {
            type = currentTerminal.spelling;
            accept(COMMET);
        }
        else
        {
            System.out.println("Expected STAR or COMMET");
            type = "???";
        }

        if (currentTerminal.kind == LEFTBRACKET){
            accept(LEFTBRACKET);
            accept(RIGHTBRACKET);
            isArray = true;
        }

        return new ReturnType(type, isArray);
    }

    //We are not allowing different types for parameters in the methods
    private IdList parseIdList()
    {
        Vector<Identifier> identifiers = new Vector<>();
        boolean areArrays = false;

        identifiers.add(parseIdentifier());
        if (currentTerminal.kind == COMMA)
        {
            while (currentTerminal.kind == COMMA)
            {
                accept(COMMA);
                identifiers.add(parseIdentifier());
            }
        }
        else if (currentTerminal.kind == LEFTBRACKET)
        {
            accept(LEFTBRACKET);
            accept(RIGHTBRACKET);
            while (currentTerminal.kind == COMMA)
            {
                accept(COMMA);
                identifiers.add(parseIdentifier());
                accept(LEFTBRACKET);
                accept(RIGHTBRACKET);
            }
            areArrays = true;
        }

        return new IdList(identifiers, areArrays);
    }

    private SupernovaBlock parseSupernovaBlock()
    {
        accept(WHITEHOLE);
        Statements statements =  parseStatements();
        ReturnStatement returnStatement = parseReturnStatement();
        accept(BLACKHOLE);

        return new SupernovaBlock(statements, returnStatement);
    }

    private Block parseBlock()
    {
        accept(WHITEHOLE);
        Statements statements = parseStatements();
        accept(BLACKHOLE);

        return new Block(statements);
    }

    private Statements parseStatements()
    {
        Vector<OneStatement> statements = new Vector<>();

        while(currentTerminal.kind == IDENTIFIER ||
                currentTerminal.kind == COMETLITERAL ||
                currentTerminal.kind == STARLITERAL ||
                currentTerminal.kind == EXPLODE||
                currentTerminal.kind == ECLIPSE ||
                currentTerminal.kind == ORBIT ||
                currentTerminal.kind == RETURN)
            statements.add(parseOneStatement());

        return new Statements(statements);
    }

    private OneStatement parseOneStatement()
    {
        OneStatement statement = null;
        switch (currentTerminal.kind)
        {
            case IDENTIFIER: case COMETLITERAL: case STARLITERAL:
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

    private ExpressionStatement parseExpressionStatement()
    {
        Expression expression = parseExpression();
        accept(SEMICOLON);
        return new ExpressionStatement(expression);
    }

    private EclipseStatement parseEclipseStatement()
    {
        accept(ECLIPSE);
        accept(LEFTPARAN);
        Expression expression = parseExpression();
        accept(RIGHTPARAN);
        Block block = parseBlock();

        return new EclipseStatement(expression, block);
    }

    private OrbitStatement parseOrbitStatement()
    {
        accept(ORBIT);
        accept(LEFTPARAN);
        Identifier incrementalIdentifier = parseIdentifier();
        accept(COMMA);
        Identifier countIdentifier = parseIdentifier();
        accept(RIGHTPARAN);
        Block block = parseBlock();

        return new OrbitStatement(incrementalIdentifier, countIdentifier, block);
    }

    private SupernovaStatement parseSupernovaStatement()
    {
        Vector<Primary> parameters = new Vector<>();

        accept(EXPLODE);
        Identifier identifier = parseIdentifier();
        accept(LEFTPARAN);
        parameters.add(parsePrimary());
        while(currentTerminal.kind == COMMA)
        {
            accept(COMMA);
            parameters.add(parsePrimary());
        }
        accept(RIGHTPARAN);

        return new SupernovaStatement(identifier, parameters);
    }

    private ReturnStatement parseReturnStatement()
    {
        accept(RETURN);
        Primary primary = parsePrimary();
        accept(SEMICOLON);

        return new ReturnStatement(primary);
    }

    private Expression parseExpression()
    {
        Vector<Operator> operators = new Vector<>();
        Vector<Primary> primaries = new Vector<>();

        Primary primary = parsePrimary();
        while(currentTerminal.kind == OPERATOR)
        {
            operators.add(parseOperator());
            primaries.add(parsePrimary());
        }

        return new Expression(primary, primaries, operators);
    }

    private Primary parsePrimary()
    {
        Primary primary = null;
        switch (currentTerminal.kind)
        {
            case IDENTIFIER:
                Identifier identifier = parseIdentifier();
                if (currentTerminal.kind == LEFTBRACKET){
                    primary = new Primary(parseArrayAccess(identifier));
                } else primary = new Primary(identifier);
                break;
            case COMETLITERAL:
                primary = new Primary(parseCometLiteral());
                accept(COMETLITERAL);
                break;
            case QUOTE:
                primary = new Primary(parseStarLiteral());
                break;
        }
        return primary;
    }

    private ArrayAccess parseArrayAccess(Identifier identifier){
        accept(LEFTBRACKET);
        CometLiteral cometLiteral = parseCometLiteral();
        accept(RIGHTBRACKET);
        return new ArrayAccess(identifier, cometLiteral);
    }

    private Identifier parseIdentifier()
    {
        if(currentTerminal.kind == IDENTIFIER)
        {
            Identifier identifier = new Identifier(currentTerminal.spelling);
            accept(IDENTIFIER);
            return identifier;
        }
        else
        {
            System.out.println("Identifier expected");
            return new Identifier("????");
        }
    }

    private CometLiteral parseCometLiteral()
    {
        if(currentTerminal.kind == COMETLITERAL)
        {
            CometLiteral literal = new CometLiteral(currentTerminal.spelling);
            accept(COMETLITERAL);
            return literal;
        }
        else
        {
            System.out.println("CometLiteral expected");
            return new CometLiteral("????");
        }
    }

    private StarLiteral parseStarLiteral()
    {
        if(currentTerminal.kind == QUOTE)
        {
            accept(QUOTE);
            StarLiteral literal = new StarLiteral(currentTerminal.spelling);
            accept(IDENTIFIER);
            accept(QUOTE);
            return literal;
        }
        else
        {
            System.out.println("StarLiteral expected");
            return new StarLiteral("????");
        }
    }

    private Operator parseOperator()
    {
        if(currentTerminal.kind == OPERATOR)
        {
            Operator operator = new Operator(currentTerminal.spelling);
            accept(OPERATOR);
            return operator;
        }
        else
        {
            System.out.println("Operator expected");
            return new Operator("????");
        }
    }

    private void accept(TokenKind expected)
    {
        if(currentTerminal.kind == expected)
            currentTerminal = scanner.scan();
        else
            System.out.println( "Expected token of kind " + expected );
    }
}
