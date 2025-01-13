package Starcode.parser.ast;

import Starcode.core.IVisitor;

import java.util.Vector;

/**
 * The Expression class represents an expression in the abstract syntax tree (AST).
 * An expression can consist of a primary value, a sequence of operators, and additional primary values,
 * defining complex computations or evaluations.
 */
public class Expression extends AST {
    /**
     * The primary value at the root of the expression.
     */
    public Primary primary;

    /**
     * A vector containing additional primary values used in the expression.
     * These are typically operands for the operators in the expression.
     */
    public Vector<Primary> primaries;

    /**
     * A vector containing the operators used in the expression.
     * These define the operations to be performed on the primary values.
     */
    public Vector<Operator> operators;

    /**
     * Constructs an Expression instance with the specified primary, additional primaries, and operators.
     *
     * @param primary The primary value at the root of the expression.
     * @param primaries A vector of additional primary values used as operands in the expression.
     * @param operators A vector of operators defining the operations in the expression.
     */
    public Expression(Primary primary, Vector<Primary> primaries, Vector<Operator> operators) {
        this.primary = primary;
        this.primaries = primaries;
        this.operators = operators;
    }

    /**
     * Accepts a visitor for processing this Expression element as part of the Visitor pattern.
     * This allows external operations to be performed on the expression without modifying its structure.
     *
     * @param v The visitor instance responsible for processing this Expression.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitExpression(this, arg);
    }
}