package TAM;

/**
 * The Machine class defines constants and data structures related to the Tiny Abstract Machine (TAM).
 * It includes the operation codes for various instructions, addresses for memory storage, and the representation
 * of different data types and register numbers used for processing.
 */
public final class Machine {

    // Maximum routine (function/method) nesting level.
    public final static int maxRoutineLevel = 7;

    // ====================================================
    // WORDS AND ADDRESSES
    // These are the representations used to encode values in the machine.
    // In Java, we use integer types, but these are logically mapped to specific sizes in the TAM.
    // Java has no type synonyms, so the following representations are assumed.
    // ====================================================

    //  type
    //    Word = -32767..+32767; {16 bits signed}
    //    DoubleWord = -2147483648..+2147483647; {32 bits signed}
    //    CodeAddress = 0..+32767; {15 bits unsigned}
    //    DataAddress = 0..+32767; {15 bits unsigned}

    // ====================================================
    // INSTRUCTION CODES
    // These are the operation codes (opcodes) for different instructions that the machine can execute.
    // ====================================================
    public final static int
            // Memory operations
            LOADop = 0,
            LOADAop = 1,
            LOADIop = 2,
            LOADLop = 3,
            STOREop = 4,
            STOREIop = 5,

    // Routine operations
    CALLop = 6,
            CALLIop = 7,
            RETURNop = 8,

    // Stack operations
    PUSHop = 10,
            POPop = 11,

    // Control flow operations
    JUMPop = 12,
            JUMPIop = 13,
            JUMPIFop = 14,

    // Termination operation
    HALTop = 15;

    // ====================================================
    // CODE STORAGE
    // The 'code' array holds the instructions that represent the program to be executed by the machine.
    // ====================================================
    public static Instruction[] code = new Instruction[1024];

    // ====================================================
    // CODE STORAGE REGISTER ADDRESSES
    // These constants define the memory locations for different sections of the program code.
    // ====================================================
    public final static int
            CodeBase = 0,  // Starting point for the binary code of our program (translated from Assembly code)
            PrimitivesBase = 1024,  // Upper bound of the code array + 1, used for primitives (operations, etc.)
            PrimitivesTop = 1052;  // Upper bound for primitives, used for stack, heap, etc. (runtime data management)

    // ====================================================
    // REGISTER NUMBERS
    // These are the registers used by the TAM machine for various purposes like code base, stack, heap, etc.
    // ====================================================
    public final static int
            CBr = 0,  // Code base register -> points to the start of the program
            CTr = 1,  // Code top register -> points to the end of the code segment
            PBr = 2,  // Primitive base register -> points to the base of the primitives segment
            PTr = 3,  // Primitive top register -> points to the end of the primitives segment
            SBr = 4,  // Stack base register -> points to the start of the stack
            STr = 5,  // Stack top register -> points to the current top of the stack
            HBr = 6,  // Heap base register (currently unused)
            HTr = 7,  // Heap top register (currently unused)
            LBr = 8,  // Local base register -> points to the base of a stack frame
            L1r = LBr + 1,  // Register for the 1st stack frame
            L2r = LBr + 2,  // Register for the 2nd stack frame
            L3r = LBr + 3,  // Register for the 3rd stack frame
            L4r = LBr + 4,  // Register for the 4th stack frame
            L5r = LBr + 5,  // Register for the 5th stack frame
            L6r = LBr + 6,  // Register for the 6th stack frame
            CPr = 15;  // Current program counter register -> points to the current instruction being executed

    // ====================================================
    // DATA REPRESENTATION
    // These constants represent the sizes of various data types used in the machine.
    // ====================================================
    public final static int
            booleanSize = 1,
            characterSize = 1,
            integerSize = 1,
            addressSize = 1,
            closureSize = 2 * addressSize,  // A closure has two addresses: one for the beginning, one for return
            linkDataSize = 3 * addressSize,  // Link data requires dynamic link, static link, and return address
            falseRep = 0,
            trueRep = 1,
            maxintRep = 32767;

    // ====================================================
    // ADDRESSES OF PRIMITIVE ROUTINES
    // These constants define the positions of primitive routines in the program.
    // These routines implement basic operations like arithmetic, input/output, etc.
    // ====================================================
    public final static int
            idDisplacement = 1,
            notDisplacement = 2,
            andDisplacement = 3,
            orDisplacement = 4,
            succDisplacement = 5,
            predDisplacement = 6,
            negDisplacement = 7,
            addDisplacement = 8,
            subDisplacement = 9,
            multDisplacement = 10,
            divDisplacement = 11,
            modDisplacement = 12,
            ltDisplacement = 13,
            leDisplacement = 14,
            geDisplacement = 15,
            gtDisplacement = 16,
            eqDisplacement = 17,
            neDisplacement = 18,
            eolDisplacement = 19,
            eofDisplacement = 20,
            getDisplacement = 21,
            putDisplacement = 22,
            geteolDisplacement = 23,
            puteolDisplacement = 24,
            getintDisplacement = 25,
            putintDisplacement = 26,
            newDisplacement = 27,
            disposeDisplacement = 28;
}
