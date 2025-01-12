package Starcode.parser.ast;

import Starcode.core.IVisitor;
import Starcode.runtime_gen.Address;

public class SupernovaDeclaration extends OneDeclaration
{
    public ReturnType returnType;
    public Identifier identifier;
    public IdList idList;
    public SupernovaBlock supernovaBlock;
    public TypeList typeList;
    public Address address;

    public SupernovaDeclaration(ReturnType returnType, Identifier identifier, IdList idList, SupernovaBlock supernovaBlock, TypeList typeList) {
        this.returnType = returnType;
        this.identifier = identifier;
        this.idList = idList;
        this.supernovaBlock = supernovaBlock;
        this.typeList = typeList;
    }

    public Object visit(IVisitor v, Object arg )
    {
        return v.visitSupernovaDeclaration( this, arg );
    }
}
