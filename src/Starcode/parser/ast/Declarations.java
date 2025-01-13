package Starcode.parser.ast;

import Starcode.core.IVisitor;

import java.util.Vector;

/**
 * The Declarations class represents a collection of variable or object declarations
 * in the abstract syntax tree (AST). It serves as a container for multiple declarations.
 */
public class Declarations extends AST {

    /**
     * A vector containing the individual declarations.
     * Each element in the vector is an instance of OneDeclaration or its subclass.
     */
    public Vector<OneDeclaration> declarations;

    /**
     * Constructs a Declarations instance with the specified vector of declarations.
     *
     * @param declarations A vector containing the individual declarations.
     */
    public Declarations(Vector<OneDeclaration> declarations) {
        this.declarations = declarations;
    }

    /**
     * Accepts a visitor for processing this Declarations element as part of the Visitor pattern.
     * This enables operations on the collection of declarations without modifying their structure.
     *
     * @param v The visitor instance responsible for processing this Declarations object.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitDeclarations(this, arg);
    }
}