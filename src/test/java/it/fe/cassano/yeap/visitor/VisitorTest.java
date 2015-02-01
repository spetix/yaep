package it.fe.cassano.yeap.visitor;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class VisitorTest extends TestCase {

	public VisitorTest(String testName) {
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(LispOutputVisitorTest.class);
    	ts.addTestSuite(EvalVisitorTest.class);
    	ts.addTestSuite(EvalWithAssignVisitorTest.class);
    	 ts.addTestSuite(TypeVisitorTest.class);
        return ts;
    }
    
  
}
