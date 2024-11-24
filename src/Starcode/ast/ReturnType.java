package Starcode.ast;

public class ReturnType extends AST
{
    public String type;
    public boolean isArray;

    public ReturnType(String type, boolean isArray)
    {
        this.type = type;
        this.isArray = isArray;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitReturnType( this, arg );
    }
}
