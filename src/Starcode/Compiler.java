package Starcode;


import Starcode.parser.Parser;
import Starcode.parser.ast.AST;
import Starcode.parser.ast.Program;
import Starcode.runtime_gen.EncoderStarCode;
import Starcode.scanner.Scanner;
import Starcode.scanner.SourceFile;
import Starcode.semantics.SemanticVisitor;

import javax.swing.*;

public class Compiler
{
	private static final String EXAMPLES_DIR = "";

	public static void main(String args[])
	{
		JFileChooser fc = new JFileChooser(EXAMPLES_DIR);

		if(fc.showOpenDialog(null) == fc.APPROVE_OPTION)
		{
			String sourceName = fc.getSelectedFile().getAbsolutePath();

			SourceFile in = new SourceFile(fc.getSelectedFile().getAbsolutePath());
			Scanner s = new Scanner(in);
			Parser p = new Parser(s);
			SemanticVisitor v = new SemanticVisitor();
			EncoderStarCode e = new EncoderStarCode();
		
			AST ast = p.parseProgram();
			v.visitProgram((Program)ast, null);
			e.visitProgram((Program)ast, null);

			String targetName;
			if( sourceName.endsWith( ".txt" ) )
				targetName = sourceName.substring( 0, sourceName.length() - 4 ) + ".tam";
			else
				targetName = sourceName + ".tam";

			e.saveToFile(targetName);
		}
	}
}