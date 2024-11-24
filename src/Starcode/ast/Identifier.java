package Starcode.ast;

public class Identifier extends Terminal
{
    public Identifier(String spelling)
    {
        this.spelling = spelling;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitIdentifier( this, arg );
    }
}
