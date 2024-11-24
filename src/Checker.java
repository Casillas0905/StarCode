import Starcode.ast.*;

import java.util.*;


public class Checker
        implements Visitor
{
    private IdentificationTable idTable = new IdentificationTable();


    public void check( Program p )
    {
        p.visit( this, null );
    }


    public Object visitProgram( Program p, Object arg )
    {
        idTable.openScope();

        p.block.visit( this, null );

        idTable.closeScope();

        return null;
    }

    @Override
    public Object visitArrayAccess(ArrayAccess p, Object arg) {
        return null;
    }


    public Object visitBlock(Block b, Object arg )
    {
        b.statements.visit( this, null );

        return null;
    }

    @Override
    public Object visitCometDeclaration(CometDeclaration p, Object arg) {
        return null;
    }

    @Override
    public Object visitCometLiteral(CometLiteral p, Object arg) {
        return null;
    }


    public Object visitDeclarations( Declarations d, Object arg )
    {
        for( Declaration decl: d.dec )
            decl.visit( this, null );

        return null;
    }

    @Override
    public Object visitEclipseStatement(EclipseStatement p, Object arg) {
        return null;
    }

    @Override
    public Object visitExpression(Expression p, Object arg) {
        return null;
    }


    public Object visitVariableDeclaration( VariableDeclaration v, Object arg )
    {
        String id = (String) v.id.visit( this, null );

        idTable.enter( id, v );

        return null;
    }


    public Object visitFunctionDeclaration( FunctionDeclaration f, Object arg )
    {
        String id = (String) f.name.visit( this, null );

        idTable.enter( id, f );
        idTable.openScope();

        f.params.visit( this, null );
        f.block.visit( this, null );
        f.retExp.visit( this, null );

        idTable.closeScope();

        return null;
    }


    public Object visitStatements( Statements s, Object arg )
    {
        for( Statement stat: s.stat )
            stat.visit( this, null );

        return null;
    }

    @Override
    public Object visitSupernovaBlock(SupernovaBlock p, Object arg) {
        return null;
    }

    @Override
    public Object visitSupernovaDeclaration(SupernovaDeclaration p, Object arg) {
        return null;
    }

    @Override
    public Object visitSupernovaStatement(SupernovaStatement p, Object arg) {
        return null;
    }

    @Override
    public Object visitTypeList(TypeList p, Object arg) {
        return null;
    }


    public Object visitExpressionStatement( ExpressionStatement e, Object arg )
    {
        e.exp.visit( this, null );

        return null;
    }


    public Object visitIfStatement( IfStatement i, Object arg )
    {
        i.exp.visit( this, null );
        i.thenPart.visit( this, null );
        i.elsePart.visit( this, null );

        return null;
    }


    public Object visitWhileStatement( WhileStatement w, Object arg )
    {
        w.exp.visit( this, null );
        w.stats.visit( this, null );

        return null;
    }


    public Object visitSayStatement( SayStatement s, Object arg )
    {
        s.exp.visit( this, null );

        return null;
    }


    public Object visitBinaryExpression( BinaryExpression b, Object arg )
    {
        Type t1 = (Type) b.operand1.visit( this, null );
        Type t2 = (Type) b.operand2.visit( this, null );
        String operator = (String) b.operator.visit( this, null );

        if( operator.equals( ":=" ) && t1.rvalueOnly )
            System.out.println( "Left-hand side of := must be a variable" );

        return new Type( true );
    }


    public Object visitVarExpression( VarExpression v, Object arg )
    {
        String id = (String) v.name.visit( this, null );

        Declaration d = idTable.retrieve( id );
        if( d == null )
            System.out.println( id + " is not declared" );
        else if( !( d instanceof VariableDeclaration ) )
            System.out.println( id + " is not a variable" );
        else
            v.decl = (VariableDeclaration) d;

        return new Type( false );
    }


    public Object visitCallExpression( CallExpression c, Object arg )
    {
        String id = (String) c.name.visit( this, null );
        Vector<Type> t = (Vector<Type>)( c.args.visit( this, null ) );

        Declaration d = idTable.retrieve( id );
        if( d == null )
            System.out.println( id + " is not declared" );
        else if( !( d instanceof FunctionDeclaration ) )
            System.out.println( id + " is not a function" );
        else {
            FunctionDeclaration f = (FunctionDeclaration) d;
            c.decl = f;

            if( f.params.dec.size() != t.size() )
                System.out.println( "Incorrect number of arguments in call to " + id );
        }

        return new Type( true );
    }


    public Object visitUnaryExpression( UnaryExpression u, Object arg )
    {
        u.operand.visit( this, null );
        String operator = (String) u.operator.visit( this, null );

        if( !operator.equals( "+" ) && !operator.equals( "-" ) )
            System.out.println( "Only + or - is allowed as unary operator" );

        return new Type( true );
    }


    public Object visitIntLitExpression( IntLitExpression i, Object arg )
    {
        i.literal.visit( this, true );

        return new Type( true );
    }


    public Object visitExpList( ExpList e, Object arg )
    {
        Vector<Type> types = new Vector<Type>();

        for( Expression exp: e.exp )
            types.add( (Type) exp.visit( this, null ) );

        return types;
    }


    public Object visitIdentifier( Identifier i, Object arg )
    {
        return i.spelling;
    }

    @Override
    public Object visitIdList(IdList p, Object arg) {
        return null;
    }


    public Object visitIntegerLiteral( IntegerLiteral i, Object arg )
    {
        return null;
    }


    public Object visitOperator( Operator o, Object arg )
    {
        return o.spelling;
    }

    @Override
    public Object visitOrbitStatement(OrbitStatement p, Object arg) {
        return null;
    }

    @Override
    public Object visitPrimary(Primary p, Object arg) {
        return null;
    }

    @Override
    public Object visitProgramBlock(ProgramBlock p, Object arg) {
        return null;
    }

    @Override
    public Object visitReturnStatement(ReturnStatement p, Object arg) {
        return null;
    }

    @Override
    public Object visitReturnType(ReturnType p, Object arg) {
        return null;
    }

    @Override
    public Object visitStarDeclaration(StarDeclaration p, Object arg) {
        return null;
    }

    @Override
    public Object visitStarLiteral(StarLiteral p, Object arg) {
        return null;
    }
}