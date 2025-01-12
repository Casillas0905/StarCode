package Starcode.parser.ast;

import Starcode.core.IVisitor;

public class CometLiteral extends Terminal
{
    public CometLiteral(String spelling)
    {
        this.spelling = spelling;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitCometLiteral( this, arg );
    }
}
