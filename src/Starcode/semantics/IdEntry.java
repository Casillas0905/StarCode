package Starcode.semantics;

import Starcode.parser.ast.OneDeclaration;

/**
 * The IdEntry class represents an entry in the declarations table.
 * It associates an identifier (id) with its corresponding declaration (attr).
 */
public class IdEntry {

    public String id;           // The identifier name
    public OneDeclaration attr; // The associated declaration object

    /**
     * Constructs an IdEntry with the specified identifier and declaration.
     *
     * @param id The identifier name.
     * @param attr The declaration associated with the identifier.
     */
    public IdEntry(String id, OneDeclaration attr) {
        this.id = id;
        this.attr = attr;
    }

    /**
     * Returns the string representation of the IdEntry, which is the identifier.
     *
     * @return The identifier as a string.
     */
    @Override
    public String toString() {
        return id;
    }
}
