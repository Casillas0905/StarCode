package Starcode.parser.ast;

import Starcode.core.IVisitor;

public class ArrayAccess {

    public Identifier identifier;
    public CometLiteral cometLiteral;

    public ArrayAccess(Identifier identifier, CometLiteral cometLiteral) {
        this.identifier = identifier;
        this.cometLiteral = cometLiteral;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitArrayAccess( this, arg );
    }
}
