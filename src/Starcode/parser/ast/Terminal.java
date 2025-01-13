package Starcode.parser.ast;

/**
 * The Terminal class is an abstract representation of terminal elements in the abstract syntax tree (AST).
 * Terminal elements are the basic building blocks in the syntax tree that cannot be further decomposed,
 * such as identifiers, literals, or operators.
 */
public abstract class Terminal extends AST {

    /**
     * The spelling of the terminal, which is its textual representation in the source code.
     * For example, the spelling of a literal could be "42", and the spelling of an operator could be "+".
     */
    public String spelling;
}