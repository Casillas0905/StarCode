package Starcode.ast;

public class ReturnStatement extends OneStatement
{
    Primary primary;

    public ReturnStatement(Primary primary)
    {
        this.primary = primary;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitReturnStatement( this, arg );
    }
}
