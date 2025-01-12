package TAM;

public final class Machine {
    public final static int maxRoutineLevel = 7;

// WORDS AND ADDRESSES
// Java has no type synonyms, so the following representations are
// assumed:
//
//  type
//    Word = -32767..+32767; {16 bits signed}
//    DoubleWord = -2147483648..+2147483647; {32 bits signed}
//    CodeAddress = 0..+32767; {15 bits unsigned}
//    DataAddress = 0..+32767; {15 bits unsigned}

// INSTRUCTIONS
    // Operation codes
    public final static int
            // Memory
            LOADop = 0,
            LOADAop = 1,
            LOADIop = 2,
            LOADLop = 3,
            STOREop = 4,
            STOREIop = 5,
            //Routines
            CALLop = 6,
            CALLIop = 7,
            RETURNop = 8,
            // Stack
            PUSHop = 10,
            POPop = 11,
            // Control flow
            JUMPop = 12,
            JUMPIop = 13,
            JUMPIFop = 14,
            // Termination
            HALTop = 15;

// CODE STORE
    public static Instruction[] code = new Instruction[1024];

// CODE STORE REGISTERS
    public final static int
            CodeBase = 0, // binary code of our program -> translated from Assembly code
            PrimitivesBase = 1024,  // = upper bound of code array + 1 -> primitives (operations such are  etc)
            PrimitivesTop = 1052;  // = PB + 28 -> used for stack, heap, etc (compile-time/runtime data management)

// REGISTER NUMBERS
    public final static int
            CBr = 0, // Code base register -> points to the start of the program (0)
            CTr = 1, // Code top register -> points to the end of the code segment
            PBr = 2, // Primitive base register -> PB
            PTr = 3, // Primitive base Register -> PT
            SBr = 4, // Stack base register -> points to start of the Stack
            STr = 5, // Stack top register -> points to the current top of the stack
            HBr = 6, // Heap - no concern
            HTr = 7, // Heap - no concern
            LBr = 8, // Local base register -> points to the base of a stack frame
            L1r = LBr + 1, // register for 1st stack frame
            L2r = LBr + 2, // register for 2st stack frame
            L3r = LBr + 3, // register for 3rd stack frame
            L4r = LBr + 4, // register for 4th stack frame
            L5r = LBr + 5, // register for 5th stack frame
            L6r = LBr + 6, // register for 6th stack frame
            CPr = 15; // Current program counter register -> points to the current instruction that is being executed

// DATA REPRESENTATION
    public final static int
            booleanSize = 1,
            characterSize = 1,
            integerSize = 1,
            addressSize = 1,
            closureSize = 2 * addressSize, // Needs to have an adress for beginning, and where to return after the closure
    linkDataSize = 3 * addressSize, // Needs to have dynamic link, static link, and return address
    falseRep = 0,
            trueRep = 1,
            maxintRep = 32767;


// ADDRESSES OF PRIMITIVE ROUTINES
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
