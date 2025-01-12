package Starcode.parser.ast;

import Starcode.core.IVisitor;

import java.util.Vector;

public class Statements extends AST
{
    public Vector<OneStatement> statements;

    public Statements(Vector<OneStatement> statements)
    {
        this.statements = statements;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitStatements( this, arg );
    }
}
