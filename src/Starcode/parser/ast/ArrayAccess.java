package Starcode.parser.ast;

import Starcode.core.IVisitor;

/**
 * The ArrayAccess class represents an array access operation within the program.
 * It encapsulates an identifier for the array and a literal value (index) for the access.
 */
public class ArrayAccess {

    /**
     * The identifier representing the array being accessed.
     */
    public Identifier identifier;

    /**
     * The literal value representing the index for the array access.
     */
    public CometLiteral cometLiteral;

    /**
     * Constructs an ArrayAccess instance with the specified identifier and literal index.
     * @param identifier The identifier for the array.
     * @param cometLiteral The literal index for accessing the array.
     */
    public ArrayAccess(Identifier identifier, CometLiteral cometLiteral) {
        this.identifier = identifier;
        this.cometLiteral = cometLiteral;
    }

    /**
     * Accepts a visitor for processing this ArrayAccess element as part of the Visitor pattern.
     * @param v The visitor instance.
     * @param arg Additional argument for processing.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitArrayAccess(this, arg);
    }
}
