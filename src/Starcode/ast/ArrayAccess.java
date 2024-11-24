package Starcode.ast;

public class ArrayAccess {

    public Identifier identifier;
    public CometLiteral cometLiteral;

    public ArrayAccess(Identifier identifier, CometLiteral cometLiteral) {
        this.identifier = identifier;
        this.cometLiteral = cometLiteral;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitArrayAccess( this, arg );
    }
}
