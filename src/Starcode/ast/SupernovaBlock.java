package Starcode.ast;

class SupernovaBlock extends AST {
    public Statements statements;
    public String returnIdentifier;

    public SupernovaBlock(Statements statements, String returnIdentifier) {
        this.statements = statements;
        this.returnIdentifier = returnIdentifier;
    }
}
