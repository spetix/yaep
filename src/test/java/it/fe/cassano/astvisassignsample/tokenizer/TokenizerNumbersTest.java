package it.fe.cassano.astvisassignsample.tokenizer;

import it.fe.cassano.astvisassignsample.ccparser.ExpressionParser;
import it.fe.cassano.astvisassignsample.ccparser.ExpressionParserConstants;
import it.fe.cassano.astvisassignsample.ccparser.Token;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.lang3.StringUtils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class TokenizerNumbersTest extends TestCase {

	public TokenizerNumbersTest(String testName) {
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(TokenizerNumbersTest.class);
    	
        return ts;
    }
    
    public void testNumber() throws IOException
    {
    	Reader r = new StringReader("123");
    	 ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(tok.image.equals("123"));
    	r.close();	
    }
    
    public void testNegativeNumber() throws IOException
    {
    	Reader r = new StringReader("-123");
    	 ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(tok.image.equals("-123"));
    	r.close();	
    }
    
    public void testZero() throws IOException
    {
    	Reader r = new StringReader("0");
    	 ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(tok.image.equals("0"));
    	assertTrue(tok.kind==ExpressionParserConstants.INTNUM);
    	r.close();	
    }
    
    public void testNegativeZero() throws IOException
    {
    	Reader r = new StringReader("-0");
    	 ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(StringUtils.equals(tok.image,"-"));
    	assertTrue(tok.kind==ExpressionParserConstants.MINUS);
    	tok = t.getNextToken();
    	assertTrue(StringUtils.equals(tok.image,"0"));
    	assertTrue(tok.kind==ExpressionParserConstants.INTNUM);
    	
    	r.close();	
    }
    
    public void testTripleZero() throws IOException
    {
    	Reader r = new StringReader("000");
    	 ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(StringUtils.equals(tok.image,"0"));
    	assertTrue(tok.kind==ExpressionParserConstants.INTNUM);
    	tok = t.getNextToken();
    	assertTrue(StringUtils.equals(tok.image,"0"));
    	assertTrue(tok.kind==ExpressionParserConstants.INTNUM);
    	tok = t.getNextToken();
    	assertTrue(StringUtils.equals(tok.image,"0"));
    	assertTrue(tok.kind==ExpressionParserConstants.INTNUM);
    	r.close();	
    }
    
    public void testFloatNumber() throws IOException
    {
    	Reader r = new StringReader("0.03");
    	 ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(StringUtils.equals(tok.image,"0.03"));
    	assertTrue(tok.kind==ExpressionParserConstants.REALNUM);
    	r.close();	
    }
    
    public void testNegativeFloatNumber() throws IOException
    {
    	Reader r = new StringReader("-0.03");
    	 ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(StringUtils.equals(tok.image,"-0.03"));
    	assertTrue(tok.kind==ExpressionParserConstants.REALNUM);
    	r.close();	
    }
    
    
    public void testFloatNumber2() throws IOException
    {
    	Reader r = new StringReader("1324.33333");
    	 ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(StringUtils.equals(tok.image,"1324.33333"));
    	assertTrue(tok.kind==ExpressionParserConstants.REALNUM);
    	r.close();	
    }
    
    public void testNegativeFloatNumber2() throws IOException
    {
    	Reader r = new StringReader("-1202.33");
    	 ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(StringUtils.equals(tok.image,"-1202.33"));
    	assertTrue(tok.kind==ExpressionParserConstants.REALNUM);
    	r.close();	
    }
    
}
