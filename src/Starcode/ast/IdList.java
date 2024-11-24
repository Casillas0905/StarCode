package Starcode.ast;

import java.util.Vector;

public class IdList extends AST
{
    public Vector<Identifier> identifiers;
    public boolean areArrays;

    public IdList(Vector<Identifier> identifiers, boolean areArrays)
    {
        this.identifiers = identifiers;
        this.areArrays = areArrays;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitIdList( this, arg );
    }
}
