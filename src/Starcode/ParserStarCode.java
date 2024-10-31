package Starcode;

import static Starcode.TokenKind.*;

public class ParserStarCode
{
    private Scanner scanner;
    private Token currentTerminal;

    public ParserStarCode(Scanner scanner)
    {
        this.scanner = scanner;

        currentTerminal = scanner.scan();
    }

    public void parseProgram()
    {
        parseProgramBlock();
        if(currentTerminal.kind != EOT)
            System.out.println( "Tokens found after end of program" );
    }

    private void parseProgramBlock()
    {
        accept(LEFTKEY);
        parseDeclarations();
        if(currentTerminal.kind == EXPLODE)
            parseSupernovaStatement();
        accept(RIGHTKEY);
    }

    private void parseDeclarations()
    {
        while(currentTerminal.kind == STAR ||
              currentTerminal.kind == COMMET ||
              currentTerminal.kind == SUPERNOVA)
            parseOneDeclaration();
    }

    private void parseOneDeclaration()
    {
        switch(currentTerminal.kind)
        {
            case STAR:
                parseStarDeclaration();
                break;
            case COMMET:
                parseCometDeclaration();
                break;
            case SUPERNOVA:
                parseSupernovaDeclaration();
                break;
        }
    }

    private void parseStarDeclaration()
    {
        accept(STAR);
        accept(IDENTIFIER);
        if(currentTerminal.kind == LEFTBRACKET)
        {
            accept(LEFTBRACKET);
            accept(RIGHTBRACKET);
        }
        accept(SEMICOLON);
    }

    private void parseCometDeclaration()
    {
        accept(COMMET);
        accept(IDENTIFIER);
        if(currentTerminal.kind == LEFTBRACKET)
        {
            accept(LEFTBRACKET);
            accept(RIGHTBRACKET);
        }
        accept(SEMICOLON);
    }

    private void parseSupernovaDeclaration()
    {
        accept(SUPERNOVA);
        parseReturnType();
        accept(IDENTIFIER);
        accept(LEFTPARAN);
        parseIdList();
        accept(RIGHTPARAN);
        parseSupernovaBlock();
        accept(SEMICOLON);
    }

    private void parseReturnType()
    {
        if(currentTerminal.kind == STAR)
        {
            accept(STAR);
        }
        else if(currentTerminal.kind == COMMET)
        {
            accept(COMMET);
        }

        if (currentTerminal.kind == LEFTBRACKET){
            accept(LEFTBRACKET);
            accept(RIGHTBRACKET);
        }
    }

    //We are not allowing different types for parameters in the methods
    private void parseIdList()
    {
        accept(IDENTIFIER);
        if (currentTerminal.kind == COMMA)
        {
            while (currentTerminal.kind == COMMA)
            {
                accept(COMMA);
                accept(IDENTIFIER);
            }
        }
        else if (currentTerminal.kind == LEFTBRACKET)
        {
            accept(LEFTBRACKET);
            accept(RIGHTBRACKET);
            while (currentTerminal.kind == COMMA)
            {
                accept(COMMA);
                accept(IDENTIFIER);
                accept(LEFTBRACKET);
                accept(RIGHTBRACKET);
            }
        }
    }

    private void parseSupernovaBlock()
    {
        accept(WHITEHOLE);
        parseStatements();
        parseReturnStatement();
        accept(BLACKHOLE);
    }

    private void parseBlock()
    {
        accept(WHITEHOLE);
        parseStatements();
        accept(BLACKHOLE);
    }

    private void parseStatements()
    {
        while(currentTerminal.kind == IDENTIFIER ||
              currentTerminal.kind == COMETLITERAL ||
              currentTerminal.kind == STARLITERAL ||
              currentTerminal.kind == EXPLODE||
              currentTerminal.kind == ECLIPSE ||
              currentTerminal.kind == ORBIT ||
              currentTerminal.kind == RETURN)
            parseOneStatement();
    }

    private void parseOneStatement()
    {
        switch (currentTerminal.kind)
        {
            case IDENTIFIER: case COMETLITERAL: case STARLITERAL:
                parseExpressionStatement();
                break;
            case ECLIPSE:
                parseEclipseStatement();
                break;
            case ORBIT:
                parseOrbitStatement();
                break;
            case EXPLODE:
                parseSupernovaStatement();
                break;
            case RETURN:
                parseReturnStatement();
                break;
        }
    }

    private void parseExpressionStatement()
    {
        parseExpression();
        accept(SEMICOLON);
    }

    private void parseEclipseStatement()
    {
        accept(ECLIPSE);
        accept(LEFTPARAN);
        parseExpression();
        accept(RIGHTPARAN);
        parseBlock();
    }

    private void parseOrbitStatement()
    {
        accept(ORBIT);
        accept(LEFTPARAN);
        accept(IDENTIFIER);
        accept(COMMA);
        accept(IDENTIFIER);
        accept(RIGHTPARAN);
        parseBlock();
    }

    private void parseSupernovaStatement()
    {
        accept(EXPLODE);
        accept(IDENTIFIER);
        accept(LEFTPARAN);
        parsePrimary();
        while(currentTerminal.kind == COMMA)
        {
            accept(COMMA);
            parsePrimary();
        }
        accept(RIGHTPARAN);
    }

    private void parseReturnStatement()
    {
        accept(RETURN);
        parsePrimary();
        accept(SEMICOLON);
    }

    private void parseExpression()
    {
        parsePrimary();
        while(currentTerminal.kind == OPERATOR)
        {
            accept(OPERATOR);
            parsePrimary();
        }
    }

    private void parsePrimary()
    {
        switch (currentTerminal.kind)
        {
            case IDENTIFIER:
                accept(IDENTIFIER);
                break;
            case COMETLITERAL:
                accept(COMETLITERAL);
                break;
            //TODO: I think this is broken | The solution to this I guess  will need to be written in the scanner
            case QUOTE:
                accept(QUOTE);
                accept(IDENTIFIER);
                accept(QUOTE);
                break;
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
