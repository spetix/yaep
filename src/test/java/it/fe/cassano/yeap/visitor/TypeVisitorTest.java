package it.fe.cassano.yeap.visitor;

import it.fe.cassano.yeap.MockEnvironment;
import it.fe.cassano.yeap.ast.AssignExp;
import it.fe.cassano.yeap.ast.DivideExp;
import it.fe.cassano.yeap.ast.IdentExp;
import it.fe.cassano.yeap.ast.MinusExp;
import it.fe.cassano.yeap.ast.NumExp;
import it.fe.cassano.yeap.ast.PlusExp;
import it.fe.cassano.yeap.ast.ProductExp;
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
    
    public void testSumIntReal()
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
  
    public void testSumRealReal()
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	RealExp r1  = new RealExp(3.5f);
    	RealExp r2  = new RealExp(3.5f);
    	
    	
    	PlusExp s = new PlusExp(r1,r2);
    	visitor.visit(s);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    }
    
    public void testSumIntInt()
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	NumExp n2 = new NumExp(3);
    	PlusExp s = new PlusExp(n1,n2);
    	visitor.visit(s);
    	assertEquals(ExpType.NumExp,(ExpType)visitor.getVal());
    	
    }
    
    public void testMinusIntReal()
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
  
    public void testMinusRealReal()
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	RealExp r1  = new RealExp(3.5f);
    	RealExp r2  = new RealExp(3.5f);
    	
    	
    	MinusExp m = new MinusExp(r1,r2);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    }
    
    public void testMinusIntInt()
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	NumExp n2 = new NumExp(3);
    	MinusExp m = new MinusExp(n1,n2);
    	visitor.visit(m);
    	assertEquals(ExpType.NumExp,(ExpType)visitor.getVal());
    	
    }
    
    /* Mul */
    
    public void testProductIntReal()
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	RealExp r2  = new RealExp(3.5f);
    	
    	
    	ProductExp m = new ProductExp(n1,r2);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    	m = new ProductExp(r2,n1);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    }
  
    public void testProductRealReal()
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	RealExp r1  = new RealExp(3.5f);
    	RealExp r2  = new RealExp(3.5f);
    	
    	
    	ProductExp m = new ProductExp(r1,r2);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    }
    
    public void testProductIntInt()
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	NumExp n2 = new NumExp(3);
    	ProductExp m = new ProductExp(n1,n2);
    	visitor.visit(m);
    	assertEquals(ExpType.NumExp,(ExpType)visitor.getVal());
    	
    }
    
    /* DIV */
    
    public void testDivideIntReal()
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	RealExp r2  = new RealExp(3.5f);
    	
    	
    	DivideExp m = new DivideExp(n1,r2);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    	m = new DivideExp(r2,n1);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    }
  
    public void testDivideRealReal()
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	RealExp r1  = new RealExp(3.5f);
    	RealExp r2  = new RealExp(3.5f);
    	
    	
    	DivideExp m = new DivideExp(r1,r2);
    	visitor.visit(m);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    }
    
    public void testDivIntInt()
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	NumExp n2 = new NumExp(3);
    	DivideExp m = new DivideExp(n1,n2);
    	visitor.visit(m);
    	assertEquals(ExpType.NumExp,(ExpType)visitor.getVal());
    	
    }
    
    /* UNARY Minus */
    
    public void testUnaryMinusReal()
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
    
    public void testMinusInt()
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
    
    public void testAssignmentInt()
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	NumExp n1  = new NumExp(3);
    	AssignExp e = new AssignExp(new IdentExp("IntVal"), n1);
    	visitor.visit(e);
    	assertEquals(ExpType.NumExp,(ExpType)visitor.getVal());
    	
    }
    
    public void testAssignmentReal()
    {
    	IVisitor visitor = new TypeVisitor(new MockEnvironment());
    	
    	RealExp r1  = new RealExp(3.1f);
    	AssignExp e = new AssignExp(new IdentExp("RealVal"), r1);
    	visitor.visit(e);
    	assertEquals(ExpType.RealExp,(ExpType)visitor.getVal());
    	
    }
    	
}
