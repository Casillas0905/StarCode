package Starcode.ast;

public class CometDeclaration extends OneDeclaration
{
    public Identifier identifier;
    //TODO: Ask if this is a right approach !!
    public boolean isArray;

    public CometDeclaration(Identifier identifier, boolean isArray)
    {
        this.identifier = identifier;
        this.isArray = isArray;
    }
}
