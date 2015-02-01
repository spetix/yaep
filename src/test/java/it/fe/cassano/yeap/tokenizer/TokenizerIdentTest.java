package it.fe.cassano.yeap.tokenizer;

import it.fe.cassano.yeap.ccparser.ExpressionParser;
import it.fe.cassano.yeap.ccparser.ExpressionParserConstants;
import it.fe.cassano.yeap.ccparser.Token;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class TokenizerIdentTest extends TestCase {

	public TokenizerIdentTest(String testName) {
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(TokenizerIdentTest.class);  
        return ts;
    }
   
    public void testIdentifier() throws IOException
    {
    	Reader r = new StringReader("A");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("A",tok.image);
    	r.close();  	
    }
    
    public void testIdentifierAnonymous() throws IOException
    {
    	Reader r = new StringReader("_");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("_",tok.image);
    	r.close();  	
    }
    
    public void testNotAnIdentifier() throws IOException
    {
    	Reader r = new StringReader("aaaA");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(ExpressionParserConstants.IDENT != tok.kind);
    	r.close();  	
    }
    
    public void testIdentifierComplex() throws IOException
    {
    	Reader r = new StringReader("A1ae_test");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("A1ae_test",tok.image);
    	r.close();  	
    }
    
    public void testIdentifierComplex2() throws IOException
    {
    	Reader r = new StringReader("_a1A1ae_test");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("_a1A1ae_test",tok.image);
    	r.close();  	
    }
    
    public void testIdentifierLValRVal() throws IOException
    {
    	Reader r = new StringReader("Abc=DD");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("Abc",tok.image);
    	tok = t.getNextToken();
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("DD",tok.image);
    	r.close();  	
    }
    
    public void testIdentifierInOp() throws IOException
    {
    	Reader r = new StringReader("_+DD");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("_",tok.image);
    	tok = t.getNextToken();
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("DD",tok.image);
    	r.close();  	
    }
  
    public void testIdentifierInBrackets() throws IOException
    {
    	Reader r = new StringReader("(DD)");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("DD",tok.image);
    	r.close();  	
    }
    
}
