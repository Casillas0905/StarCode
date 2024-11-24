package Starcode.ast;

public class Block extends AST
{
    public Statements statements;

    public Block(Statements statements)
    {
        this.statements = statements;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitBlock( this, arg );
    }
}
