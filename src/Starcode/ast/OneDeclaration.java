package Starcode.ast;

class OneDeclaration extends AST {
    public String type; // Can be "star", "comet", or "supernova"
    public String identifier;
    public IDReturn idReturn;
    public IDList idList;
    public SupernovaBlock supernovaBlock;

    public OneDeclaration(String type, String identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    public OneDeclaration(String type, String identifier, IDReturn idReturn, IDList idList, SupernovaBlock supernovaBlock) {
        this.type = type;
        this.identifier = identifier;
        this.idReturn = idReturn;
        this.idList = idList;
        this.supernovaBlock = supernovaBlock;
    }
}

