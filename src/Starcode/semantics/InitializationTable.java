package Starcode.semantics;

import java.util.Vector;

/**
 * The InitializationTable class keeps track of initialized variables in the program.
 * It stores each variable's identifier and its associated value.
 */
public class InitializationTable {

    // A vector to store InitializedId objects, which associate variable identifiers with their values
    private Vector<InitializedId> initializedVariables = new Vector<>();

    /**
     * Constructs an empty InitializationTable.
     */
    public InitializationTable() {
        // No initialization needed
    }

    /**
     * Adds or updates an initialized variable to the table.
     * If the variable is already initialized, it updates its value; otherwise, it adds a new entry.
     *
     * @param id The identifier of the variable.
     * @param value The value associated with the identifier.
     */
    public void addInitialization(String id, Object value) {
        // Look for an existing initialized variable by id
        InitializedId initializedId = getInitializedId(id);

        if (initializedId != null) {
            // If the variable is already initialized, update its value
            initializedId.value = value;
            return;
        }

        // Otherwise, add a new entry for the variable and its value
        initializedVariables.add(new InitializedId(id, value));
    }

    /**
     * Retrieves an initialized variable by its identifier.
     *
     * @param id The identifier of the variable.
     * @return The InitializedId object containing the variable and its value, or null if not found.
     */
    public InitializedId getInitializedId(String id) {
        // Search through the initialized variables to find the one with the given id
        for (InitializedId initId : initializedVariables) {
            if (initId.id.equals(id)) {
                return initId; // Return the matching InitializedId
            }
        }
        return null; // Return null if no matching variable is found
    }
}
