package Starcode.runtime_gen;

/**
 * The Address class represents a memory address, consisting of a level and a displacement.
 * It is used to manage and reference locations in a runtime environment or memory.
 */
public class Address {

    /**
     * The level of the address, typically representing a scope or nesting level.
     */
    public int level;

    /**
     * The displacement within the given level, representing the offset from a base address.
     */
    public int displacement;

    /**
     * Default constructor for Address.
     * Initializes the level and displacement to zero.
     */
    public Address() {
        level = 0;
        displacement = 0;
    }

    /**
     * Constructs an Address with the specified level and displacement.
     *
     * @param level The scope or nesting level of the address.
     * @param displacement The offset within the specified level.
     */
    public Address(int level, int displacement) {
        this.level = level;
        this.displacement = displacement;
    }

    /**
     * Constructs an Address by copying an existing address and applying an increment to the displacement.
     *
     * @param a The existing Address to copy.
     * @param increment The increment to add to the displacement.
     */
    public Address(Address a, int increment) {
        this.level = a.level;
        this.displacement = a.displacement + increment;
    }

    /**
     * Constructs an Address by copying an existing address and advancing to the next level.
     * The displacement is reset to zero.
     *
     * @param a The existing Address to copy.
     */
    public Address(Address a) {
        this.level = a.level + 1; // Advance to the next level
        this.displacement = 0; // Reset the displacement
    }

    /**
     * Returns a string representation of the Address, showing the level and displacement.
     *
     * @return A string in the format "level={level} displacement={displacement}".
     */
    @Override
    public String toString() {
        return "level=" + level + " displacement=" + displacement;
    }
}