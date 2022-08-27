package it.fe.cassano.yeap.tokenizer;

import it.fe.cassano.yeap.ccparser.ExpressionParser;
import it.fe.cassano.yeap.ccparser.ExpressionParserConstants;
import it.fe.cassano.yeap.ccparser.Token;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;


public class TokenizerNumbersTest {


    
    @Test
	void testNumber() throws IOException
    {
    	Reader r = new StringReader("123");
    	 ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(tok.image.equals("123"));
    	assertTrue(tok.kind == ExpressionParserConstants.INTNUM);
    	r.close();	
    }
    
    @Test
    void testNegativeNumber() throws IOException
    {
    	Reader r = new StringReader("-123");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
     	assertTrue(StringUtils.equals(tok.image,"-"));
     	assertTrue(tok.kind==ExpressionParserConstants.MINUS);
    	tok = t.getNextToken();
    	assertTrue(tok.image.equals("123"));
    	r.close();	
    }
    
    @Test 
	void testZero() throws IOException
    {
    	Reader r = new StringReader("0");
    	 ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(tok.image.equals("0"));
    	assertTrue(tok.kind==ExpressionParserConstants.INTNUM);
    	r.close();	
    }
    
    @Test
	void testNegativeZero() throws IOException
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
    
    @Test
	void testTripleZero() throws IOException
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
    
    @Test
	void testFloatNumber() throws IOException
    {
    	Reader r = new StringReader("0.03");
    	 ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(StringUtils.equals(tok.image,"0.03"));
    	assertTrue(tok.kind==ExpressionParserConstants.REALNUM);
    	r.close();	
    }
    
    @Test
	void testNegativeFloatNumber() throws IOException
    {
    	Reader r = new StringReader("-0.03");
    	 ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(tok.kind==ExpressionParserConstants.MINUS);
    	tok = t.getNextToken();
    	assertTrue(StringUtils.equals(tok.image,"0.03"));
    	assertTrue(tok.kind==ExpressionParserConstants.REALNUM);
    	r.close();	
    }
    
    
    @Test
	void testFloatNumber2() throws IOException
    {
    	Reader r = new StringReader("1324.33333");
    	 ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(StringUtils.equals(tok.image,"1324.33333"));
    	assertTrue(tok.kind==ExpressionParserConstants.REALNUM);
    	r.close();	
    }
    
    @Test
	void testNegativeFloatNumber2() throws IOException
    {
    	Reader r = new StringReader("-1202.33");
    	 ITokenizer t = new ExpressionParser(r);
    	 Token tok = t.getNextToken();
     	assertTrue(tok.kind==ExpressionParserConstants.MINUS);
     	tok = t.getNextToken();
     	assertTrue(StringUtils.equals(tok.image,"1202.33"));
    	assertTrue(tok.kind==ExpressionParserConstants.REALNUM);
    	r.close();	
    }
    
}
