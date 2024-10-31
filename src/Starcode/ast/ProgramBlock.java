package Starcode.ast;

public class ProgramBlock extends AST
{
    public Declarations declarations;
    public SupernovaStatement statement;

    public ProgramBlock(Declarations declarations, SupernovaStatement statement)
    {
        this.declarations = declarations;
        this.statement = statement;
    }
}
