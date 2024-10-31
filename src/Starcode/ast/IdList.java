package Starcode.ast;

import java.util.Vector;

public class IdList extends AST
{
    public Vector<Identifier> identifiers;
    //TODO: Ask if this is a right approach !!
    public boolean areArrays;

    public IdList(Vector<Identifier> identifiers, boolean areArrays)
    {
        this.identifiers = identifiers;
        this.areArrays = areArrays;
    }
}
