package Starcode.parser.ast;

import Starcode.core.IVisitor;

import java.util.Vector;

/**
 * The TypeList class represents a collection of return types in the abstract syntax tree (AST).
 * It is used to define a list of types, such as those for function parameters or return values.
 */
public class TypeList extends AST {

    /**
     * A vector containing the return types in the list.
     * Each element is an instance of ReturnType, representing a specific type.
     */
    public Vector<ReturnType> returnTypes;

    /**
     * Constructs a TypeList instance with the specified vector of return types.
     *
     * @param returnType A vector of return types to include in the list.
     */
    public TypeList(Vector<ReturnType> returnType) {
        this.returnTypes = returnType;
    }

    /**
     * Accepts a visitor for processing this TypeList element as part of the Visitor pattern.
     * This allows external operations to be performed on the type list without modifying its structure.
     *
     * @param v The visitor instance responsible for processing this TypeList.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitTypeList(this, arg);
    }
}
