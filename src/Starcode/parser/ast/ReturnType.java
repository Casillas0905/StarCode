package Starcode.parser.ast;

import Starcode.core.IVisitor;

/**
 * The ReturnType class represents the return type of a function or method in the abstract syntax tree (AST).
 * It specifies the type of value returned and whether the return type is an array.
 */
public class ReturnType extends AST {

    /**
     * The data type of the return value (e.g., "int", "float", "String").
     */
    public String type;

    /**
     * A flag indicating whether the return type is an array.
     * If true, the return type is an array; otherwise, it is a scalar value.
     */
    public boolean isArray;

    /**
     * Constructs a ReturnType instance with the specified type and array flag.
     *
     * @param type The data type of the return value.
     * @param isArray True if the return type is an array; false otherwise.
     */
    public ReturnType(String type, boolean isArray) {
        this.type = type;
        this.isArray = isArray;
    }

    /**
     * Accepts a visitor for processing this ReturnType element as part of the Visitor pattern.
     * This allows external operations to be performed on the return type without modifying its structure.
     *
     * @param v The visitor instance responsible for processing this ReturnType.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitReturnType(this, arg);
    }
}