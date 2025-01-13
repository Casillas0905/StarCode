package Starcode.parser.ast;

import Starcode.core.IVisitor;

/**
 * The ReturnStatement class represents a return statement in the abstract syntax tree (AST).
 * A return statement specifies a value (identified by an identifier) to be returned from a function or block.
 */
public class ReturnStatement extends OneStatement {

    /**
     * The identifier representing the value to be returned.
     */
    public Identifier identifier;

    /**
     * Constructs a ReturnStatement instance with the specified identifier.
     *
     * @param identifier The identifier of the value to be returned.
     */
    public ReturnStatement(Identifier identifier) {
        this.identifier = identifier;
    }

    /**
     * Accepts a visitor for processing this ReturnStatement element as part of the Visitor pattern.
     * This allows external operations to be performed on the statement without modifying its structure.
     *
     * @param v The visitor instance responsible for processing this ReturnStatement.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitReturnStatement(this, arg);
    }
}