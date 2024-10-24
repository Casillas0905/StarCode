package Starcode.ast;

import java.util.Vector;

class OneStatement extends AST {
    public Expression expression;
    public Block block;
    public String identifier;
    public Vector<Primary> primaries = new Vector<>();

    public OneStatement(Expression expression) {
        this.expression = expression;
    }

    public OneStatement(Expression expression, Block block) {
        this.expression = expression;
        this.block = block;
    }

    public OneStatement(String identifier, Vector<Primary> primaries) {
        this.identifier = identifier;
        this.primaries = primaries;
    }
}
