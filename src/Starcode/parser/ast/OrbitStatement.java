package Starcode.parser.ast;

import Starcode.core.IVisitor;

/**
 * The OrbitStatement class represents a loop construct in the abstract syntax tree (AST).
 * It is used to iterate over a block of code a specific number of times, using an incremental identifier
 * and a count identifier to manage the iteration.
 */
public class OrbitStatement extends OneStatement {

    /**
     * The identifier used to track the current iteration value.
     */
    public Identifier incrementalIdentifier;

    /**
     * The identifier representing the total count of iterations.
     */
    public Identifier countIdentifier;

    /**
     * The block of code to be executed during each iteration of the loop.
     */
    public Block block;

    /**
     * Constructs an OrbitStatement instance with the specified incremental identifier,
     * count identifier, and block of code.
     *
     * @param incrementalIdentifier The identifier that tracks the current iteration value.
     * @param countIdentifier The identifier representing the total number of iterations.
     * @param block The block of code to execute during each iteration.
     */
    public OrbitStatement(Identifier incrementalIdentifier, Identifier countIdentifier, Block block) {
        this.incrementalIdentifier = incrementalIdentifier;
        this.countIdentifier = countIdentifier;
        this.block = block;
    }

    /**
     * Accepts a visitor for processing this OrbitStatement element as part of the Visitor pattern.
     * This allows external operations to be performed on the loop statement without modifying its structure.
     *
     * @param v The visitor instance responsible for processing this OrbitStatement.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitOrbitStatement(this, arg);
    }
}