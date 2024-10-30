package Starcode;

public enum TokenKind
{
	// Core
	IDENTIFIER,
	STARLITERAL,
	COMETLITERAL,
	OPERATOR,

	// Keywords
	STAR( "star" ),
	COMMET( "commet" ),
	SUPERNOVA ( "supernova" ),
	RETURN( "return" ),
	EXPLODE( "explode" ),
	ORBIT( "orbit" ),
	ECLIPSE( "eclipse" ),
	SHINE( "shine" ),
	BLACKHOLE( "blackhole" ),
	WHITEHOLE("whitehole"),

	// Signs
	COMMA( "," ),
	SEMICOLON( ";" ),
	LEFTPARAN( "(" ),
	RIGHTPARAN( ")" ),
	LEFTKEY("{"),
	RIGHTKEY( "}" ),
	QUOTE("\""),
	TILDE("~"),
	RIGHTBRACKET("]"),
	LEFTBRACKET("["),

	// Miscellaneous
	EOT,
	ERROR;

	private String spelling = null;

	private TokenKind()
	{

	}

	private TokenKind(String spelling)
	{
		this.spelling = spelling;
	}

	public String getSpelling()
	{
		return spelling;
	}
}