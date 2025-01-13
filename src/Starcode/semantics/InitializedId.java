package Starcode.semantics;

/**
 * The InitializedId class represents a variable that has been initialized.
 * It stores the variable's identifier and its associated value.
 */
public class InitializedId {

    public String id;       // The identifier (name) of the variable
    public Object value;    // The value associated with the variable

    /**
     * Constructs an InitializedId object with the specified identifier and value.
     *
     * @param id The identifier of the variable.
     * @param value The value associated with the variable.
     */
    public InitializedId(String id, Object value) {
        this.id = id;
        this.value = value;
    }

    /**
     * Returns a string representation of the InitializedId object.
     * The string representation is simply the identifier of the variable.
     *
     * @return The identifier of the variable as a string.
     */
    @Override
    public String toString() {
        return id;
    }

    /**
     * Retrieves the value associated with the initialized variable.
     *
     * @return The value of the variable.
     */
    public Object getValue() {
        return value;
    }
}