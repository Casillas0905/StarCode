package Starcode.ast;

public class ReturnStatement extends OneStatement
{
    Primary primary;

    public ReturnStatement(Primary primary)
    {
        this.primary = primary;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitReturnStatement( this, arg );
    }
}
