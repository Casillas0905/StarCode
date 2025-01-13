package Starcode.parser.ast;

import Starcode.core.IVisitor;

import java.util.Vector;

/**
 * The Statements class represents a collection of statements in the abstract syntax tree (AST).
 * It serves as a container for multiple statements that are executed sequentially.
 */
public class Statements extends AST {

    /**
     * A vector containing the individual statements.
     * Each statement is an instance of OneStatement or its subclass.
     */
    public Vector<OneStatement> statements;

    /**
     * Constructs a Statements instance with the specified vector of statements.
     *
     * @param statements A vector containing the individual statements to be executed.
     */
    public Statements(Vector<OneStatement> statements) {
        this.statements = statements;
    }

    /**
     * Accepts a visitor for processing this Statements element as part of the Visitor pattern.
     * This allows external operations to be performed on the collection of statements
     * without modifying their structure.
     *
     * @param v The visitor instance responsible for processing this Statements object.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitStatements(this, arg);
    }
}