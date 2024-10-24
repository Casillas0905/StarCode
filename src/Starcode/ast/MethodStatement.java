package Starcode.ast;

import java.util.Vector;

class MethodStatement extends AST {
    public String identifier;
    public Vector<Primary> primaries = new Vector<>();

    public MethodStatement(String identifier, Vector<Primary> primaries) {
        this.identifier = identifier;
        this.primaries = primaries;
    }
}
