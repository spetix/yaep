package it.fe.cassano.astvisassignsample.parser;

import it.fe.cassano.astvisassignsample.ccparser.ExpressionParser;
import it.fe.cassano.astvisassignsample.ccparser.ParseException;

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
    	assertEquals("(A=(3))",p.expr().toString());
    }
    

    public void testParseAssigment2() throws ParseException 
    {
    	Reader r = new StringReader("A = 3.0");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(A=(3.0))",p.expr().toString());
    }
    
    public void testParseAssigment3() throws ParseException 
    {
    	Reader r = new StringReader("A = -3.0");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(A=(-(3.0)))",p.assignment().toString());
    }
    
    public void testParseAssigmentDouble() throws ParseException 
    {
    	Reader r = new StringReader("A=BCa= 3.0");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(A=(BCa=(3.0)))",p.assignment().toString());
    }
    
    public void testParseAssigmentWithUOP() throws ParseException 
    {
    	Reader r = new StringReader("A=-BCa= 3.0");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(A=(-(BCa=(3.0))))",p.assignment().toString());
    }
    
    public void testParseAssigmentWithFun() throws ParseException 
    {
    	Reader r = new StringReader("A=-f()");
    	ExpressionParser p = new ExpressionParser(r);
    	assertEquals("(A=(-(f())))",p.assignment().toString());
    }
    
