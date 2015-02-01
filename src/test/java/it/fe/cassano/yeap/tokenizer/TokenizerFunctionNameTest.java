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


public class TokenizerFunctionNameTest extends TestCase {

	public TokenizerFunctionNameTest(String testName) {
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(TokenizerFunctionNameTest.class);  
        return ts;
    }
   
    public void testJName() throws IOException
    {
    	Reader r = new StringReader("java.lang.Math.sin");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.JFUN, tok.kind);
    	assertEquals("java.lang.Math.sin",tok.image);
    	r.close();  
    	
    }

    public void testInvalidJName() throws IOException
    {
    	Reader r = new StringReader("java.lang.Math..sin");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.JFUN, tok.kind);
    	assertEquals("java.lang.Math..sin",tok.image);
    	r.close();  
    	
    }

    
}
