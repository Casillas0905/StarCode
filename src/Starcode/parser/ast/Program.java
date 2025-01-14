package Starcode.parser.ast;

import Starcode.core.IVisitor;

public class Program extends AST
{
    public ProgramBlock block;

    public Program(ProgramBlock block)
    {
        this.block = block;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitProgram( this, arg );
    }
}
