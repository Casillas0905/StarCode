package Starcode.ast;

import java.util.Vector;

public class IDList extends AST {
    Vector<String> identifiers;
    String identifier;

    IDList(String identifier, Vector<String> identifiers)
    {
        this.identifier = identifier;
        this.identifiers = identifiers;
    }
}
