package Starcode.parser.ast;

import Starcode.core.IVisitor;
import Starcode.runtime_gen.Address;

/**
 * The CometDeclaration class represents the declaration of a comet variable in the abstract syntax tree (AST).
 * A comet can either be an integer variable or an array, depending on its properties.
 */
public class CometDeclaration extends OneDeclaration {

    /**
     * The identifier representing the name of the comet variable.
     */
    public Identifier identifier;

    /**
     * A flag indicating whether the comet variable is an array.
     * If true, the variable is an array; otherwise, it is a scalar.
     */
    public boolean isArray;

    /**
     * The length of the array, if the comet variable is declared as an array.
     */
    public int arrayLenght;

    /**
     * The memory address associated with this comet declaration.
     * This may be used for code generation or execution purposes.
     */
    public Address address;

    /**
     * Constructs a CometDeclaration instance with the specified identifier, array flag, and array length.
     *
     * @param identifier The identifier for the comet variable.
     * @param isArray True if the variable is an array; false otherwise.
     * @param arrayLenght The length of the array, applicable if isArray is true.
     */
    public CometDeclaration(Identifier identifier, boolean isArray, int arrayLenght) {
        this.identifier = identifier;
        this.isArray = isArray;
        this.arrayLenght = arrayLenght;
    }

    /**
     * Accepts a visitor for processing this CometDeclaration element as part of the Visitor pattern.
     * This enables operations on this CometDeclaration without modifying its structure.
     *
     * @param v The visitor instance responsible for processing this declaration.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitCometDeclaration(this, arg);
    }
}
