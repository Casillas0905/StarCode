package Starcode.parser.ast;

import Starcode.core.IVisitor;

/**
 * The Block class represents a block of code in the abstract syntax tree (AST).
 * A block is a container for a sequence of statements that are executed sequentially.
 */
public class Block extends AST {

    /**
     * The statements contained within this block.
     */
    public Statements statements;

    /**
     * Constructs a Block instance with the specified statements.
     * @param statements The sequence of statements that belong to this block.
     */
    public Block(Statements statements) {
        this.statements = statements;
    }

    /**
     * Accepts a visitor for processing this Block element as part of the Visitor pattern.
     * This allows external classes to perform operations on this Block without
     * changing its structure.
     *
     * @param v The visitor instance responsible for processing this Block.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitBlock(this, arg);
    }
}
