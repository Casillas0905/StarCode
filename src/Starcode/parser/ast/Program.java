package Starcode.parser.ast;

import Starcode.core.IVisitor;

/**
 * The Program class represents the root of the abstract syntax tree (AST).
 * It encapsulates the main block of the program, which contains all the statements and declarations
 * that define the program's behavior.
 */
public class Program extends AST {

    /**
     * The main block of the program, containing the declarations and statements.
     */
    public ProgramBlock block;

    /**
     * Constructs a Program instance with the specified program block.
     *
     * @param block The main block of the program.
     */
    public Program(ProgramBlock block) {
        this.block = block;
    }

    /**
     * Accepts a visitor for processing this Program element as part of the Visitor pattern.
     * This allows external operations to be performed on the program without modifying its structure.
     *
     * @param v The visitor instance responsible for processing this Program.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitProgram(this, arg);
    }
}