package Starcode.parser.ast;

import Starcode.core.IVisitor;
import Starcode.runtime_gen.Address;

/**
 * The Identifier class represents an identifier in the abstract syntax tree (AST).
 * An identifier is a name used to refer to variables, functions, or other named entities in the program.
 */
public class Identifier extends Terminal {

    /**
     * The memory address associated with this identifier.
     * This may be used during code generation or execution to reference the identifier's location.
     */
    public Address address;

    /**
     * Constructs an Identifier instance with the specified spelling.
     *
     * @param spelling The string representation of the identifier as it appears in the source code.
     */
    public Identifier(String spelling) {
        this.spelling = spelling;
    }

    /**
     * Accepts a visitor for processing this Identifier element as part of the Visitor pattern.
     * This allows external operations to be performed on the identifier without altering its structure.
     *
     * @param v The visitor instance responsible for processing this Identifier.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitIdentifier(this, arg);
    }
}