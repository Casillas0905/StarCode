package Starcode.semantics;

import Starcode.parser.ast.OneDeclaration;

import java.util.Vector;

/**
 * The DeclarationsTable class manages a collection of declarations.
 * It allows adding and retrieving declarations by their identifier (id).
 */
public class DeclarationsTable {

    // A vector to store IdEntry objects, which pair identifiers with declarations
    private Vector<IdEntry> declarations = new Vector<>();

    /**
     * Constructs an empty DeclarationsTable.
     */
    public DeclarationsTable() {
        // No initialization required
    }

    /**
     * Adds a declaration to the table.
     * If the identifier is already declared, an error message is printed, and the declaration is not added.
     *
     * @param id The identifier of the declaration.
     * @param declaration The declaration object to associate with the identifier.
     */
    public void addDeclaration(String id, OneDeclaration declaration) {
        // Check if the identifier is already declared
        IdEntry idEntry = findDeclaration(id);
        if (idEntry != null) {
            System.out.println(id + " declared twice"); // Error message for duplicate declaration
            return;
        }

        // Add the new declaration
        declarations.add(new IdEntry(id, declaration));
    }

    /**
     * Retrieves the declaration associated with a given identifier.
     *
     * @param id The identifier to look up.
     * @return The declaration object if found, or null if the identifier is not declared.
     */
    public OneDeclaration getDeclaration(String id) {
        // Find the declaration by id
        IdEntry entry = findDeclaration(id);

        // Return the associated declaration or null if not found
        if (entry != null) {
            return entry.attr;
        } else {
            return null;
        }
    }

    /**
     * Finds a declaration entry by its identifier.
     *
     * @param id The identifier to look up.
     * @return The IdEntry object if found, or null if the identifier is not declared.
     */
    private IdEntry findDeclaration(String id) {
        // Iterate through the declarations to find a matching id
        for (IdEntry entry : declarations) {
            if (entry.id.equals(id)) {
                return entry; // Return the matching entry
            }
        }
        return null; // Return null if no match is found
    }
}