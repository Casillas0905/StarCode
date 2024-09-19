/*
 * 23.08.2019 Original version
 */


public enum TokenKind
{
	IDENTIFIER,
	INTEGERLITERAL,
	OPERATOR,
	
	DECLARE( "declare" ),
	DO( "do" ),
	ELSE( "else" ),
	FI( "fi" ),
	FUNC( "func" ),
	IF( "if" ),
	OD( "od" ),
	RETURN( "return" ),
	SAY( "say" ),
	THEN( "then" ),
	VAR( "var" ),
	WHILE( "while" ),
	
	COMMA( "," ),
	SEMICOLON( ";" ),
	LEFTPARAN( "(" ),
	RIGHTPARAN( ")" ),
	
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