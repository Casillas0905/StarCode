package Starcode.parser.ast;

import Starcode.core.IVisitor;
import Starcode.runtime_gen.Address;

/**
 * The SupernovaDeclaration class represents the declaration of a function or method in the abstract syntax tree (AST).
 * It includes details about the function's return type, name, parameters, body, and type information.
 */
public class SupernovaDeclaration extends OneDeclaration {

    /**
     * The return type of the function or method.
     */
    public ReturnType returnType;

    /**
     * The identifier representing the name of the function or method.
     */
    public Identifier identifier;

    /**
     * The list of parameter identifiers for the function or method.
     */
    public IdList idList;

    /**
     * The block of code defining the function or method body.
     */
    public SupernovaBlock supernovaBlock;

    /**
     * The type list providing additional type information for the parameters or function.
     */
    public TypeList typeList;

    /**
     * The memory address associated with this declaration.
     * This may be used during code generation or execution to reference the function's location.
     */
    public Address address;

    /**
     * Constructs a SupernovaDeclaration instance with the specified return type, name, parameters, body, and type list.
     *
     * @param returnType The return type of the function or method.
     * @param identifier The name of the function or method.
     * @param idList The list of parameter identifiers.
     * @param supernovaBlock The block of code defining the function body.
     * @param typeList The type information for the parameters or function.
     */
    public SupernovaDeclaration(ReturnType returnType, Identifier identifier, IdList idList, SupernovaBlock supernovaBlock, TypeList typeList) {
        this.returnType = returnType;
        this.identifier = identifier;
        this.idList = idList;
        this.supernovaBlock = supernovaBlock;
        this.typeList = typeList;
    }

    /**
     * Accepts a visitor for processing this SupernovaDeclaration element as part of the Visitor pattern.
     * This allows external operations to be performed on the declaration without modifying its structure.
     *
     * @param v The visitor instance responsible for processing this SupernovaDeclaration.
     * @param arg Additional argument for processing, providing context or state.
     * @return Result of the visitor's visit operation.
     */
    public Object visit(IVisitor v, Object arg) {
        return v.visitSupernovaDeclaration(this, arg);
    }
}