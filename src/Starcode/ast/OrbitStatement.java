package Starcode.ast;

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
}
