package Starcode.parser.ast;

import Starcode.core.IVisitor;

/**
 * The StarLiteral class represents a literal value in the abstract syntax tree (AST)
 * associated with a "star" variable or construct. A literal is a constant value
 * directly specified in the source code.
 */
public class StarLiteral extends Terminal {

    /**
     * Constructs a StarLiteral instance with the specified spelling.
     *
     * @param spelling The string representation of the literal value.
     */
    public StarLiteral(String spelling) {
        this.spelling = spelling;
    }

    /**
     * Accepts a visitor for processing this StarLiteral element as part of the Visitor pattern.
     * This allows external operations to be performed on the literal without modifying its structure.
     *
     * @param v The visitor instance responsible for processing this StarLiteral.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitStarLiteral(this, arg);
    }
}