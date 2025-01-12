package Starcode.parser.ast;

import Starcode.core.IVisitor;

public class OrbitStatement extends OneStatement
{
    public Identifier incrementalIdentifier;
    public Identifier countIdentifier;
    public Block block;

    public OrbitStatement(Identifier incrementalIdentifier, Identifier countIdentifier, Block block)
    {
        this.incrementalIdentifier = incrementalIdentifier;
        this.countIdentifier = countIdentifier;
        this.block = block;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitOrbitStatement( this, arg );
    }
}
