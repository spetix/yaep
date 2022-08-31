package it.fe.cassano.yaep.tokenizer;

import it.fe.cassano.yaep.ccparser.ExpressionParser;
import it.fe.cassano.yaep.ccparser.ExpressionParserConstants;
import it.fe.cassano.yaep.ccparser.Token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

class JFunctionNameTest {

   
    @Test
	void testFName() throws IOException
    {
    	Reader r = new StringReader("s(2)");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.FNAME, tok.kind);
    	assertEquals("s",tok.image);
    	r.close();  	
    }
    
  
    
    @Test
	void testNotAFName() throws IOException
    {
    	Reader r = new StringReader("Aaa");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(ExpressionParserConstants.FNAME != tok.kind);
    	r.close();  	
    }
    
	// @Ignore
    // public void testFNameComplex() throws IOException
    // {
    // 	Reader r = new StringReader("a1ae_test");
    // 	ITokenizer t = new ExpressionParser(r);
    // 	Token tok = t.getNextToken();
    // 	assertEquals(ExpressionParserConstants.FNAME, tok.kind);
    // 	assertEquals("a1ae_test",tok.image);
    // 	r.close();  	
    // }
    
   
    
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
