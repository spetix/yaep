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

public class ExpressionParserTest extends TestCase {
	
	public ExpressionParserTest(String testName) {
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(ExpressionParserTest.class);
  
        return ts;
    }
    
    
    public void testParseDoublePlus() throws IOException, ParseException {
    	Reader r = new StringReader("1+1+1;");
    	ExpressionParser t = new ExpressionParser(r);
    	assertEquals("(((1)+(1))+(1))",t.s().toString());
    }
    
    public void testParseDoubleMinus() throws IOException, ParseException {
    	Reader r = new StringReader("1- 1- 1;");
    	ExpressionParser t = new ExpressionParser(r);
    	assertEquals("(((1)-(1))-(1))",t.s().toString());
    }
    
    public void testParseDoubleMinus2() throws IOException, ParseException {
    	Reader r = new StringReader("1-1-1;");
    	ExpressionParser t = new ExpressionParser(r);
    	Exp e = t.s();
    	assertEquals("(((1)-(1))-(1))",e.toString());
    }
    
    public void testParseDoubleMinus3() throws IOException, ParseException {
    	Reader r = new StringReader("1-1--1;");
    	ExpressionParser t = new ExpressionParser(r);
    	Exp e = t.s();
    	assertEquals("(((1)-(1))-(-(1)))",e.toString());
    }
    
    public void testParseDoubleExpr() throws IOException, ParseException {
    	Reader r = new StringReader("1-1+1;");
    	ExpressionParser t = new ExpressionParser(r);
    	Exp e = t.s();
    	assertEquals("(((1)-(1))+(1))",e.toString());
    }
    
    public void testParseDoubleExpr2() throws IOException, ParseException {
    	Reader r = new StringReader("1-1+-1;");
    	ExpressionParser t = new ExpressionParser(r);
    	Exp e = t.s();
    	assertEquals("(((1)-(1))+(-(1)))",e.toString());
    }
    
    public void testExprOnlyNeededBrackets() throws IOException, ParseException
    {
        assertTrue( true );
        Reader valThis = new StringReader("12     * ( ( 5 + 1 )  - 1 );");
        ExpressionParser p = new ExpressionParser(valThis);
        Exp expressionEval = p.s();
        assertEquals("((12)*(((5)+(1))-(1)))",expressionEval.toString());
    }
    
    public void testExprOnlyNeededBracketsBut() throws IOException, ParseException
    {
        assertTrue( true );
        Reader valThis = new StringReader("12     * ( 5 + 1  - 1 );");
        ExpressionParser p = new ExpressionParser(valThis);
        Exp expressionEval = p.s();
        assertEquals("((12)*(((5)+(1))-(1)))",expressionEval.toString());
    }
    
    
    public void testExprRightAssSimplerMinus() throws IOException, ParseException
    {
        assertTrue( true );
        Reader valThis = new StringReader("( 12     - ( 5  - 1 ) );");
        ExpressionParser p = new ExpressionParser(valThis);
        Exp expressionEval = p.s();
        assertEquals("((12)-((5)-(1)))",expressionEval.toString());
    }
    
    public void testExprRightAssSimplerMul() throws IOException, ParseException
    {
        assertTrue( true );
        Reader valThis = new StringReader("( 12     * ( 5  * 1 ) );");
        ExpressionParser p = new ExpressionParser(valThis);
        Exp expressionEval = p.s();
        assertEquals("((12)*((5)*(1)))",expressionEval.toString());
    }
    
    
    public void testExprRightAss() throws IOException, ParseException
    {
        assertTrue( true );
        Reader valThis = new StringReader("( 12     - ( ( 5 + 1 )  - 1 ) );");
        ExpressionParser p = new ExpressionParser(valThis);
        Exp expressionEval = p.s();
        assertEquals("((12)-(((5)+(1))-(1)))",expressionEval.toString());
    }
    
    public void testExpr2() throws IOException, ParseException
    {
        assertTrue( true );
        Reader r = new StringReader("12 *  ( 5 + 1 )  - 1 ;");
        ExpressionParser p = new ExpressionParser(r);
        Exp expressionEval = p.s();
        assertEquals("(((12)*((5)+(1)))-(1))",expressionEval.toString());
    }
    
    public void testExpr3() throws IOException, ParseException
    {
        assertTrue( true );
        Reader r = new StringReader("( 12 *  ( 5 + 1 ) ) - 1 ;");
       ExpressionParser p = new ExpressionParser(r);
        Exp expressionEval = p.s();
        assertEquals("(((12)*((5)+(1)))-(1))",expressionEval.toString());
    }
    
    public void testParseTermAsExpr1() throws IOException, ParseException
    {
    	Reader r = new StringReader("3 * ( 2 * ( 1 ) );");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("((3)*((2)*(1)))",p.s().toString());
    	
    }
    
    public void testParseFactorBrackets() throws IOException, ParseException
    {
    	Reader r = new StringReader("( (  ( 3 ) ) );");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(3)",p.s().toString());
    	
    }
    
    public void testParseTermBrackets() throws IOException, ParseException
    {
    	Reader r = new StringReader("( (  ( 3 ) ) );");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(3)",p.s().toString());
    	
    }
    
    public void testParseTermBracketsDiv() throws IOException, ParseException
    {
    	Reader r = new StringReader("( (  ( 3 / 2 ) ) );");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("((3)/(2))",p.s().toString());
    	
    }
    
    public void testParseFactorBracketsDiv() throws IOException, ParseException
    {
    	Reader r = new StringReader("( (  ( 3 / 2 ) ) );");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("((3)/(2))",p.s().toString());
    	
    }
}
