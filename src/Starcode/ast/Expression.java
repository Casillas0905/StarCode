package Starcode.ast;

import java.util.Vector;

public class Expression extends AST
{
    public Primary primary;
    public Vector<Operator> operators;
    public Vector<Primary> primaries;

    public Expression(Primary primary, Vector<Primary> primaries, Vector<Operator> operators) {
        this.primary = primary;
        this.primaries = primaries;
        this.operators = operators;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitExpression( this, arg );
    }
}
