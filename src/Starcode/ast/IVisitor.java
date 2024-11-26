package Starcode.ast;

public interface IVisitor {

    public Object visitProgram(Program program, Object arg);
    public Object visitArrayAccess(ArrayAccess arrayAccess, Object arg);
    public Object visitBlock(Block block, Object arg);
    public Object visitCometDeclaration(CometDeclaration cometDeclaration, Object arg);
    public Object visitCometLiteral(CometLiteral cometLiteral, Object arg);
    public Object visitDeclarations(Declarations declarations, Object arg);
    public Object visitEclipseStatement(EclipseStatement eclipseStatement, Object arg);
    public Object visitExpression(Expression expression, Object arg);
    public Object visitExpressionStatement(ExpressionStatement expressionStatement, Object arg);
    public Object visitIdentifier(Identifier identifier, Object arg);
    public Object visitIdList(IdList idList, Object arg);
    public Object visitOperator(Operator operator, Object arg);
    public Object visitOrbitStatement(OrbitStatement orbitStatement, Object arg);
    public Object visitPrimary(Primary primary, Object arg);
    public Object visitProgramBlock(ProgramBlock programBlock, Object arg);
    public Object visitReturnStatement(ReturnStatement returnStatement, Object arg);
    public Object visitReturnType(ReturnType returnType, Object arg);
    public Object visitStarDeclaration(StarDeclaration starDeclaration, Object arg);
    public Object visitStarLiteral(StarLiteral starLiteral, Object arg);
    public Object visitStatements(Statements statements, Object arg);
    public Object visitSupernovaBlock(SupernovaBlock supernovaBlock, Object arg);
    public Object visitSupernovaDeclaration(SupernovaDeclaration supernovaDeclaration, Object arg);
    public Object visitSupernovaStatement(SupernovaStatement supernovaStatement, Object arg);
    public Object visitTypeList(TypeList typeList, Object arg);
}
