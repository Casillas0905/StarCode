package Starcode.ast;

public class CometDeclaration extends OneDeclaration
{
    public Identifier identifier;
    public boolean isArray;

    public CometDeclaration(Identifier identifier, boolean isArray)
    {
        this.identifier = identifier;
        this.isArray = isArray;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitCometDeclaration( this, arg );
    }
}
