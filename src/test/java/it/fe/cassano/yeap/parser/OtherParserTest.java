package it.fe.cassano.yeap.parser;

import it.fe.cassano.yeap.ast.Exp;
import it.fe.cassano.yeap.ccparser.ExpressionParser;
import it.fe.cassano.yeap.ccparser.ParseException;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class OtherParserTest extends TestCase {
	
	public OtherParserTest(String testName) {
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(OtherParserTest.class);
  
        return ts;
    }
    
    public void testExprSimpleRightMinus() throws IOException, ParseException
    {
    	Reader valThis = new StringReader("1 - ( 1 - 1 )");
        ExpressionParser p = new ExpressionParser(valThis);
        //assertTrue(p.isValidExpression());
        assertEquals("((1)-((1)-(1)))",p.expr().toString());
    	
    }
    
    public void testUltraViolence() throws IOException, ParseException
    {
    
        Reader valThis = new StringReader("A=3,1 - ( a(3.4,-1) - 1 )");
        ExpressionParser p = new ExpressionParser(valThis);
        //assertTrue(p.isValidExpression());
        Exp e = p.initialGoal();
        assertEquals("((A=(3)),((1)-((a((3.4),(-(1))))-(1))))",e.toString());
    	
    }
    
    public void testLongTerms() throws IOException, ParseException
    {
    
        Reader valThis = new StringReader("1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1");
        ExpressionParser p = new ExpressionParser(valThis);
        //assertTrue(p.isValidExpression());
        Exp e = p.expr();
        assertEquals("((((((((((((((((((((((((((((((1)-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))-(1))",e.toString());
    	
    }
    
    
}
