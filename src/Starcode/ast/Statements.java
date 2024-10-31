package Starcode.ast;

import java.util.Vector;

public class Statements extends AST
{
    public Vector<OneStatement> statements;

    public Statements(Vector<OneStatement> statements)
    {
        this.statements = statements;
    }
}
