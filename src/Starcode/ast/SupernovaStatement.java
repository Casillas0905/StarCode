package Starcode.ast;

import java.util.Vector;

public class SupernovaStatement extends OneStatement
{
    public Identifier identifier;
    public Vector<Primary> parameters;

    public SupernovaStatement(Identifier identifier, Vector<Primary> parameters)
    {
        this.identifier = identifier;
        this.parameters = parameters;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitSupernovaStatement( this, arg );
    }
}
