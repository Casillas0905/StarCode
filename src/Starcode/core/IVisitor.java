package Starcode.core;

import Starcode.parser.ast.*;

/**
 * The IVisitor interface defines a set of visit methods for implementing the Visitor design pattern.
 * Each method corresponds to a specific element or construct in the system and is designed to
 * process or traverse the element with additional arguments and potentially return a result.
 */
public interface IVisitor {

    /**
     * Visits a Program element.
     * @param program The Program to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitProgram(Program program, Object arg);

    /**
     * Visits an ArrayAccess element.
     * @param arrayAccess The ArrayAccess to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitArrayAccess(ArrayAccess arrayAccess, Object arg);

    /**
     * Visits a Block element.
     * @param block The Block to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitBlock(Block block, Object arg);

    /**
     * Visits a CometDeclaration element.
     * @param cometDeclaration The CometDeclaration to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitCometDeclaration(CometDeclaration cometDeclaration, Object arg);

    /**
     * Visits a CometLiteral element.
     * @param cometLiteral The CometLiteral to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitCometLiteral(CometLiteral cometLiteral, Object arg);

    /**
     * Visits a Declarations element.
     * @param declarations The Declarations to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitDeclarations(Declarations declarations, Object arg);

    /**
     * Visits an EclipseStatement element.
     * @param eclipseStatement The EclipseStatement to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitEclipseStatement(EclipseStatement eclipseStatement, Object arg);

    /**
     * Visits an Expression element.
     * @param expression The Expression to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitExpression(Expression expression, Object arg);

    /**
     * Visits an ExpressionStatement element.
     * @param expressionStatement The ExpressionStatement to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitExpressionStatement(ExpressionStatement expressionStatement, Object arg);

    /**
     * Visits an Identifier element.
     * @param identifier The Identifier to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitIdentifier(Identifier identifier, Object arg);

    /**
     * Visits an IdList element.
     * @param idList The IdList to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitIdList(IdList idList, Object arg);

    /**
     * Visits an Operator element.
     * @param operator The Operator to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitOperator(Operator operator, Object arg);

    /**
     * Visits an OrbitStatement element.
     * @param orbitStatement The OrbitStatement to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitOrbitStatement(OrbitStatement orbitStatement, Object arg);

    /**
     * Visits a Primary element.
     * @param primary The Primary to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitPrimary(Primary primary, Object arg);

    /**
     * Visits a ProgramBlock element.
     * @param programBlock The ProgramBlock to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitProgramBlock(ProgramBlock programBlock, Object arg);

    /**
     * Visits a ReturnStatement element.
     * @param returnStatement The ReturnStatement to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitReturnStatement(ReturnStatement returnStatement, Object arg);

    /**
     * Visits a ReturnType element.
     * @param returnType The ReturnType to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitReturnType(ReturnType returnType, Object arg);

    /**
     * Visits a StarDeclaration element.
     * @param starDeclaration The StarDeclaration to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitStarDeclaration(StarDeclaration starDeclaration, Object arg);

    /**
     * Visits a StarLiteral element.
     * @param starLiteral The StarLiteral to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitStarLiteral(StarLiteral starLiteral, Object arg);

    /**
     * Visits a Statements element.
     * @param statements The Statements to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitStatements(Statements statements, Object arg);

    /**
     * Visits a SupernovaBlock element.
     * @param supernovaBlock The SupernovaBlock to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitSupernovaBlock(SupernovaBlock supernovaBlock, Object arg);

    /**
     * Visits a SupernovaDeclaration element.
     * @param supernovaDeclaration The SupernovaDeclaration to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitSupernovaDeclaration(SupernovaDeclaration supernovaDeclaration, Object arg);

    /**
     * Visits a SupernovaStatement element.
     * @param supernovaStatement The SupernovaStatement to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitSupernovaStatement(SupernovaStatement supernovaStatement, Object arg);

    /**
     * Visits a TypeList element.
     * @param typeList The TypeList to visit.
     * @param arg Additional argument for processing.
     * @return Result of the visit operation.
     */
    public Object visitTypeList(TypeList typeList, Object arg);
}
