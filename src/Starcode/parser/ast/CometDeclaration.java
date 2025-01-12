package Starcode.parser.ast;

import Starcode.core.IVisitor;
import Starcode.runtime_gen.Address;

public class CometDeclaration extends OneDeclaration
{
    public Identifier identifier;
    public boolean isArray;
    public int arrayLenght;
    public Address address;

    public CometDeclaration(Identifier identifier, boolean isArray, int arrayLenght)
    {
        this.identifier = identifier;
        this.isArray = isArray;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitCometDeclaration( this, arg );
    }
}
