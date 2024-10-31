package Starcode.ast;

import java.util.ArrayList;

public class Declarations extends AST
{
    public ArrayList<OneDeclaration> declarations;

    Declarations(ArrayList<OneDeclaration> declarations)
    {
        this.declarations = declarations;
    }
}

