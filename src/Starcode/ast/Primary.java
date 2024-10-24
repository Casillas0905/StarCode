package Starcode.ast;

class Primary extends AST {
    public String identifier;
    public String operator;
    public Primary primary;

    public Primary(String identifier) {
        this.identifier = identifier;
    }

    public Primary(String operator, Primary primary) {
        this.operator = operator;
        this.primary = primary;
    }
}
