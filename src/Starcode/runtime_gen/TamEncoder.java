package Starcode.runtime_gen;

import TAM.Instruction;
import TAM.Machine;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class TamEncoder
{
    public int nextInstructionAddress = Machine.CodeBase;

    /// Operand can occupy up to 32 bytes of memory -> 256 bits
    /// Operand is a number/adress to be stored/loaded/called - depending on the operation
    public void setInstruction(int operation, int lenght, int register, int operand)
    {
        if(lenght > 255)
        {
            System.out.println("Operand is too long");
        }

        Instruction instruction = new Instruction();
        instruction.op = operation;
        instruction.n = lenght;
        instruction.r = register;
        instruction.d = operand;

        if(nextInstructionAddress >= Machine.PrimitivesBase)
        {
            System.out.println("Program is too large!");
        }
        else
        {
            Machine.code[nextInstructionAddress++] = instruction;
        }
    }

    public void setInstructionOperand(int address, int newOperand)
    {
        Machine.code[address].d = newOperand;
    }

    public int getRegisterByDepth(int currentLevel, int entityLevel)
    {
        if(entityLevel == 0)
        {
            return Machine.SBr; // Static base pointer
        }
        else if(currentLevel - entityLevel <= 6 )
        {
            return Machine.LBr + currentLevel - entityLevel; // Stack frame pointer
        }
        else
        {
            System.out.println("Accessing across to many levels");
            return Machine.L6r;
        }
    }

    public void saveProgramToFile(String fileName)
    {
        try
        {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));
            for(int i = Machine.CodeBase; i < nextInstructionAddress; ++i)
            {
                Machine.code[i].write(out);
            }
            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error occured when writing instructions to::" + fileName);
        }
    }


}
