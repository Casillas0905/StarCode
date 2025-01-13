package Starcode.parser.ast;

import Starcode.core.IVisitor;

/**
 * The ExpressionStatement class represents a statement that consists of a single expression
 * in the abstract syntax tree (AST). This is often used for expressions that produce side effects,
 * such as method calls or assignments.
 */
public class ExpressionStatement extends OneStatement {

    /**
     * The expression that constitutes the statement.
     */
    public Expression expression;

    /**
     * Constructs an ExpressionStatement instance with the specified expression.
     *
     * @param expression The expression that forms the statement.
     */
    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    /**
     * Accepts a visitor for processing this ExpressionStatement element as part of the Visitor pattern.
     * This allows external operations to be performed on the statement without modifying its structure.
     *
     * @param v The visitor instance responsible for processing this ExpressionStatement.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitExpressionStatement(this, arg);
    }
}