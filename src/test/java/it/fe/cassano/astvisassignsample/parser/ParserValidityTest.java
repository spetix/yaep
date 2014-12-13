package it.fe.cassano.astvisassignsample.parser;

import it.fe.cassano.astvisassignsample.ccparser.ExpressionParser;
import it.fe.cassano.astvisassignsample.ccparser.ParseException;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ParserValidityTest extends TestCase {
	
	public ParserValidityTest(String testName) {
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(ParserValidityTest.class);
  
        return ts;
    }
    
    public void testSimpleToken() throws IOException, ParseException
    {
    	Reader r = new StringReader("  1 ");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    	
    }
    
    public void testParsePlusOp() throws IOException, ParseException
    {
    	Reader r = new StringReader("1+3");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
    
    public void testParsePlusOpTernary() throws IOException, ParseException
    {
    	Reader r = new StringReader("1+3+1");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
    
    
    public void testParseMinusOp() throws IOException, ParseException
    {
    	Reader r = new StringReader("1-3");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
    
    public void testParseMinusOpTernary() throws IOException, ParseException
    {
    	Reader r = new StringReader("1-3-1");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
    
    
    public void testParseMulOp() throws IOException, ParseException
    {
    	Reader r = new StringReader("1*3");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    	
    }
    
    public void testParseMulOpTernary() throws IOException, ParseException
    {
    	Reader r = new StringReader("1*3*2");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    	
    }
    
    public void testParseDivOp() throws IOException, ParseException
    {
    	Reader r = new StringReader("1/3");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
    
    public void testParseAssign() throws IOException, ParseException
    {
    	Reader r = new StringReader("A=3");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
    
    public void testParseIdentWordAssign() throws IOException, ParseException
    {
    	Reader r = new StringReader("MyVar=6");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
    
    public void testParseIdentWordAssignFail() throws IOException, ParseException
    {
    	Reader r = new StringReader("myVar=6");
    	ExpressionParser p = new ExpressionParser(r);
    	assertFalse(p.isValidExpression());
    }
    
    public void testComplexExpression() throws ParseException 
    {
    	Reader r = new StringReader("1+3*2");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
    
    public void testBracketsExpression() throws ParseException 
    {
    	Reader r = new StringReader("(1+3)*2");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
    
    public void testInvalidUnbalancedBrackets() throws ParseException 
    {
    	Reader r = new StringReader("(1+3))*2");
    	ExpressionParser p = new ExpressionParser(r);
    	assertFalse(p.isValidExpression());
    }
    
    public void testLoongAssignmentChain() throws ParseException 
    {
    	Reader r = new StringReader("A=B=2");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
    
    public void testAssignIdentChainTernary() throws ParseException 
    {
    	Reader r = new StringReader("A=B=C=3+1");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
    
    public void testAssignIdentSimpleChain() throws ParseException 
    {
    	Reader r = new StringReader("A=B+3");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
    
    public void testAssignIdentChain() throws ParseException 
    {
    	Reader r = new StringReader("A=B=3+1");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
    
    public void testLooongAssignmentChain() throws ParseException 
    {
    	Reader r = new StringReader("A=B=(1+C)*2");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
    
    public void testLooongAssignmentChainStrange() throws ParseException 
    {
    	Reader r = new StringReader("A=3*((B=(1+C))*2)");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
    
    public void testSequence() throws ParseException 
    {
    	Reader r = new StringReader("A=3,B=3*2");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
    
    public void testLongSequence() throws ParseException 
    {
    	Reader r = new StringReader("A=3,B=3*2,C=A*B,D=3,E=D-A*B+D/D");
    	ExpressionParser p = new ExpressionParser(r);
    	assertTrue(p.isValidExpression());
    }
}
