package it.fe.cassano.astvisassignsample.visitor;

import it.fe.cassano.astvisassignsample.ast.NumExp;
import it.fe.cassano.astvisassignsample.ast.PlusExp;
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
        EvalWithAssignVisitor v = new EvalWithAssignVisitor();
        v.visit(sum);
        
        
    }

}
