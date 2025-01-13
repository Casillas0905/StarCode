package Starcode.parser.ast;

import Starcode.core.IVisitor;

import java.util.Vector;

/**
 * The IdList class represents a list of identifiers, typically used to define the names of parameters
 * in the abstract syntax tree (AST). It also includes a flag indicating whether the identifiers represent arrays.
 */
public class IdList extends AST {

    /**
     * A vector containing the identifiers that make up the list.
     * Each identifier represents the name of a parameter.
     */
    public Vector<Identifier> identifiers;

    /**
     * A flag indicating whether the identifiers in the list represent arrays.
     * If true, all identifiers in the list are considered arrays; otherwise, they are scalar variables.
     */
    public boolean areArrays;

    /**
     * Constructs an IdList instance with the specified identifiers and array flag.
     *
     * @param identifiers A vector of identifiers representing parameter names.
     * @param areArrays A flag indicating whether the identifiers represent arrays.
     */
    public IdList(Vector<Identifier> identifiers, boolean areArrays) {
        this.identifiers = identifiers;
        this.areArrays = areArrays;
    }

    /**
     * Accepts a visitor for processing this IdList element as part of the Visitor pattern.
     * This allows external operations to be performed on the list without altering its structure.
     *
     * @param v The visitor instance responsible for processing this IdList.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitIdList(this, arg);
    }
}