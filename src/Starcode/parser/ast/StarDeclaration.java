package Starcode.parser.ast;

import Starcode.core.IVisitor;
import Starcode.runtime_gen.Address;

/**
 * The StarDeclaration class represents the declaration of a star variable in the abstract syntax tree (AST).
 * A star variable can either be a scalar value or an array, depending on its properties.
 */
public class StarDeclaration extends OneDeclaration {

    /**
     * The identifier representing the name of the star variable.
     */
    public Identifier identifier;

    /**
     * A flag indicating whether the star variable is an array.
     * If true, the variable is an array; otherwise, it is a scalar value.
     */
    public boolean isArray;

    /**
     * The length of the array, if the star variable is declared as an array.
     */
    public int arrayLenght;

    /**
     * The memory address associated with this star declaration.
     * This may be used for code generation or execution purposes.
     */
    public Address address;

    /**
     * Constructs a StarDeclaration instance with the specified identifier, array flag, and array length.
     *
     * @param identifier The identifier for the star variable.
     * @param isArray True if the variable is an array; false otherwise.
     * @param arrayLenght The length of the array, applicable if isArray is true.
     */
    public StarDeclaration(Identifier identifier, boolean isArray, int arrayLenght) {
        this.identifier = identifier;
        this.isArray = isArray;
        this.arrayLenght = arrayLenght;
    }

    /**
     * Accepts a visitor for processing this StarDeclaration element as part of the Visitor pattern.
     * This allows external operations to be performed on the declaration without modifying its structure.
     *
     * @param v The visitor instance responsible for processing this StarDeclaration.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitStarDeclaration(this, arg);
    }
}