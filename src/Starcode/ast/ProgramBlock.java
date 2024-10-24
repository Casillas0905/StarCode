package Starcode.ast;

public class ProgramBlock {
    Declarations declarations;
    OneStatement statement;

    ProgramBlock(Declarations declarations, OneStatement statement){
        this.declarations = declarations;
        this.statement = statement;
    }
}
