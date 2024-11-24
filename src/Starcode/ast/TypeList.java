package Starcode.ast;

import java.util.Vector;

public class TypeList extends AST {

    public Vector<ReturnType> returnType;

    public TypeList(Vector<ReturnType> returnType) {
        this.returnType = returnType;
    }

    public Object visit( Visitor v, Object arg )
    {
        return v.visitTypeList( this, arg );
    }
}
