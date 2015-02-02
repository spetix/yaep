package it.fe.cassano.yeap.parser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ParserTest extends TestCase {
	
	public ParserTest(String testName) {
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	// ts.addTestSuite(ParserTest.class);
    	ts.addTestSuite(ExpressionParserTest.class);
    	ts.addTestSuite(TermParserTest.class);
    	ts.addTestSuite(FactorParserTest.class);
    	ts.addTestSuite(AssignmentParserTest.class);
    	ts.addTestSuite(OtherParserTest.class);
    	
        return ts;
    }
  
}
