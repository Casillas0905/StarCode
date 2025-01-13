package TAM;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * The Instruction class represents a TAM (Tiny Abstract Machine) instruction.
 * It contains fields for the opcode, register number, length, and operand.
 * This class also provides methods to read and write instructions from/to files.
 */
public class Instruction {

    // The opcode, register number, length, and operand are initialized to zero.
    public int op; // OpCode (4 bits unsigned)
    public int r;  // RegisterNumber (8 bits unsigned)
    public int n;  // Length (8 bits unsigned)
    public int d;  // Operand (16 bits signed)

    /**
     * Default constructor initializes the fields to zero.
     */
    public Instruction() {
        op = 0;
        r = 0;
        n = 0;
        d = 0;
    }

    /**
     * Writes the instruction to the specified output stream.
     * This method writes the fields (op, r, n, and d) to the output stream.
     *
     * @param output The DataOutputStream to write the instruction to.
     * @throws IOException If an I/O error occurs during writing.
     */
    public void write(DataOutputStream output) throws IOException {
        output.writeInt(op);  // Write the OpCode
        output.writeInt(r);   // Write the Register Number
        output.writeInt(n);   // Write the Length
        output.writeInt(d);   // Write the Operand
    }

    /**
     * Reads an instruction from the specified input stream.
     * This method reads the fields (op, r, n, and d) from the input stream and returns a new Instruction object.
     *
     * @param input The DataInputStream to read the instruction from.
     * @return A new Instruction object populated with the read values.
     * @throws IOException If an I/O error occurs during reading.
     */
    public static Instruction read(DataInputStream input) throws IOException {
        Instruction inst = new Instruction();  // Create a new Instruction object
        try {
            inst.op = input.readInt();  // Read the OpCode
            inst.r = input.readInt();   // Read the Register Number
            inst.n = input.readInt();   // Read the Length
            inst.d = input.readInt();   // Read the Operand
            return inst;  // Return the populated Instruction object
        } catch (EOFException s) {
            return null;  // Return null if the end of the file is reached
        }
    }
}
