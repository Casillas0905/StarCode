package Starcode.ast;

public class SupernovaBlock extends AST
{
    public Statements statements;
    public ReturnStatement returnStatement;

    public SupernovaBlock(Statements statements, ReturnStatement returnStatement)
    {
        this.statements = statements;
        this.returnStatement = returnStatement;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitSupernovaBlock( this, arg );
    }
}
