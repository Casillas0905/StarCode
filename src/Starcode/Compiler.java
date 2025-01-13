package Starcode;


import Starcode.parser.Parser;
import Starcode.parser.ast.AST;
import Starcode.parser.ast.Program;
import Starcode.runtime_gen.EncoderStarCode;
import Starcode.scanner.Scanner;
import Starcode.scanner.SourceFile;
import Starcode.semantics.SemanticVisitor;

import javax.swing.*;

/**
 * The Compiler class is responsible for compiling a source file.
 * It handles reading the source file, scanning, parsing, performing semantic analysis,
 * generating machine code, and saving the result to a `.tam` file.
 */
public class Compiler {

	// Directory where example files are located (currently empty)
	private static final String EXAMPLES_DIR = "";

	/**
	 * The main method serves as the entry point for the compiler.
	 * It opens a file dialog for selecting the source file, processes the file,
	 * performs semantic analysis, generates machine code, and saves the result to a `.tam` file.
	 *
	 * @param args Command-line arguments (unused in this implementation).
	 */
	public static void main(String args[]) {
		// Create a file chooser to select a source file
		JFileChooser fc = new JFileChooser(EXAMPLES_DIR);

		// If the user selects a file, proceed with compilation
		if (fc.showOpenDialog(null) == fc.APPROVE_OPTION) {
			// Get the selected file's absolute path
			String sourceName = fc.getSelectedFile().getAbsolutePath();

			// Initialize the source file, scanner, parser, semantic visitor, and encoder
			SourceFile in = new SourceFile(fc.getSelectedFile().getAbsolutePath());
			Scanner s = new Scanner(in);
			Parser p = new Parser(s);
			SemanticVisitor v = new SemanticVisitor();
			EncoderStarCode e = new EncoderStarCode();

			// Parse the source file into an AST and visit it with semantic checks and encoding
			AST ast = p.parseProgram();
			v.visitProgram((Program) ast, null);
			e.visitProgram((Program) ast, null);

			// Determine the target file name, replacing .txt extension with .tam
			String targetName;
			if (sourceName.endsWith(".txt"))
				targetName = sourceName.substring(0, sourceName.length() - 4) + ".tam";
			else
				targetName = sourceName + ".tam";

			// Save the generated machine code to the target file
			e.saveToFile(targetName);
		}
	}
}