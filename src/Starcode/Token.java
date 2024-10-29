package Starcode;

import static Starcode.TokenKind.*;

public class Token
{
	public TokenKind kind;
	public String spelling;

	private static final TokenKind[] KEYWORDS = { RETURN, SUPERNOVA, ORBIT, ECLIPSE, SHINE, SPECTRUM, BLACKHOLE, COMMET, STAR };
	private static final String ASSIGNOPS[] = { "=" };
	private static final String ADDOPS[] =	{ "+", "-" };
	private static final String MULOPS[] = { "*", "/" };

	public Token(TokenKind kind, String spelling)
	{
		this.kind = kind;
		this.spelling = spelling;

		if(kind != IDENTIFIER)
		{
			return;
		}

		for(TokenKind tk: KEYWORDS)
		{
			if(spelling.equals(tk.getSpelling()))
			{
				this.kind = tk;
				break;
			}
		}
	}

	public boolean isAssignOperator()
	{
		if(kind == OPERATOR)
			return containsOperator(spelling, ASSIGNOPS);
		else
			return false;
	}
	
	public boolean isAddOperator()
	{
		if(kind == OPERATOR)
			return containsOperator(spelling, ADDOPS );
		else
			return false;
	}
	
	public boolean isMulOperator()
	{
		if(kind == OPERATOR)
			return containsOperator(spelling, MULOPS);
		else
			return false;
	}
	
	
	private boolean containsOperator(String spelling, String OPS[])
	{
		for( int i = 0; i < OPS.length; ++i )
			if( spelling.equals( OPS[i] ) )
				return true;

		return false;
	}
}