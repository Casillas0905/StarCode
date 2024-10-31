package Starcode.ast;

class Primary extends AST
{
    public Identifier identifier;
    public CometLiteral cometLiteral;
    public StarLiteral starLiteral;

    public Primary(Identifier identifier)
    {
        this.identifier = identifier;
    }

    public Primary(CometLiteral cometLiteral)
    {
        this.cometLiteral = cometLiteral;
    }

    public Primary(StarLiteral starLiteral)
    {
        this.starLiteral = starLiteral;
    }
}