//    
//    public void testParseTerm2() throws IOException
//    {
//    	Reader r = new StringReader("( 12 * 1 )");
//    	ITokenizer t = new ExpressionTokenizer(r);
//    	ExpressionParser p = new ExpressionParser(t);
//    	assertEquals("((12)*(1))",p.parseTerm().toString());
//    	
//    }
//    
//    public void testParseTerm3() throws IOException
//    {
//    	Reader r = new StringReader("  1  ");
//    	ITokenizer t = new ExpressionTokenizer(r);
//    	ExpressionParser p = new ExpressionParser(t);
//    	assertEquals("(1)",p.parseTerm().toString());
//    	
//    }
//    
//    public void testParseTerm4() throws IOException
//    {
//    	Reader r = new StringReader("(  1  )");
//    	ITokenizer t = new ExpressionTokenizer(r);
//    	ExpressionParser p = new ExpressionParser(t);
//    	assertEquals("(1)",p.parseTerm().toString());
//    	
//    }
//    
//    
//    public void testParseTerm5() throws IOException
//    {
//    	Reader r = new StringReader("3 * ( 2 + 1 )");
//    	ITokenizer t = new ExpressionTokenizer(r);
//    	ExpressionParser p = new ExpressionParser(t);
//    	assertEquals("((3)*((2)+(1)))",p.parseTerm().toString());
//    	
//    }
//    
//    public void testParseTerm6() throws IOException
//    {
//    	Reader r = new StringReader("3 * ( 2 * ( 1 ) )");
//    	ITokenizer t = new ExpressionTokenizer(r);
//    	ExpressionParser p = new ExpressionParser(t);
//    	assertEquals("((3)*((2)*(1)))",p.parseTerm().toString());
//    	
//    }
//    
//    public void testExprSimpe() throws IOException
//    {
//        Reader valThis = new StringReader("1 + 1 - 1");
//        ITokenizer tok = new ExpressionTokenizer(valThis);
//        ExpressionParser p = new ExpressionParser(tok);
//        Exp expressionEval = p.parseExp();
//        assertEquals("(((1)+(1))-(1))",expressionEval.toString());
//    }
//    
//    public void testExprSimpleRightPlus() throws IOException
//    {
//        Reader valThis = new StringReader("1 + ( 1 - 1 )");
//        ITokenizer tok = new ExpressionTokenizer(valThis);
//        ExpressionParser p = new ExpressionParser(tok);
//        Exp expressionEval = p.parseExp();
//        assertEquals("((1)+((1)-(1)))",expressionEval.toString());
//    }
//    
//    public void testExprSimpleRightMinus() throws IOException
//    {
//        Reader valThis = new StringReader("1 - ( 1 - 1 )");
//        ITokenizer tok = new ExpressionTokenizer(valThis);
//        ExpressionParser p = new ExpressionParser(tok);
//        Exp expressionEval = p.parseExp();
//        assertEquals("((1)-((1)-(1)))",expressionEval.toString());
//    }
//    
//    
//    public void testExpr() throws IOException
//    {
//        assertTrue( true );
//        Reader valThis = new StringReader("( 12     * ( ( 5 + 1 )  - 1 ) )");
//        ITokenizer tok = new ExpressionTokenizer(valThis);
//        ExpressionParser p = new ExpressionParser(tok);
//        Exp expressionEval = p.parseExp();
//        assertEquals("((12)*(((5)+(1))-(1)))",expressionEval.toString());
//    }
//    
//    public void testExprOnlyNeededBrackets() throws IOException
//    {
//        assertTrue( true );
//        Reader valThis = new StringReader("12     * ( ( 5 + 1 )  - 1 )");
//        ITokenizer tok = new ExpressionTokenizer(valThis);
//        ExpressionParser p = new ExpressionParser(tok);
//        Exp expressionEval = p.parseExp();
//        assertEquals("((12)*(((5)+(1))-(1)))",expressionEval.toString());
//    }
//    
//    public void testExprOnlyNeededBracketsBut() throws IOException
//    {
//        assertTrue( true );
//        Reader valThis = new StringReader("12     * ( 5 + 1  - 1 )");
//        ITokenizer tok = new ExpressionTokenizer(valThis);
//        ExpressionParser p = new ExpressionParser(tok);
//        Exp expressionEval = p.parseExp();
//        assertEquals("((12)*(((5)+(1))-(1)))",expressionEval.toString());
//    }
//    
//    public void testExprRightAssSimplerMinus() throws IOException
//    {
//        assertTrue( true );
//        Reader valThis = new StringReader("( 12     - ( 5  - 1 ) )");
//        ITokenizer tok = new ExpressionTokenizer(valThis);
//        ExpressionParser p = new ExpressionParser(tok);
//        Exp expressionEval = p.parseExp();
//        assertEquals("((12)-((5)-(1)))",expressionEval.toString());
//    }
//    
//    public void testExprRightAssSimplerMul() throws IOException
//    {
//        assertTrue( true );
//        Reader valThis = new StringReader("( 12     * ( 5  * 1 ) )");
//        ITokenizer tok = new ExpressionTokenizer(valThis);
//        ExpressionParser p = new ExpressionParser(tok);
//        Exp expressionEval = p.parseExp();
//        assertEquals("((12)*((5)*(1)))",expressionEval.toString());
//    }
//    
//    
//    public void testExprRightAss() throws IOException
//    {
//        assertTrue( true );
//        Reader valThis = new StringReader("( 12     - ( ( 5 + 1 )  - 1 ) )");
//        ITokenizer tok = new ExpressionTokenizer(valThis);
//        ExpressionParser p = new ExpressionParser(tok);
//        Exp expressionEval = p.parseExp();
//        assertEquals("((12)-(((5)+(1))-(1)))",expressionEval.toString());
//    }
//    
//    public void testExpr2() throws IOException
//    {
//        assertTrue( true );
//        Reader r = new StringReader("12 *  ( 5 + 1 )  - 1 ");
//        ExpressionParser p = new ExpressionParser(r);
//        Exp expressionEval = p.parseExp();
//        assertEquals("(((12)*((5)+(1)))-(1))",expressionEval.toString());
//    }
//    
//    public void testExpr3() throws IOException
//    {
//        assertTrue( true );
//        Reader r = new StringReader("( 12 *  ( 5 + 1 ) ) - 1 ");
//       ExpressionParser p = new ExpressionParser(r);
//        Exp expressionEval = p.parseExp();
//        assertEquals("(((12)*((5)+(1)))-(1))",expressionEval.toString());
//    }
//    
//    public void testParseTermAsExpr1() throws IOException
//    {
//    	Reader r = new StringReader("3 * ( 2 * ( 1 ) )");
//    	ExpressionParser p = new ExpressionParser(r);
//    	assertEquals("((3)*((2)*(1)))",p.parseExp().toString());
//    	
//    }
//    
//    public void testParseFactorBrackets() throws IOException
//    {
//    	Reader r = new StringReader("( (  ( 3 ) ) )");
//    	ExpressionParser p = new ExpressionParser(r);
//    	assertEquals("(3)",p.parseFactor().toString());
//    	
//    }
//    
//    public void testParseTermBrackets() throws IOException
//    {
//    	Reader r = new StringReader("( (  ( 3 ) ) )");
//    	ExpressionParser p = new ExpressionParser(r);
//    	assertEquals("(3)",p.parseTerm().toString());
//    	
//    }
//    
//    public void testParseTermBracketsDiv() throws IOException
//    {
//    	Reader r = new StringReader("( (  ( 3 / 2 ) ) )");
//    	ExpressionParser p = new ExpressionParser(r);
//    	assertEquals("((3)/(2))",p.parseTerm().toString());
//    	
//    }
//    
//    public void testParseFactorBracketsDiv() throws IOException
//    {
//    	Reader r = new StringReader("( (  ( 3 / 2 ) ) )");
//    	ExpressionParser p = new ExpressionParser(r);
//    	assertEquals("((3)/(2))",p.parseFactor().toString());
//    	
//    }
}
