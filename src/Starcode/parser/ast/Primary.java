package Starcode.parser.ast;

import Starcode.core.IVisitor;

/**
 * The Primary class represents a fundamental unit of an expression in the abstract syntax tree (AST).
 * A primary can be an identifier, a literal value (comet or star), or an array access.
 */
public class Primary extends AST {

    /**
     * The identifier associated with this primary, if applicable.
     */
    public Identifier identifier;

    /**
     * The comet literal value associated with this primary, if applicable.
     */
    public CometLiteral cometLiteral;

    /**
     * The star literal value associated with this primary, if applicable.
     */
    public StarLiteral starLiteral;

    /**
     * The array access associated with this primary, if applicable.
     */
    public ArrayAccess arrayAccess;

    /**
     * Constructs a Primary instance with an identifier.
     *
     * @param identifier The identifier representing this primary.
     */
    public Primary(Identifier identifier) {
        this.identifier = identifier;
    }

    /**
     * Constructs a Primary instance with an array access.
     *
     * @param arrayAccess The array access representing this primary.
     */
    public Primary(ArrayAccess arrayAccess) {
        this.arrayAccess = arrayAccess;
    }

    /**
     * Constructs a Primary instance with a comet literal.
     *
     * @param cometLiteral The comet literal representing this primary.
     */
    public Primary(CometLiteral cometLiteral) {
        this.cometLiteral = cometLiteral;
    }

    /**
     * Constructs a Primary instance with a star literal.
     *
     * @param starLiteral The star literal representing this primary.
     */
    public Primary(StarLiteral starLiteral) {
        this.starLiteral = starLiteral;
    }

    /**
     * Accepts a visitor for processing this Primary element as part of the Visitor pattern.
     * This allows external operations to be performed on the primary without modifying its structure.
     *
     * @param v The visitor instance responsible for processing this Primary.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitPrimary(this, arg);
    }
}