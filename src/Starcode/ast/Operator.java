package Starcode.ast;

public class Operator extends Terminal
{
    public Operator(String spelling)
    {
        this.spelling = spelling;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitOperator( this, arg );
    }
}
