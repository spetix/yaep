package it.fe.cassano.astvisassignsample.ast;



import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class SimpleExpressionsTest extends TestCase {

	public SimpleExpressionsTest(String testName) {
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(SimpleExpressionsTest.class);
  
        return ts;
    }

    /**
     * Rigourous Test :-)
     */
    public void testSum()
    {
    	OpExp sum = new PlusExp(new NumExp(3),new NumExp(5));
    	System.out.println(sum.toString());
        assertTrue( sum.toString().equals("((3)+(5))") );
    }
    public void testMinus()
    {
    	OpExp minus = new MinusExp(new NumExp(5),new NumExp(3));
    	assertTrue( minus.toString().equals("((5)-(3))") );
    }
    public void testProduct()
    {
    	OpExp product = new ProductExp(new NumExp(5),new NumExp(3));
    	System.out.println(product.toString());
        assertTrue( product.toString().equals("((5)*(3))") );
    }
    
    public void testDivide()
    {
    	OpExp divide = new DivideExp(new NumExp(5),new NumExp(3));
    	System.out.println(divide.toString());
        assertTrue( divide.toString().equals("((5)/(3))") );
    }
    

    
}


