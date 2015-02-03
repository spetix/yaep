package it.fe.cassano.yeap.parser;

import it.fe.cassano.yeap.ccparser.ExpressionParser;
import it.fe.cassano.yeap.ccparser.ParseException;

import java.io.Reader;
import java.io.StringReader;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FunctionDefineParserTest extends TestCase {
	
	public FunctionDefineParserTest(String testName) {
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(FunctionDefineParserTest.class);
  
        return ts;
    }
    
  
    
    public void testParseDefine() throws ParseException 
    {
    	Reader r = new StringReader("%sin = java.Math.sin(:RealExp):RealExp; sin(4.1)");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(sin=(java.Math.sin(RealExp)RealExp))",p.initialGoal().toString());
    }
    
    public void testParseDefine2() throws ParseException 
    {
    	Reader r = new StringReader("%sin = java.Math.sin(:RealExp):RealExp; %sin = java.Math.sin(:RealExp):RealExp; sin(4.1)");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(sin=(java.Math.sin(RealExp)RealExp))",p.initialGoal().toString());
    }
    
}
