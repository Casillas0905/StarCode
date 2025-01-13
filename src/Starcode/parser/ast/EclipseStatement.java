package Starcode.parser.ast;

import Starcode.core.IVisitor;

/**
 * The EclipseStatement class represents a conditional statement in the abstract syntax tree (AST).
 * It associates an expression with a block of code that executes if the expression evaluates to true.
 */
public class EclipseStatement extends OneStatement {

    /**
     * The expression that determines whether the block of code will be executed.
     */
    public Expression expression;

    /**
     * The block of code to be executed if the expression evaluates to true.
     */
    public Block block;

    /**
     * Constructs an EclipseStatement instance with the specified expression and block.
     *
     * @param expression The condition expression for the statement.
     * @param block The block of code to execute if the condition is true.
     */
    public EclipseStatement(Expression expression, Block block) {
        this.expression = expression;
        this.block = block;
    }

    /**
     * Accepts a visitor for processing this EclipseStatement element as part of the Visitor pattern.
     * This allows external operations to be performed on the statement without altering its structure.
     *
     * @param v The visitor instance responsible for processing this EclipseStatement.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitEclipseStatement(this, arg);
    }
}