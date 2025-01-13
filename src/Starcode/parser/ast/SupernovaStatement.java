package Starcode.parser.ast;

import Starcode.core.IVisitor;

import java.util.Vector;

/**
 * The SupernovaStatement class represents a function or method call statement in the abstract syntax tree (AST).
 * It includes the function or method's name and the list of arguments passed during the call.
 */
public class SupernovaStatement extends OneStatement {

    /**
     * The identifier representing the name of the function or method being called.
     */
    public Identifier identifier;

    /**
     * A vector containing the arguments (as Primary objects) passed to the function or method.
     */
    public Vector<Primary> parameters;

    /**
     * Constructs a SupernovaStatement instance with the specified function or method name and arguments.
     *
     * @param identifier The name of the function or method being called.
     * @param parameters A vector of arguments passed to the function or method.
     */
    public SupernovaStatement(Identifier identifier, Vector<Primary> parameters) {
        this.identifier = identifier;
        this.parameters = parameters;
    }

    /**
     * Accepts a visitor for processing this SupernovaStatement element as part of the Visitor pattern.
     * This allows external operations to be performed on the statement without modifying its structure.
     *
     * @param v The visitor instance responsible for processing this SupernovaStatement.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitSupernovaStatement(this, arg);
    }
}
