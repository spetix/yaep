package it.fe.cassano.yeap.tokenizer;

import it.fe.cassano.yeap.ccparser.ExpressionParser;
import it.fe.cassano.yeap.ccparser.ExpressionParserConstants;
import it.fe.cassano.yeap.ccparser.Token;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.jupiter.api.Test;
class TokenizerFunctionNameTest  {


   
    @Test
	void testJName() throws IOException
    {
    	Reader r = new StringReader("java.lang.Math.sin");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.JFUN, tok.kind);
    	assertEquals("java.lang.Math.sin",tok.image);
    	r.close();  
    	
    }
    
    @Test
	void testJName2() throws IOException
    {
    	Reader r = new StringReader("java.Math.sin(");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.JFUN, tok.kind);
    	assertEquals("java.Math.sin",tok.image);
    	r.close();  
    	
    }
    
    @Test
	void testJName3() throws IOException
    {
    	Reader r = new StringReader("= java.Math.sin(");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.JFUN, tok.kind);
    	assertEquals("java.Math.sin",tok.image);
    	r.close(); 	
    }
    
}
