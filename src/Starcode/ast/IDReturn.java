package Starcode.ast;

class IDReturn extends AST {
    public String identifier;
    public boolean isEmpty; // If true, it's epsilon

    public IDReturn(String identifier) {
        this.identifier = identifier;
        this.isEmpty = false;
    }

    public IDReturn() {
        this.isEmpty = true;
    }
}
