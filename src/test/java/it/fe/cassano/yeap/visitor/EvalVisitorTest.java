package it.fe.cassano.yeap.visitor;

import it.fe.cassano.yeap.ast.NumExp;
import it.fe.cassano.yeap.ast.PlusExp;
import it.fe.cassano.yeap.visitors.EvalVisitor;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class EvalVisitorTest extends TestCase {
	
	public EvalVisitorTest(String testName)
	{
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(EvalVisitorTest.class);
  
        return ts;
    }
    
    public void testSum()
    {
    	PlusExp sum = new PlusExp(new NumExp(3),new NumExp(5));
    	System.out.println(sum.toString());
        assertTrue( sum.toString().equals("((3)+(5))") );
        EvalVisitor v = new EvalVisitor();
        v.visit(sum);
        assertEquals(((Integer)v.getVal()).intValue(),8);             
    }

}
