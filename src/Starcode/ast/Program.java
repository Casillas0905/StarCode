package Starcode.ast;

public class Program extends AST {
    ProgramBlock block;

    Program(ProgramBlock block) {
        this.block = block;
    }
}
