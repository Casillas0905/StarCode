package Starcode.scanner;

import static Starcode.scanner.TokenKind.*;

/**
 * The Scanner class is responsible for lexical analysis.
 * It reads characters from the source file and groups them into tokens.
 */
public class Scanner {

	private SourceFile source;  // The source file being scanned
	private char currentChar;  // The current character being processed
	private StringBuffer currentSpelling = new StringBuffer();  // The spelling of the current token

	/**
	 * Constructs a Scanner with the given source file.
	 *
	 * @param source The source file to scan.
	 */
	public Scanner(SourceFile source) {
		this.source = source;
		currentChar = source.getSource(); // Initialize the first character
	}

	/**
	 * Scans the next token from the source file.
	 *
	 * @return The next token found in the source file.
	 */
	public Token scan() {
		// Skip separators (whitespace and control characters)
		while (currentChar == '\n' || currentChar == '\r' || currentChar == '\t' || currentChar == ' ')
			scanSeparator();

		// Reset the current spelling and determine the token kind
		currentSpelling = new StringBuffer("");
		TokenKind kind = scanToken();

		// Create and return a new Token
		return new Token(kind, new String(currentSpelling));
	}

	/**
	 * Determines the kind of the current token and consumes the characters.
	 *
	 * @return The TokenKind of the scanned token.
	 */
	private TokenKind scanToken() {
		if (isLetter(currentChar)) {
			// Handle identifiers
			takeIt();
			while (isLetter(currentChar) || isDigit(currentChar))
				takeIt();
			return IDENTIFIER;
		} else if (isDigit(currentChar)) {
			// Handle numeric literals
			takeIt();
			while (isDigit(currentChar))
				takeIt();
			return COMETLITERAL;
		}

		// Handle specific symbols and operators
		switch (currentChar) {
			case '+': case '-': case '*': case '/':
				takeIt();
				return OPERATOR;
			case ',':
				takeIt();
				return COMMA;
			case '=':
				takeIt();
				return OPERATOR;
			case ';':
				takeIt();
				return SEMICOLON;
			case '(':
				takeIt();
				return LEFTPARAN;
			case ')':
				takeIt();
				return RIGHTPARAN;
			case '{':
				takeIt();
				return LEFTKEY;
			case '\"':
				takeIt();
				return QUOTE;
			case '}':
				takeIt();
				return RIGHTKEY;
			case '~':
				takeIt();
				return TILDE;
			case ']':
				takeIt();
				return RIGHTBRACKET;
			case '[':
				takeIt();
				return LEFTBRACKET;
			case '<':
				takeIt();
				return LT;
			case '>':
				takeIt();
				return GT;
			case SourceFile.EOT:
				return EOT; // End of the source file
			default:
				takeIt();
				return ERROR; // Unrecognized character
		}
	}

	/**
	 * Skips separators such as whitespace and control characters.
	 */
	private void scanSeparator() {
		switch (currentChar) {
			case ' ': case '\n': case '\r': case '\t':
				takeIt();
				break;
		}
	}

	/**
	 * Consumes the current character, appends it to the current spelling,
	 * and advances to the next character.
	 */
	private void takeIt() {
		currentSpelling.append(currentChar);
		currentChar = source.getSource(); // Advance to the next character
	}

	/**
	 * Checks if the given character is a letter.
	 *
	 * @param c The character to check.
	 * @return True if the character is a letter; false otherwise.
	 */
	private boolean isLetter(char c) {
		return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
	}

	/**
	 * Checks if the given character is a digit.
	 *
	 * @param c The character to check.
	 * @return True if the character is a digit; false otherwise.
	 */
	private boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}
}