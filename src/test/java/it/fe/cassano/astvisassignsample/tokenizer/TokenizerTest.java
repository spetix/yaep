package it.fe.cassano.astvisassignsample.tokenizer;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class TokenizerTest extends TestCase {

	public TokenizerTest(String testName) {
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(TokenizerTest.class);
  
        return ts;
    }
    
    public void testFirstToken() throws IOException
    {
    	Reader r = new StringReader("123 + 2 + 3 + 0");
    	ITokenizer t = new ExpressionTokenizer(r);
    	assertTrue(t.hasNext());
    	r.close();
    	
    }
    
    public void testNext() throws IOException
    {
    	Reader r = new StringReader("1 + 2 + 3");
    	ITokenizer t = new ExpressionTokenizer(r);
    	
    	assertTrue("1".equals(t.getCurrent()));
    	assertTrue("+".equals(t.getCurrent()));
    	assertTrue(t.hasNext());
    	assertTrue("2".equals(t.getCurrent()));
    	assertTrue(t.hasNext());
    	assertTrue("+".equals(t.getCurrent()));
    	assertTrue(t.hasNext());
    	assertTrue("3".equals(t.getCurrent()));
    	assertTrue(!t.hasNext());
    	r.close();  	
    }
    
    public void testBrackets() throws IOException
    {
    	Reader r = new StringReader("( )");
    	ITokenizer t = new ExpressionTokenizer(r);
    	assertTrue("(".equals(t.getCurrent()));
    	assertTrue(t.hasNext());
    	assertTrue(")".equals(t.getCurrent()));
   	
    }
    
    public void testNextTokenIs() throws IOException
    {
    	Reader r = new StringReader("1 aaa = 15");
    	ITokenizer t = new ExpressionTokenizer(r);
    	assertEquals(StreamTokenizer.TT_WORD,t.nextTokenIs()); // Parse number disabled..
    	assertEquals("1",t.getCurrent());
    	assertEquals(StreamTokenizer.TT_WORD,t.nextTokenIs());
    	assertEquals("aaa",t.getCurrent());
    	assertEquals(StreamTokenizer.TT_WORD, t.nextTokenIs());
    	assertEquals("=",t.getCurrent());
    	assertEquals("15",t.getCurrent());
    	
    	assertTrue(!t.hasNext());
    	assertEquals(StreamTokenizer.TT_EOF, t.nextTokenIs());
    }
    
    public void testPeekCurrent() throws IOException
    {
    	Reader r = new StringReader("1 aaa = 15");
    	ITokenizer t = new ExpressionTokenizer(r);
    	assertEquals("1",t.peekCurrent()); // Parse number disabled..
    	assertEquals("1",t.getCurrent());
    	assertEquals("aaa",t.peekCurrent());
    	assertEquals("aaa",t.peekCurrent());
    	assertEquals("aaa",t.peekCurrent());
    	assertEquals("aaa",t.getCurrent());
    }

}
