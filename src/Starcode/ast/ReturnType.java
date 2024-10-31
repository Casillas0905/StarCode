package Starcode.ast;

public class ReturnType extends AST
{
    public String type;
    //TODO: Ask if this is a right approach !!
    public boolean isArray;

    public ReturnType(String type, boolean isArray)
    {
        this.type = type;
        this.isArray = isArray;
    }
}
