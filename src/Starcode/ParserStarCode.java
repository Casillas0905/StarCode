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
        if( currentTerminal.kind == IDENTIFIER){
            parseMethodStatement();
        }
        accept( RIGHTKEY );
    }

    private void parseMethodStatement() {
        accept(IDENTIFIER);
        accept(LEFTPARAN);
        parsePrimary();
        while (currentTerminal.kind == COMMA){
            accept(COMMA);
            parsePrimary();
        }
        accept(RIGHTPARAN);
        accept(SEMICOLON);
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

    private void parseBlock(){

        if (currentTerminal.kind == RETURN){
            accept(RETURN);
            accept(IDENTIFIER);
        }
        else{
            parseStatements();
            if (currentTerminal.kind == RETURN){
                accept(RETURN);
                accept(IDENTIFIER);
            }
        }
    }

    private void parseOneStatement() {
        //Todo parse expression
            switch (currentTerminal.kind){
                case ECLIPSE:
                        accept(ECLIPSE);
                        accept(LEFTPARAN);
                        parseExpression();
                        accept(RIGHTPARAN);
                        accept(STAR);
                        parseBlock();
                        accept(BLACKHOLE);
                        break;
                case ORBIT:
                    accept(ORBIT);
                    accept(LEFTPARAN);
                    accept(IDENTIFIER);
                    accept(COMMA);
                    accept(IDENTIFIER);
                    accept(RIGHTPARAN);
                    accept(LEFTKEY);
                    parseBlock();
                    accept(RIGHTKEY);
                    accept(SEMICOLON);
                    break;
                case IDENTIFIER:
                        parseExpression();
                        accept(SEMICOLON);
                        break;
                case SPECTRUM:
                    accept(SPECTRUM);
                    accept(LEFTPARAN);
                    parsePrimary();
                    while (currentTerminal.kind == COMMA) {
                        accept(COMMA);
                        parsePrimary();
                    }
                    accept(RIGHTPARAN);
                    break;
            }
    }

    private void parseExpression() {
        parsePrimary();
        while (currentTerminal.kind == OPERATOR) {
            accept(OPERATOR);
            parsePrimary();
        }
    }

    private void parsePrimary(){
        if (currentTerminal.kind == OPERATOR){
            accept(OPERATOR);
            parsePrimary();
        }
        if (currentTerminal.kind == IDENTIFIER){
            accept(IDENTIFIER);
        }
        if (currentTerminal.kind == COMETLITERAL){
            accept(COMETLITERAL);
        }
        if (currentTerminal.kind == QUOTE){
            accept(QUOTE);
            accept(IDENTIFIER);
            accept(QUOTE);
        }
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
