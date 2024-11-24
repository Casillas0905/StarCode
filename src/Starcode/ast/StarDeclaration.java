package Starcode.ast;

public class StarDeclaration extends OneDeclaration
{
    public Identifier identifier;
    public boolean isArray;

    public StarDeclaration(Identifier identifier, boolean isArray)
    {
        this.identifier = identifier;
        this.isArray = isArray;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitStarDeclaration( this, arg );
    }
}
