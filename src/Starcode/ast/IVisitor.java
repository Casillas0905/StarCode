package Starcode.ast;

public interface IVisitor {

    public Object visitProgram( Program p, Object arg );

    public Object visitArrayAccess( ArrayAccess p, Object arg );

    public Object visitBlock( Block p, Object arg );

    public Object visitCometDeclaration( CometDeclaration p, Object arg );

    public Object visitCometLiteral( CometLiteral p, Object arg );

    public Object visitDeclarations( Declarations p, Object arg );

    public Object visitEclipseStatement( EclipseStatement p, Object arg );

    public Object visitExpression( Expression p, Object arg );

    public Object visitExpressionStatement( ExpressionStatement p, Object arg );

    public Object visitIdentifier( Identifier p, Object arg );

    public Object visitIdList( IdList p, Object arg );

    public Object visitOperator( Operator p, Object arg );

    public Object visitOrbitStatement( OrbitStatement p, Object arg );

    public Object visitPrimary( Primary p, Object arg );

    public Object visitProgramBlock( ProgramBlock p, Object arg );


    public Object visitReturnStatement( ReturnStatement p, Object arg );

    public Object visitReturnType( ReturnType p, Object arg );

    public Object visitStarDeclaration( StarDeclaration p, Object arg );

    public Object visitStarLiteral( StarLiteral p, Object arg );


    public Object visitStatements( Statements p, Object arg );

    public Object visitSupernovaBlock( SupernovaBlock p, Object arg );

    public Object visitSupernovaDeclaration( SupernovaDeclaration p, Object arg );

    public Object visitSupernovaStatement( SupernovaStatement p, Object arg );

    public Object visitTypeList( TypeList p, Object arg );
}
