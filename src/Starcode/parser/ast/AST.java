package Starcode.parser.ast;

import Starcode.core.IVisitor;

/**
 * The AST (Abstract Syntax Tree) class serves as the base class for all elements
 * of the abstract syntax tree in the program. It defines the structure and behavior
 * that all specific AST nodes must implement.
 */
public abstract class AST {

    /**
     * Abstract method to accept a visitor for processing this AST element.
     * This method must be implemented by all subclasses, enabling traversal
     * and processing of the AST using the Visitor pattern.
     *
     * @param v The visitor instance responsible for processing this AST node.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation, which can vary depending on the implementation.
     */
    public abstract Object visit(IVisitor v, Object arg);
}
