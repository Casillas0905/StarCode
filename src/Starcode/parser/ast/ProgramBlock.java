package Starcode.parser.ast;

import Starcode.core.IVisitor;

public class ProgramBlock extends AST
{
    public Declarations declarations;
    public SupernovaStatement statement;

    public ProgramBlock(Declarations declarations, SupernovaStatement statement)
    {
        this.declarations = declarations;
        this.statement = statement;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitProgramBlock( this, arg );
    }
}
