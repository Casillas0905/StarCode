package Starcode.parser.ast;

import Starcode.core.IVisitor;

/**
 * The SupernovaBlock class represents a block of code in the abstract syntax tree (AST)
 * that contains a series of statements followed by an optional return statement.
 * It is typically used to encapsulate logic and a return value in a scoped block.
 */
public class SupernovaBlock extends AST {

    /**
     * The sequence of statements contained within the block.
     */
    public Statements statements;

    /**
     * The return statement for the block, specifying a value to be returned.
     * This can be null if no return statement is present.
     */
    public ReturnStatement returnStatement;

    /**
     * Constructs a SupernovaBlock instance with the specified statements and return statement.
     *
     * @param statements The sequence of statements contained within the block.
     * @param returnStatement The return statement for the block, or null if not present.
     */
    public SupernovaBlock(Statements statements, ReturnStatement returnStatement) {
        this.statements = statements;
        this.returnStatement = returnStatement;
    }

    /**
     * Accepts a visitor for processing this SupernovaBlock element as part of the Visitor pattern.
     * This allows external operations to be performed on the block without modifying its structure.
     *
     * @param v The visitor instance responsible for processing this SupernovaBlock.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitSupernovaBlock(this, arg);
    }
}