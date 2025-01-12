package Starcode.parser.ast;

import Starcode.core.IVisitor;

public class ExpressionStatement extends OneStatement
{
    public Expression expression;

    public ExpressionStatement(Expression expression)
    {
        this.expression = expression;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitExpressionStatement( this, arg );
    }
}
