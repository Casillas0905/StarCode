package Starcode.ast;

public abstract class AST
{
    public abstract Object visit(IVisitor v, Object arg);
}
