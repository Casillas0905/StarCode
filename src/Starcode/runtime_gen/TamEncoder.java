package Starcode.runtime_gen;

import TAM.Instruction;
import TAM.Machine;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

/**
 * The TamEncoder class is responsible for encoding instructions for the TAM (Triangle Abstract Machine).
 * It manages the creation, storage, and manipulation of instructions, as well as saving them to a file.
 */
public class TamEncoder {

    /**
     * The address of the next instruction to be written.
     * Initialized to the base address of the code section in the TAM.
     */
    public int nextInstructionAddress = Machine.CodeBase;

    /**
     * Sets an instruction in the machine's code memory.
     *
     * @param operation The opcode for the instruction.
     * @param length The size of the operand, must not exceed 255.
     * @param register The register used for the instruction.
     * @param operand The operand value, which can represent data, an address, or a call target.
     */
    public void setInstruction(int operation, int length, int register, int operand) {
        if (length > 255) {
            System.out.println("Operand is too long");
        }

        Instruction instruction = new Instruction();
        instruction.op = operation;
        instruction.n = length;
        instruction.r = register;
        instruction.d = operand;

        // Check for overflow of code memory
        if (nextInstructionAddress >= Machine.PrimitivesBase) {
            System.out.println("Program is too large!");
        } else {
            Machine.code[nextInstructionAddress++] = instruction;
        }
    }

    /**
     * Updates the operand of an existing instruction in memory.
     *
     * @param address The memory address of the instruction to update.
     * @param newOperand The new operand value to set.
     */
    public void setInstructionOperand(int address, int newOperand) {
        Machine.code[address].d = newOperand;
    }

    /**
     * Determines the appropriate register for accessing an entity based on its depth.
     *
     * @param currentLevel The current scope level.
     * @param entityLevel The scope level of the entity being accessed.
     * @return The register number used to access the entity.
     */
    public int getRegisterByDepth(int currentLevel, int entityLevel) {
        if (entityLevel == 0) {
            return Machine.SBr; // Static base pointer
        } else if (currentLevel - entityLevel <= 6) {
            return Machine.LBr + currentLevel - entityLevel; // Stack frame pointer
        } else {
            System.out.println("Accessing across too many levels");
            return Machine.L6r; // Default to the highest level
        }
    }

    /**
     * Saves the program's instructions to a file.
     *
     * @param fileName The name of the file to save the instructions to.
     */
    public void saveProgramToFile(String fileName) {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));
            for (int i = Machine.CodeBase; i < nextInstructionAddress; ++i) {
                Machine.code[i].write(out); // Write each instruction to the file
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred when writing instructions to: " + fileName);
        }
    }
}