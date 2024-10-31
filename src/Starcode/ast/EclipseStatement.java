package Starcode.ast;

public class EclipseStatement extends AST
{
    public Expression expression;
    public Block block;

    public EclipseStatement(Expression expression, Block block)
    {
        this.expression = expression;
        this.block = block;
    }
}
