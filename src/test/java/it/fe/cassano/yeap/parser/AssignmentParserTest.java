package it.fe.cassano.yeap.parser;

import it.fe.cassano.yeap.ccparser.ExpressionParser;
import it.fe.cassano.yeap.ccparser.ParseException;

import java.io.Reader;
import java.io.StringReader;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AssignmentParserTest extends TestCase {
	
	public AssignmentParserTest(String testName) {
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(AssignmentParserTest.class);
  
        return ts;
    }
    
  
    
    public void testParseAssigment() throws ParseException 
    {
    	Reader r = new StringReader("A = 3");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(A=(3))",p.initialGoal().toString());
    }
    

    public void testParseAssigment2() throws ParseException 
    {
    	Reader r = new StringReader("A = 3.0");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(A=(3.0))",p.initialGoal().toString());
    }
    
    public void testParseAssigment3() throws ParseException 
    {
    	Reader r = new StringReader("A = -3.0");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(A=(-(3.0)))",p.initialGoal().toString());
    }
    
    public void testParseAssigmentDouble() throws ParseException 
    {
    	Reader r = new StringReader("A=BCa= 3.0");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(A=(BCa=(3.0)))",p.initialGoal().toString());
    }
    
    public void testParseAssigmentWithUOP() throws ParseException 
    {
    	Reader r = new StringReader("A=-BCa= 3.0");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(A=(-(BCa=(3.0))))",p.initialGoal().toString());
    }
    
    public void testParseAssigmentWithFun() throws ParseException 
    {
    	Reader r = new StringReader("A=-f(3)");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(A=(-(f((3)))))",p.initialGoal().toString());
    }
}
