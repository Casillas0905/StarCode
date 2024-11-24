package Starcode.ast;

public class Program extends AST
{
    public ProgramBlock block;

    public Program(ProgramBlock block)
    {
        this.block = block;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitProgram( this, arg );
    }
}
