package Starcode.parser.ast;

import Starcode.core.IVisitor;

import java.util.Vector;

public class TypeList extends AST {

    public Vector<ReturnType> returnTypes;

    public TypeList(Vector<ReturnType> returnType) {
        this.returnTypes = returnType;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitTypeList( this, arg );
    }
}
