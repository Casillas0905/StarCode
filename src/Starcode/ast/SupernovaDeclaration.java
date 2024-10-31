package Starcode.ast;

public class SupernovaDeclaration extends OneDeclaration
{
    public ReturnType returnType;
    public Identifier identifier;
    public IdList idList;
    public SupernovaBlock supernovaBlock;

    public SupernovaDeclaration(ReturnType returnType, Identifier identifier, IdList idList, SupernovaBlock supernovaBlock)
    {
        this.returnType = returnType;
        this.identifier = identifier;
        this.idList = idList;
        this.supernovaBlock = supernovaBlock;
    }
}
