package Starcode.ast;

public class Operator extends Terminal
{
    public Operator(String spelling)
    {
        this.spelling = spelling;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitOperator( this, arg );
    }
}
