package Starcode.ast;

public class ArrayAccess {

    public Identifier identifier;
    public CometLiteral cometLiteral;

    public ArrayAccess(Identifier identifier, CometLiteral cometLiteral) {
        this.identifier = identifier;
        this.cometLiteral = cometLiteral;
    }
}
