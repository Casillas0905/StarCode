package Starcode.parser.ast;

import Starcode.core.IVisitor;

import java.util.Vector;

public class Declarations extends AST
{
    public Vector<OneDeclaration> declarations;

    public Declarations(Vector<OneDeclaration> declarations)
    {
        this.declarations = declarations;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitDeclarations( this, arg );
    }
}

