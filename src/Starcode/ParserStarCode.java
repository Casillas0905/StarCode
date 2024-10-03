package Starcode;

import static Starcode.TokenKind.*;

public class ParserStarCode {
    private Scanner scan;


    private Token currentTerminal;


    public ParserStarCode( Scanner scan )
    {
        this.scan = scan;

        currentTerminal = scan.scan();
    }

    public void parseProgram()
    {
        parseProgramBlock();

        if( currentTerminal.kind != EOT )
            System.out.println( "Tokens found after end of program" );
    }

    private void parseProgramBlock() {
        accept( LEFTKEY );
        parseDeclarations();
        accept( RIGHTKEY );
    }

    private void parseDeclarations()
    {
        //TODO review what is those values
        while( currentTerminal.kind == STAR ||
                currentTerminal.kind == COMMET ||
                currentTerminal.kind == SUPERNOVA )
            parseOneDeclaration();
    }

    private void parseOneDeclaration() {
        switch( currentTerminal.kind ){
            case STAR:
                accept(STAR);
                accept(IDENTIFIER);
                accept(SEMICOLON);
                break;
            case COMMET:
                accept(COMMET);
                accept(IDENTIFIER);
                accept(SEMICOLON);
                break;
            case SUPERNOVA:
                accept(SUPERNOVA);
                parseIDReturn();
                accept(IDENTIFIER);
                accept(LEFTPARAN);
                parseIDList();
                accept(RIGHTPARAN);
                parseSuperNovaBlock();
                accept(SEMICOLON);
                break;
        }
    }

    private void parseSuperNovaBlock() {
        accept(LEFTKEY);
        parseStatements();
        accept(RETURN);
        accept(IDENTIFIER);
        accept(SEMICOLON);
        accept(RIGHTKEY);
    }

    private void parseStatements() {
        while( currentTerminal.kind == ECLIPSE ||
                currentTerminal.kind == ORBIT ||
                currentTerminal.kind == IDENTIFIER){
            parseOneStatement();
        }
    }

    private void parseOneStatement() {
    }

    //We are not allowing different types for parameters in the methods
    private void parseIDList() {
        accept(IDENTIFIER);
        if (currentTerminal.kind == COMMA) {
            while (currentTerminal.kind == COMMA) {
                accept(COMMA);
                accept(IDENTIFIER);
            }
        }
        if (currentTerminal.kind == LEFTBRACKET) {
                accept(LEFTBRACKET);
                accept(RIGHTBRACKET);
                while (currentTerminal.kind == COMMA) {
                    accept(COMMA);
                    accept(IDENTIFIER);
                    accept(LEFTBRACKET);
                    accept(RIGHTBRACKET);
            }
        }
    }

    private void parseIDReturn() {
        accept(IDENTIFIER);
        if (currentTerminal.kind == LEFTBRACKET){
            accept(LEFTBRACKET);
            accept(RIGHTBRACKET);
        }
    }


    private void accept( TokenKind expected )
    {
        if( currentTerminal.kind == expected )
            currentTerminal = scan.scan();
        else
            System.out.println( "Expected token of kind " + expected );
    }
}
