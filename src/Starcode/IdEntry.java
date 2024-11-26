package Starcode;

import Starcode.ast.OneDeclaration;

public class IdEntry
{
    public String id;
    public OneDeclaration attr;

    public IdEntry(String id, OneDeclaration attr)
    {
        this.id = id;
        this.attr = attr;
    }

    public String toString()
    {
        return id;
    }
}
