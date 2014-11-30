package it.fe.cassano.astvisassignsample.parser;

import it.fe.cassano.astvisassignsample.ast.Exp;
import it.fe.cassano.astvisassignsample.tokenizer.ExpressionTokenizer;
import it.fe.cassano.astvisassignsample.tokenizer.ITokenizer;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

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
    	ts.addTestSuite(ParserTest.class);
  
        return ts;
    }
    
    public void testParseFactor() throws IOException
    {
    	Reader r = new StringReader("  1  ");
    	ITokenizer t = new ExpressionTokenizer(r);
    	Parser p = new Parser(t);
    	assertEquals("(1)",p.parseFactor().toString());
    	
    }
    
    public void testParseFactor2() throws IOException
    {
    	Reader r = new StringReader("(  1  )");
    	ITokenizer t = new ExpressionTokenizer(r);
    	Parser p = new Parser(t);
    	assertEquals("(1)",p.parseFactor().toString());
    	
    }
    
    public void testParseFactor3() throws IOException
    {
    	Reader r = new StringReader("( 1 + 1 )");
    	ITokenizer t = new ExpressionTokenizer(r);
    	Parser p = new Parser(t);
    	assertEquals("((1)+(1))",p.parseFactor().toString());
    	
    }
    
    public void testParseFactor4() throws IOException
    {
    	Reader r = new StringReader("( 1 * 1 )");
    	ITokenizer t = new ExpressionTokenizer(r);
    	Parser p = new Parser(t);
    	assertEquals("((1)*(1))",p.parseFactor().toString());
    	
    }
    
    
    public void testParseTerm() throws IOException
    {
    	Reader r = new StringReader("( 1 * 1 )");
    	ITokenizer t = new ExpressionTokenizer(r);
    	Parser p = new Parser(t);
    	assertEquals("((1)*(1))",p.parseTerm().toString());
    	
    }
    
    public void testParseTerm2() throws IOException
    {
    	Reader r = new StringReader("( 12 * 1 )");
    	ITokenizer t = new ExpressionTokenizer(r);
    	Parser p = new Parser(t);
    	assertEquals("((12)*(1))",p.parseTerm().toString());
    	
    }
    
    public void testParseTerm3() throws IOException
    {
    	Reader r = new StringReader("  1  ");
    	ITokenizer t = new ExpressionTokenizer(r);
    	Parser p = new Parser(t);
    	assertEquals("(1)",p.parseTerm().toString());
    	
    }
    
    public void testParseTerm4() throws IOException
    {
    	Reader r = new StringReader("(  1  )");
    	ITokenizer t = new ExpressionTokenizer(r);
    	Parser p = new Parser(t);
    	assertEquals("(1)",p.parseTerm().toString());
    	
    }
    
    
    public void testParseTerm5() throws IOException
    {
    	Reader r = new StringReader("3 * ( 2 + 1 )");
    	ITokenizer t = new ExpressionTokenizer(r);
    	Parser p = new Parser(t);
    	assertEquals("((3)*((2)+(1)))",p.parseTerm().toString());
    	
    }
    
    public void testParseTerm6() throws IOException
    {
    	Reader r = new StringReader("3 * ( 2 * ( 1 ) )");
    	ITokenizer t = new ExpressionTokenizer(r);
    	Parser p = new Parser(t);
    	assertEquals("((3)*((2)*(1)))",p.parseTerm().toString());
    	
    }
    
    public void testExprSimpe() throws IOException
    {
        Reader valThis = new StringReader("1 + 1 - 1");
        ITokenizer tok = new ExpressionTokenizer(valThis);
        Parser p = new Parser(tok);
        Exp expressionEval = p.parseExp();
        assertEquals("(((1)+(1))-(1))",expressionEval.toString());
    }
    
    public void testExprSimpleRightPlus() throws IOException
    {
        Reader valThis = new StringReader("1 + ( 1 - 1 )");
        ITokenizer tok = new ExpressionTokenizer(valThis);
        Parser p = new Parser(tok);
        Exp expressionEval = p.parseExp();
        assertEquals("((1)+((1)-(1)))",expressionEval.toString());
    }
    
    public void testExprSimpleRightMinus() throws IOException
    {
        Reader valThis = new StringReader("1 - ( 1 - 1 )");
        ITokenizer tok = new ExpressionTokenizer(valThis);
        Parser p = new Parser(tok);
        Exp expressionEval = p.parseExp();
        assertEquals("((1)-((1)-(1)))",expressionEval.toString());
    }
    
    
    public void testExpr() throws IOException
    {
        assertTrue( true );
        Reader valThis = new StringReader("( 12     * ( ( 5 + 1 )  - 1 ) )");
        ITokenizer tok = new ExpressionTokenizer(valThis);
        Parser p = new Parser(tok);
        Exp expressionEval = p.parseExp();
        assertEquals("((12)*(((5)+(1))-(1)))",expressionEval.toString());
    }
    
    public void testExprOnlyNeededBrackets() throws IOException
    {
        assertTrue( true );
        Reader valThis = new StringReader("12     * ( ( 5 + 1 )  - 1 )");
        ITokenizer tok = new ExpressionTokenizer(valThis);
        Parser p = new Parser(tok);
        Exp expressionEval = p.parseExp();
        assertEquals("((12)*(((5)+(1))-(1)))",expressionEval.toString());
    }
    
    public void testExprOnlyNeededBracketsBut() throws IOException
    {
        assertTrue( true );
        Reader valThis = new StringReader("12     * ( 5 + 1  - 1 )");
        ITokenizer tok = new ExpressionTokenizer(valThis);
        Parser p = new Parser(tok);
        Exp expressionEval = p.parseExp();
        assertEquals("((12)*(((5)+(1))-(1)))",expressionEval.toString());
    }
    
    public void testExprRightAssSimplerMinus() throws IOException
    {
        assertTrue( true );
        Reader valThis = new StringReader("( 12     - ( 5  - 1 ) )");
        ITokenizer tok = new ExpressionTokenizer(valThis);
        Parser p = new Parser(tok);
        Exp expressionEval = p.parseExp();
        assertEquals("((12)-((5)-(1)))",expressionEval.toString());
    }
    
    public void testExprRightAssSimplerMul() throws IOException
    {
        assertTrue( true );
        Reader valThis = new StringReader("( 12     * ( 5  * 1 ) )");
        ITokenizer tok = new ExpressionTokenizer(valThis);
        Parser p = new Parser(tok);
        Exp expressionEval = p.parseExp();
        assertEquals("((12)*((5)*(1)))",expressionEval.toString());
    }
    
    
    public void testExprRightAss() throws IOException
    {
        assertTrue( true );
        Reader valThis = new StringReader("( 12     - ( ( 5 + 1 )  - 1 ) )");
        ITokenizer tok = new ExpressionTokenizer(valThis);
        Parser p = new Parser(tok);
        Exp expressionEval = p.parseExp();
        assertEquals("((12)-(((5)+(1))-(1)))",expressionEval.toString());
    }
    
    public void testExpr2() throws IOException
    {
        assertTrue( true );
        Reader valThis = new StringReader("12 *  ( 5 + 1 )  - 1 ");
        ITokenizer tok = new ExpressionTokenizer(valThis);
        Parser p = new Parser(tok);
        Exp expressionEval = p.parseExp();
        assertEquals("(((12)*((5)+(1)))-(1))",expressionEval.toString());
    }
    
    public void testExpr3() throws IOException
    {
        assertTrue( true );
        Reader valThis = new StringReader("( 12 *  ( 5 + 1 ) ) - 1 ");
        ITokenizer tok = new ExpressionTokenizer(valThis);
        Parser p = new Parser(tok);
        Exp expressionEval = p.parseExp();
        assertEquals("(((12)*((5)+(1)))-(1))",expressionEval.toString());
    }
    
    public void testParseTermAsExpr1() throws IOException
    {
    	Reader r = new StringReader("3 * ( 2 * ( 1 ) )");
    	ITokenizer t = new ExpressionTokenizer(r);
    	Parser p = new Parser(t);
    	assertEquals("((3)*((2)*(1)))",p.parseExp().toString());
    	
    }
    
    public void testParseFactorBrackets() throws IOException
    {
    	Reader r = new StringReader("( (  ( 3 ) ) )");
    	ITokenizer t = new ExpressionTokenizer(r);
    	Parser p = new Parser(t);
    	assertEquals("(3)",p.parseFactor().toString());
    	
    }
    
    public void testParseTermBrackets() throws IOException
    {
    	Reader r = new StringReader("( (  ( 3 ) ) )");
    	ITokenizer t = new ExpressionTokenizer(r);
    	Parser p = new Parser(t);
    	assertEquals("(3)",p.parseTerm().toString());
    	
    }
    
    public void testParseTermBracketsDiv() throws IOException
    {
    	Reader r = new StringReader("( (  ( 3 / 2 ) ) )");
    	ITokenizer t = new ExpressionTokenizer(r);
    	Parser p = new Parser(t);
    	assertEquals("((3)/(2))",p.parseTerm().toString());
    	
    }
    
    public void testParseFactorBracketsDiv() throws IOException
    {
    	Reader r = new StringReader("( (  ( 3 / 2 ) ) )");
    	ITokenizer t = new ExpressionTokenizer(r);
    	Parser p = new Parser(t);
    	assertEquals("((3)/(2))",p.parseFactor().toString());
    	
    }
    
    public void testParseSequence() throws IOException
    {
    	Reader r = new StringReader("a=3, b=2, c=a+b");
    	ITokenizer t = new ExpressionTokenizer(r);
    	Parser p = new Parser(t);
    	assertEquals("(a=(3),b=(2),(c=(a)+(b))",p.parseFactor().toString());
    	
    }
}
