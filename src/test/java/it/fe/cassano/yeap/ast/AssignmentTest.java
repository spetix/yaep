package it.fe.cassano.yeap.ast;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AssignmentTest extends TestCase {
	
	public AssignmentTest(String testName) 
	{
		super(testName);
	}
	
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(AssignmentTest.class);
  
        return ts;
    }
    
    public void testBuildAssignment()
    {
    	Exp a = new AssignExp( new IdentExp("TEST"), new NumExp(1));
    	assertEquals("(TEST=(1))", a.toString());
    }
    
    
    public void testBuildAssignmentWithExpRV()
    {
    	Exp e = new PlusExp(new NumExp(1), new NumExp(1));
    	Exp a = new AssignExp( new IdentExp("TEST"), e);
    	assertEquals("(TEST=((1)+(1)))", a.toString());
    }
    
    
    

}
