package it.fe.cassano.astvisassignsample.tokenizer;

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
    	ts.addTestSuite(TokenizerNumbersTest.class);
    	ts.addTestSuite(TokenizerIdentTest.class);
    	ts.addTestSuite(TokenizerFunctionNameTest.class);  
    	ts.addTestSuite(TokenizerExpressionsTest.class);  
    	ts.addTestSuite(TokenizerFunctionNameTest.class);  
        
    	  
        return ts;
    }
    
  
    
    /*
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
*/
}
