package Starcode.parser.ast;

import Starcode.core.IVisitor;
import Starcode.runtime_gen.Address;

public class Identifier extends Terminal
{
    public Address address;
    public Identifier(String spelling)
    {
        this.spelling = spelling;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitIdentifier( this, arg );
    }
}
