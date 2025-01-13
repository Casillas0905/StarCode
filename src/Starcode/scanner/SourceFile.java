package Starcode.scanner;

import java.io.*;

/**
 * The SourceFile class provides character-by-character access to a source file.
 * It reads the file and returns characters until the end of the file (EOT) is reached.
 */
public class SourceFile {

	public static final char EOT = 0; // End of text marker for the source file

	private FileInputStream source; // Input stream for reading the source file

	/**
	 * Constructs a SourceFile object for the specified file name.
	 *
	 * @param sourceFileName The name of the source file to open.
	 */
	public SourceFile(String sourceFileName) {
		try {
			source = new FileInputStream(sourceFileName); // Open the file
		} catch (FileNotFoundException ex) {
			System.out.println("*** FILE NOT FOUND *** (" + sourceFileName + ")");
			System.exit(1); // Terminate the program if the file is not found
		}
	}

	/**
	 * Retrieves the next character from the source file.
	 *
	 * @return The next character, or EOT if the end of the file is reached.
	 */
	public char getSource() {
		try {
			int c = source.read(); // Read the next byte from the file
			if (c < 0) {
				return EOT; // Return EOT if the end of the file is reached
			} else {
				return (char) c; // Convert the byte to a character and return it
			}
		} catch (IOException ex) {
			return EOT; // Return EOT in case of an I/O error
		}
	}
}
