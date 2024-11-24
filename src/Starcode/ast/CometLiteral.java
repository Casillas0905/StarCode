package Starcode.ast;

public class CometLiteral extends Terminal
{
    public CometLiteral(String spelling)
    {
        this.spelling = spelling;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitCometLiteral( this, arg );
    }
}
