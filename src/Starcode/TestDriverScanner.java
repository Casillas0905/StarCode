package Starcode;

import javax.swing.*;

 
public class TestDriverScanner
{
	private static final String EXAMPLES_DIR = "C:\\Users\\ikers\\Downloads\\IntLangExamples";
	
	
	public static void main( String args[] )
	{
		JFileChooser fc = new JFileChooser( EXAMPLES_DIR );
		
		if( fc.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ) {
			SourceFile in = new SourceFile( fc.getSelectedFile().getAbsolutePath() );
			Scanner s = new Scanner( in );
		
			Token t = s.scan();
			while( t.kind != TokenKind.EOT ) {
				System.out.println( t.kind + " " + t.spelling );
			
				t = s.scan();
			}
		}
	}
}