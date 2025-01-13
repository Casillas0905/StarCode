package Starcode.parser.ast;

import Starcode.core.IVisitor;
import TAM.Machine;

/**
 * The Operator class represents an operator in the abstract syntax tree (AST).
 * An operator is a symbol used in expressions to perform mathematical or logical operations.
 */
public class Operator extends Terminal {

    /**
     * Constructs an Operator instance with the specified spelling.
     *
     * @param spelling The string representation of the operator, such as "+", "-", "*", or "/".
     */
    public Operator(String spelling) {
        this.spelling = spelling;
    }

    /**
     * Accepts a visitor for processing this Operator element as part of the Visitor pattern.
     * This allows external operations to be performed on the operator without modifying its structure.
     *
     * @param v The visitor instance responsible for processing this Operator.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitOperator(this, arg);
    }

    /**
     * Retrieves the opcode corresponding to this operator for use in a virtual machine or interpreter.
     * The opcode is determined by the operator's spelling.
     *
     * @return The integer opcode corresponding to the operator, or 0 if the operator is unrecognized.
     */
    public int getOpCode() {
        if (spelling.equals("+")) {
            return Machine.addDisplacement; // Opcode for addition
        } else if (spelling.equals("-")) {
            return Machine.subDisplacement; // Opcode for subtraction
        } else if (spelling.equals("*")) {
            return Machine.multDisplacement; // Opcode for multiplication
        } else if (spelling.equals("/")) {
            return Machine.divDisplacement; // Opcode for division
        }

        return 0; // Default value for unrecognized operators
    }
}
