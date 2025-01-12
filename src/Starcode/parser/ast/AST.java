package Starcode.parser.ast;

import Starcode.core.IVisitor;

public abstract class AST
{
    public abstract Object visit(IVisitor v, Object arg);
}
