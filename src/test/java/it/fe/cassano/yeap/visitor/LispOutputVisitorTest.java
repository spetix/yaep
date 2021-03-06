package it.fe.cassano.yeap.visitor;

import it.fe.cassano.yeap.ast.Exp;
import it.fe.cassano.yeap.ast.FunExp;
import it.fe.cassano.yeap.ast.NumExp;
import it.fe.cassano.yeap.ast.RealExp;
import it.fe.cassano.yeap.visitors.LispOutputVisitor;

import java.util.Arrays;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class LispOutputVisitorTest extends TestCase {

	public LispOutputVisitorTest(String testName) {
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(LispOutputVisitorTest.class);
  
        return ts;
    }
    
    public void testNumExp()
    {
    	NumExp e = new NumExp(3);
    	LispOutputVisitor v = new LispOutputVisitor();
    	v.visit(e);
    	assertEquals("3",v.getVal());
    }
    
    public void testRealExp()
    {
    	RealExp e = new RealExp(-3.21d);
    	LispOutputVisitor v = new LispOutputVisitor();
    	v.visit(e);
    	assertEquals("-3.21",v.getVal());
    }
    
    public void testFunExp() throws Exception
    {
    	FunExp e = new FunExp("myfun", Arrays.asList( new Exp[]{ new NumExp(3), new RealExp(3.2d)}	));
    	LispOutputVisitor v = new LispOutputVisitor();
    	v.visit(e);
    	assertEquals("(myfun 3 3.2)",v.getVal());	
    }
	
}
