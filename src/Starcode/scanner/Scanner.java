package Starcode.scanner;

import static Starcode.scanner.TokenKind.*;

public class Scanner
{
	private SourceFile source;
	
	private char currentChar;
	private StringBuffer currentSpelling = new StringBuffer();

	public Scanner(SourceFile source)
	{
		this.source = source;
		
		currentChar = source.getSource();
	}

	public Token scan()
	{
		while (currentChar == '\n' || currentChar == '\r' || currentChar == '\t' || currentChar == ' ')
			scanSeparator();

		currentSpelling = new StringBuffer("");
		TokenKind kind = scanToken();

		return new Token( kind, new String( currentSpelling ) );
	}

	// This is example of code
	star

	private TokenKind scanToken()
	{
		if(isLetter(currentChar))
		{
			takeIt();
			while(isLetter(currentChar) || isDigit(currentChar))
				takeIt();

			return IDENTIFIER;
		}
		else if(isDigit(currentChar))
		{
			takeIt();
			while(isDigit(currentChar))
				takeIt();

			return COMETLITERAL;
		}

		switch(currentChar)
		{
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
				return EOT;
			default:
				takeIt();
				return ERROR;
		}
	}

	private void scanSeparator()
	{
		switch(currentChar)
		{
			case ' ': case '\n': case '\r': case '\t':
			takeIt();
			break;
		}
	}

	private void takeIt()
	{
		currentSpelling.append(currentChar);
		currentChar = source.getSource();
	}

	private boolean isLetter(char c)
	{
		return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
	}
	
	private boolean isDigit(char c)
	{
		return c >= '0' && c <= '9';
	}
}
