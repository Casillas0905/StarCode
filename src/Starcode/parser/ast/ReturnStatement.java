package Starcode.parser.ast;

import Starcode.core.IVisitor;

public class ReturnStatement extends OneStatement
{
    public Identifier identifier;

    public ReturnStatement(Identifier identifier)
    {
        this.identifier = identifier;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitReturnStatement( this, arg );
    }
}
