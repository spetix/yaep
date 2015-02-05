package it.fe.cassano.yeap.visitor;

import it.fe.cassano.yeap.MockEnvironment;
import it.fe.cassano.yeap.ast.AssignExp;
import it.fe.cassano.yeap.ast.DivExp;
import it.fe.cassano.yeap.ast.IdentExp;
import it.fe.cassano.yeap.ast.MinusExp;
import it.fe.cassano.yeap.ast.NumExp;
import it.fe.cassano.yeap.ast.PlusExp;
import it.fe.cassano.yeap.ast.MulExp;
import it.fe.cassano.yeap.ast.RealExp;
import it.fe.cassano.yeap.ast.UnaryMinusExp;
import it.fe.cassano.yeap.visitors.ExpType;
import it.fe.cassano.yeap.visitors.IVisitor;
import it.fe.cassano.yeap.visitors.TypeVisitor;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TypeVisitorTest extends TestCase {

    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(TypeVisitorTest.class);
    	  
        return ts;
    }
    
    public void testRealNum()
    {
    	RealExp r  = new RealExp(3.5f);
    	
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	visitor.visit(r);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    	
    }
    
    public void testIntNum()
    {
    	NumExp n  = new NumExp(3);
    	
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	visitor.visit(n);
    	assertEquals(ExpType.NumExp,(ExpType)visitor.getVal());	
    }
    
    public void testSumIntReal() throws Exception
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	RealExp r2  = new RealExp(3.5f);
    	
    	
    	PlusExp s = new PlusExp(n1,r2);
    	visitor.visit(s);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    	s = new PlusExp(r2,n1);
    	visitor.visit(s);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    }
  
    public void testSumRealReal() throws Exception
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	RealExp r1  = new RealExp(3.5f);
    	RealExp r2  = new RealExp(3.5f);
    	
    	
    	PlusExp s = new PlusExp(r1,r2);
    	visitor.visit(s);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    }
    
    public void testSumIntInt() throws Exception
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	NumExp n2 = new NumExp(3);
    	PlusExp s = new PlusExp(n1,n2);
    	visitor.visit(s);
    	assertEquals(ExpType.NumExp,(ExpType)visitor.getVal());
    	
    }
    
    public void testMinusIntReal() throws Exception
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	RealExp r2  = new RealExp(3.5f);
    	
    	
    	MinusExp m = new MinusExp(n1,r2);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    	m = new MinusExp(r2,n1);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    }
  
    public void testMinusRealReal() throws Exception
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	RealExp r1  = new RealExp(3.5f);
    	RealExp r2  = new RealExp(3.5f);
    	
    	
    	MinusExp m = new MinusExp(r1,r2);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    }
    
    public void testMinusIntInt() throws Exception
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	NumExp n2 = new NumExp(3);
    	MinusExp m = new MinusExp(n1,n2);
    	visitor.visit(m);
    	assertEquals(ExpType.NumExp,(ExpType)visitor.getVal());
    	
    }
    
    /* Mul */
    
    public void testProductIntReal() throws Exception
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	RealExp r2  = new RealExp(3.5f);
    	
    	
    	MulExp m = new MulExp(n1,r2);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    	m = new MulExp(r2,n1);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    }
  
    public void testProductRealReal() throws Exception
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	RealExp r1  = new RealExp(3.5f);
    	RealExp r2  = new RealExp(3.5f);
    	
    	
    	MulExp m = new MulExp(r1,r2);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    }
    
    public void testProductIntInt() throws Exception
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	NumExp n2 = new NumExp(3);
    	MulExp m = new MulExp(n1,n2);
    	visitor.visit(m);
    	assertEquals(ExpType.NumExp,(ExpType)visitor.getVal());
    	
    }
    
    /* DIV */
    
    public void testDivideIntReal() throws Exception
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	RealExp r2  = new RealExp(3.5f);
    	
    	
    	DivExp m = new DivExp(n1,r2);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    	m = new DivExp(r2,n1);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    }
  
    public void testDivideRealReal() throws Exception
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	RealExp r1  = new RealExp(3.5f);
    	RealExp r2  = new RealExp(3.5f);
    	
    	
    	DivExp m = new DivExp(r1,r2);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    }
    
    public void testDivIntInt() throws Exception
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	NumExp n2 = new NumExp(3);
    	DivExp m = new DivExp(n1,n2);
    	visitor.visit(m);
    	assertEquals(ExpType.NumExp,(ExpType)visitor.getVal());
    	
    }
    
    /* UNARY Minus */
    
    public void testUnaryMinusReal() throws Exception
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	RealExp r1  = new RealExp(3.5f);
    	RealExp r2  = new RealExp(-3.5f);
    	
    	
    	UnaryMinusExp m = new UnaryMinusExp(r1);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    	
    	m = new UnaryMinusExp(r2);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    }
    
    public void testMinusInt() throws Exception
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	UnaryMinusExp m = new UnaryMinusExp(n1);
    	visitor.visit(m);
    	assertEquals(ExpType.NumExp,(ExpType)visitor.getVal());
    	NumExp n2 = new NumExp(-3);
    	m = new UnaryMinusExp(n2);
    	assertEquals(ExpType.NumExp,(ExpType)visitor.getVal());
    }
    	
    	
    /* ASSIGNMENT */
    
    public void testAssignmentInt() throws Exception
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	AssignExp e = new AssignExp(new IdentExp("IntVal"), n1);
    	visitor.visit(e);
    	assertEquals(ExpType.NumExp,(ExpType)visitor.getVal());
    	
    }
    
    public void testAssignmentReal() throws Exception
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	RealExp r1  = new RealExp(3.1f);
    	AssignExp e = new AssignExp(new IdentExp("RealVal"), r1);
    	visitor.visit(e);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    	
    }
    	
}
