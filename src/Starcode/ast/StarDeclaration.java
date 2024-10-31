package Starcode.ast;

public class StarDeclaration extends OneDeclaration
{
    public Identifier identifier;
    //TODO: Ask if this is a right approach !!
    public boolean isArray;

    public StarDeclaration(Identifier identifier, boolean isArray)
    {
        this.identifier = identifier;
        this.isArray = isArray;
    }
}
