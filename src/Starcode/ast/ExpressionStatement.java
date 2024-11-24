package Starcode.ast;

public class ExpressionStatement extends OneStatement
{
    public Expression expression;

    public ExpressionStatement(Expression expression)
    {
        this.expression = expression;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitExpressionStatement( this, arg );
    }
}
