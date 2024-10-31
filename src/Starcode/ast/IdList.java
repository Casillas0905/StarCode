package Starcode.ast;

import java.util.Vector;

public class IdList extends AST
{
    public Vector<String> identifiers;
    //TODO: Ask if this is a right approach !!
    public boolean areArrays;

    public IdList(Vector<String> identifiers, boolean areArrays)
    {
        this.identifiers = identifiers;
        this.areArrays = areArrays;
    }
}
