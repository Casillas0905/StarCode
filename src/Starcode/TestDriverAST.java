package Starcode;


import Starcode.ast.AST;
import Starcode.ast.Program;

import javax.swing.*;

public class TestDriverAST
{
	private static final String EXAMPLES_DIR = "c:\\usr\\undervisning\\CMC\\IntLang\\examples";

	public static void main(String args[])
	{
		JFileChooser fc = new JFileChooser(EXAMPLES_DIR);
		
		if(fc.showOpenDialog(null) == fc.APPROVE_OPTION)
		{
			SourceFile in = new SourceFile(fc.getSelectedFile().getAbsolutePath());
			Scanner s = new Scanner(in);
			ParserAST p = new ParserAST(s);
			SemanticVisitor v = new SemanticVisitor();
		
			AST ast = p.parseProgram();
			//v.visitProgram((Program)ast, null);
		}
	}
}