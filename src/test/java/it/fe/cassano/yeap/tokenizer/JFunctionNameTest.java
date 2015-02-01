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


public class JFunctionNameTest extends TestCase {

	public JFunctionNameTest(String testName) {
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(JFunctionNameTest.class);  
        return ts;
    }
   
    public void testFName() throws IOException
    {
    	Reader r = new StringReader("s(2)");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.FNAME, tok.kind);
    	assertEquals("s",tok.image);
    	r.close();  	
    }
    
  
    
    public void testNotAFName() throws IOException
    {
    	Reader r = new StringReader("Aaa");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(ExpressionParserConstants.FNAME != tok.kind);
    	r.close();  	
    }
    
    public void testFNameComplex() throws IOException
    {
    	Reader r = new StringReader("a1ae_test");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.FNAME, tok.kind);
    	assertEquals("a1ae_test",tok.image);
    	r.close();  	
    }
    
   
    
    public void testFNameOfFName() throws IOException
    {
    	Reader r = new StringReader("aaaa(bbb(1))");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.FNAME, tok.kind);
    	assertEquals("aaaa",tok.image);
    	tok = t.getNextToken();
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.FNAME, tok.kind);
    	assertEquals("bbb",tok.image);
    	r.close();  	
    }
    
    public void testFNamesInOp() throws IOException
    {
    	Reader r = new StringReader("aaa()*bbb()");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.FNAME, tok.kind);
    	assertEquals("aaa",tok.image);
    	tok = t.getNextToken();
    	tok = t.getNextToken();
    	tok = t.getNextToken();
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.FNAME, tok.kind);
    	assertEquals("bbb",tok.image);
    	r.close();  	
    }
  
    public void testFNameInBrackets() throws IOException
    {
    	Reader r = new StringReader("(aaa()))");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.FNAME, tok.kind);
    	assertEquals("aaa",tok.image);
    	r.close();  	
    }
    
}
