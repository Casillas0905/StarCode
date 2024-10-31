package Starcode.ast;

import java.util.Vector;

class Statements extends AST
{
    public Vector<OneStatement> statements;

    public Statements(Vector<OneStatement> statements)
    {
        this.statements = statements;
    }
}
