package Starcode.parser.ast;

import Starcode.core.IVisitor;

public class StarLiteral extends Terminal
{
    public StarLiteral(String spelling)
    {
        this.spelling = spelling;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitStarLiteral( this, arg );
    }
}
