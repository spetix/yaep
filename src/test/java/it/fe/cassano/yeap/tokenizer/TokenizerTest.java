package it.fe.cassano.yeap.tokenizer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class TokenizerTest extends TestCase {

	public TokenizerTest(String testName) {
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(TokenizerNumbersTest.class);
    	ts.addTestSuite(TokenizerIdentTest.class);
    	ts.addTestSuite(TokenizerFunctionNameTest.class);  
    	ts.addTestSuite(TokenizerExpressionsTest.class);  
    	ts.addTestSuite(TokenizerFunctionNameTest.class);  
        
    	  
        return ts;
    }
}
