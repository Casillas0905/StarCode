package Starcode.ast;

import java.util.Vector;

class Expression extends AST {
    public Vector<Primary> primaries = new Vector<>();
    public Vector<String> operators = new Vector<>();

    public Expression(Vector<Primary> primaries, Vector<String> operators) {
        this.primaries = primaries;
        this.operators = operators;
    }
}
