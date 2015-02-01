package it.fe.cassano.yeap.tokenizer;

import it.fe.cassano.astvisassignsample.ccparser.ExpressionParser;
import it.fe.cassano.astvisassignsample.ccparser.ExpressionParserConstants;
import it.fe.cassano.astvisassignsample.ccparser.Token;
import it.fe.cassano.yeap.tokenizer.ITokenizer;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class TokenizerExpressionsTest extends TestCase {

	public TokenizerExpressionsTest(String testName) {
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(TokenizerExpressionsTest.class);  
        return ts;
    }
   
    public void testSimpleSum() throws IOException
    {
    	Reader r = new StringReader("3+ 1");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.INTNUM, tok.kind);
    	assertEquals("3",tok.image);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.PLUS, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.INTNUM, tok.kind);
    	assertEquals("1",tok.image);
    	r.close();  	
    }
    public void testSum() throws IOException
    {
    	Reader r = new StringReader("3+-1");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.INTNUM, tok.kind);
    	assertEquals("3",tok.image);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.PLUS, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.MINUS, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.INTNUM, tok.kind);
    	assertEquals("1",tok.image);
    	r.close();  	
    }
    
    public void testDoubleMinus() throws IOException {
    	Reader r = new StringReader("1-1-1");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.INTNUM, tok.kind);
    	assertEquals("1",tok.image);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.MINUS, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.INTNUM, tok.kind);
    	assertEquals("1",tok.image);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.MINUS, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.INTNUM, tok.kind);
    	assertEquals("1",tok.image);
    	r.close();  	
    	
    }
    
    public void testOperationWithIdent() throws IOException
    {
    	Reader r = new StringReader("3*_ + 1.4");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.INTNUM, tok.kind);
    	assertEquals("3",tok.image);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.MUL, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("_",tok.image);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.PLUS, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.REALNUM, tok.kind);
    	assertEquals("1.4",tok.image);
    	r.close();  	
    }
    
    public void testOperationWithFunction() throws IOException
    {
    	Reader r = new StringReader("3*abc(_) + 1.4");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.INTNUM, tok.kind);
    	assertEquals("3",tok.image);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.MUL, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.FNAME, tok.kind);
    	assertEquals("abc",tok.image);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.OBR, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("_",tok.image);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.CBR, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.PLUS, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.REALNUM, tok.kind);
    	assertEquals("1.4",tok.image);
    	r.close();  	
    }
    
    
    public void testSequence() throws IOException
    {
    	Reader r = new StringReader("A=3,3-A");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("A",tok.image);  	
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.ASSIGN, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.INTNUM, tok.kind);
    	assertEquals("3",tok.image);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.COMMA, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.INTNUM, tok.kind);
    	assertEquals("3",tok.image);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.MINUS, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("A",tok.image);
    	r.close();  	
    }
    
    public void testComplexSequence() throws IOException
    {
    	Reader r = new StringReader("A=3,abc(_,-0.3)--1");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("A",tok.image);  	
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.ASSIGN, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.INTNUM, tok.kind);
    	assertEquals("3",tok.image);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.COMMA, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.FNAME, tok.kind);
    	assertEquals("abc",tok.image);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.OBR, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("_",tok.image);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.COMMA, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.MINUS, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.REALNUM, tok.kind);
    	assertEquals("0.3",tok.image);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.CBR, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.MINUS, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.MINUS, tok.kind);
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.INTNUM, tok.kind);
    	assertEquals("1",tok.image);
    	r.close();  	
    }
   
}
