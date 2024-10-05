package Starcode;

public enum TokenKind
{
	IDENTIFIER,
	COMETLITERAL,
	OPERATOR,
	STAR,
	COMMET,
	STRINGLITERAL,

	SUPERNOVA ( "supernova" ),
	RETURN( "return" ),
	ORBIT( "orbit" ),
	ECLIPSE( "eclipse" ),
	SHINE( "shine" ),
	SPECTRUM( "spectrum" ),
	BLACKHOLE( "blackhole" ),
	
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
	EOT,
	
	ERROR;
	
	
	private String spelling = null;
	
	
	private TokenKind()
	{
	}
	
	
	private TokenKind( String spelling )
	{
		this.spelling = spelling;
	}
	
	
	public String getSpelling()
	{
		return spelling;
	}
}