package Starcode.scanner;

import static Starcode.scanner.TokenKind.*;

/**
 * The Token class represents a lexical token.
 * It encapsulates the kind of the token, its spelling, and provides utility methods for token classification.
 */
public class Token {

	public TokenKind kind;      // The kind of the token (e.g., IDENTIFIER, OPERATOR)
	public String spelling;     // The text representation of the token

	// Keywords in the language
	private static final TokenKind[] KEYWORDS = {
			RETURN, SUPERNOVA, ORBIT, ECLIPSE, SHINE, BLACKHOLE, COMMET, STAR, WHITEHOLE, EXPLODE
	};

	// Operators categorized into assignment, addition, and multiplication
	private static final String[] ASSIGNOPS = { "=" };
	private static final String[] ADDOPS = { "+", "-" };
	private static final String[] MULOPS = { "*", "/" };

	/**
	 * Constructs a Token with the specified kind and spelling.
	 * If the token is an IDENTIFIER, its kind is updated to a keyword if the spelling matches a keyword.
	 *
	 * @param kind The kind of the token.
	 * @param spelling The spelling of the token.
	 */
	public Token(TokenKind kind, String spelling) {
		this.kind = kind;
		this.spelling = spelling;

		// Check if the token is an identifier and matches a keyword
		if (kind != IDENTIFIER) {
			return; // If not an identifier, no further processing is required
		}

		// Match spelling to keywords and update the token kind if a match is found
		for (TokenKind tk : KEYWORDS) {
			if (spelling.equals(tk.getSpelling())) {
				this.kind = tk; // Update to the specific keyword kind
				break;
			}
		}
	}

	/**
	 * Checks if the token is an assignment operator.
	 *
	 * @return True if the token is an assignment operator, false otherwise.
	 */
	public boolean isAssignOperator() {
		if (kind == OPERATOR) {
			return containsOperator(spelling, ASSIGNOPS);
		} else {
			return false;
		}
	}

	/**
	 * Checks if the token is an addition operator.
	 *
	 * @return True if the token is an addition operator, false otherwise.
	 */
	public boolean isAddOperator() {
		if (kind == OPERATOR) {
			return containsOperator(spelling, ADDOPS);
		} else {
			return false;
		}
	}

	/**
	 * Checks if the token is a multiplication operator.
	 *
	 * @return True if the token is a multiplication operator, false otherwise.
	 */
	public boolean isMulOperator() {
		if (kind == OPERATOR) {
			return containsOperator(spelling, MULOPS);
		} else {
			return false;
		}
	}

	/**
	 * Checks if the given spelling matches any operator in the specified list.
	 *
	 * @param spelling The spelling to check.
	 * @param OPS The list of operator strings to match against.
	 * @return True if the spelling matches an operator, false otherwise.
	 */
	private boolean containsOperator(String spelling, String[] OPS) {
		for (int i = 0; i < OPS.length; ++i) {
			if (spelling.equals(OPS[i])) {
				return true; // Match found
			}
		}
		return false; // No match found
	}
}
