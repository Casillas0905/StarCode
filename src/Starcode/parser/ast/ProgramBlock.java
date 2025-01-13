package Starcode.parser.ast;

import Starcode.core.IVisitor;

/**
 * The ProgramBlock class represents the main block of a program in the abstract syntax tree (AST).
 * It encapsulates the declarations and the primary statement (or series of statements) that define
 * the program's behavior.
 */
public class ProgramBlock extends AST {

    /**
     * The declarations within the program block.
     * These include variable, constant, or other declarations needed for the program.
     */
    public Declarations declarations;

    /**
     * The primary statement of the program block.
     * This often represents the entry point or main logic of the program.
     */
    public SupernovaStatement statement;

    /**
     * Constructs a ProgramBlock instance with the specified declarations and main statement.
     *
     * @param declarations The declarations within the program block.
     * @param statement The main statement of the program block.
     */
    public ProgramBlock(Declarations declarations, SupernovaStatement statement) {
        this.declarations = declarations;
        this.statement = statement;
    }

    /**
     * Accepts a visitor for processing this ProgramBlock element as part of the Visitor pattern.
     * This allows external operations to be performed on the block without modifying its structure.
     *
     * @param v The visitor instance responsible for processing this ProgramBlock.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitProgramBlock(this, arg);
    }
}