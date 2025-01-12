package Starcode.parser.ast;

import Starcode.core.IVisitor;
import TAM.Machine;

public class Operator extends Terminal
{
    public Operator(String spelling)
    {
        this.spelling = spelling;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitOperator( this, arg );
    }

    public int getOpCode(){
        if (spelling.equals("+"))
        {
            return Machine.addDisplacement;
        }
        else if(spelling.equals("-"))
        {
            return Machine.subDisplacement;
        }
        else if(spelling.equals("*"))
        {
            return Machine.multDisplacement;
        }
        else if(spelling.equals("/"))
        {
            return Machine.divDisplacement;
        }

        return 0;
    }
}
