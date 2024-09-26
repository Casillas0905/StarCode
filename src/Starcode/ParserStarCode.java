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

    private void accept( TokenKind expected )
    {
        if( currentTerminal.kind == expected )
            currentTerminal = scan.scan();
        else
            System.out.println( "Expected token of kind " + expected );
    }
}
