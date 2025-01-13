package Starcode.parser.ast;

import Starcode.core.IVisitor;

/**
 * The CometLiteral class represents a literal value in the abstract syntax tree (AST).
 * A literal is a constant value directly specified in the source code -> number
 */
public class CometLiteral extends Terminal {

    /**
     * Constructs a CometLiteral instance with the specified spelling.
     * The spelling represents the literal's value as it appears in the source code.
     *
     * @param spelling The string representation of the literal value.
     */
    public CometLiteral(String spelling) {
        this.spelling = spelling;
    }

    /**
     * Accepts a visitor for processing this CometLiteral element as part of the Visitor pattern.
     * This allows operations to be performed on the literal without altering its structure.
     *
     * @param v The visitor instance responsible for processing this CometLiteral.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitCometLiteral(this, arg);
    }
}