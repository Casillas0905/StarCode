package Starcode.ast;

import java.util.Vector;

public class Statements extends AST
{
    public Vector<OneStatement> statements;

    public Statements(Vector<OneStatement> statements)
    {
        this.statements = statements;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitStatements( this, arg );
    }
}
