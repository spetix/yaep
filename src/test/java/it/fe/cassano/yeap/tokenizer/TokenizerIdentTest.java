package it.fe.cassano.yeap.tokenizer;

import it.fe.cassano.yeap.ccparser.ExpressionParser;
import it.fe.cassano.yeap.ccparser.ExpressionParserConstants;
import it.fe.cassano.yeap.ccparser.Token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.jupiter.api.Test;


class TokenizerIdentTest {

	@Test
	void testIdentifier() throws IOException
    {
    	Reader r = new StringReader("A");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("A",tok.image);
    	r.close();  	
    }
    
    @Test
	void testIdentifierAnonymous() throws IOException
    {
    	Reader r = new StringReader("_");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("_",tok.image);
    	r.close();  	
    }
    
    @Test
	void testNotAnIdentifier() throws IOException
    {
    	Reader r = new StringReader("aaaA");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertTrue(ExpressionParserConstants.IDENT != tok.kind);
    	r.close();  	
    }
    
    @Test
	void testIdentifierComplex() throws IOException
    {
    	Reader r = new StringReader("A1ae_test");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("A1ae_test",tok.image);
    	r.close();  	
    }
    
    @Test
	void testIdentifierComplex2() throws IOException
    {
    	Reader r = new StringReader("_a1A1ae_test");
    	ITokenizer t = new ExpressionParser(r);
    	Token tok = t.getNextToken();
    	assertEquals(ExpressionParserConstants.IDENT, tok.kind);
    	assertEquals("_a1A1ae_test",tok.image);
    	r.close();  	
    }
    
    @Test
	void testIdentifierLValRVal() throws IOException
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
    
    @Test
	void testIdentifierInOp() throws IOException
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
  
    @Test
	void testIdentifierInBrackets() throws IOException
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
