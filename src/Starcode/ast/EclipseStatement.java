package Starcode.ast;

public class EclipseStatement extends OneStatement
{
    public Expression expression;
    public Block block;

    public EclipseStatement(Expression expression, Block block)
    {
        this.expression = expression;
        this.block = block;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitEclipseStatement( this, arg );
    }
}
