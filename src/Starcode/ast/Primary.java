package Starcode.ast;

public class Primary extends AST
{
    public Identifier identifier;
    public CometLiteral cometLiteral;
    public StarLiteral starLiteral;
    public ArrayAccess arrayAccess;

    public Primary(Identifier identifier)
    {
        this.identifier = identifier;
    }

    public Primary(ArrayAccess arrayAccess) {
        this.arrayAccess = arrayAccess;
    }

    public Primary(CometLiteral cometLiteral)
    {
        this.cometLiteral = cometLiteral;
    }

    public Primary(StarLiteral starLiteral)
    {
        this.starLiteral = starLiteral;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitPrimary( this, arg );
    }
}

