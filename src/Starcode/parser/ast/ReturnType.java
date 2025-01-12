package Starcode.parser.ast;

import Starcode.core.IVisitor;

public class ReturnType extends AST
{
    public String type;
    public boolean isArray;

    public ReturnType(String type, boolean isArray)
    {
        this.type = type;
        this.isArray = isArray;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitReturnType( this, arg );
    }
}
