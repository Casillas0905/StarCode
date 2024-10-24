package Starcode.ast;

class Block extends AST {
    public Statements statements;
    public String returnIdentifier;

    public Block(Statements statements) {
        this.statements = statements;
    }

    public Block(Statements statements, String returnIdentifier) {
        this.statements = statements;
        this.returnIdentifier = returnIdentifier;
    }

    public Block(String returnIdentifier) {
        this.returnIdentifier = returnIdentifier;
    }
}
