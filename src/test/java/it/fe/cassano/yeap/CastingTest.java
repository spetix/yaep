package it.fe.cassano.yeap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CastingTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CastingTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(CastingTest.class);

        return ts;
    }

    /** 
     */
    public void testCasting() 
    {
    	int a = 3;
    	Object b = a;
    	double c= (double) ((int)b);
    	assertEquals(3.0, c);
    	assertEquals(3,(int)b);
    }
    
    public void testCasting2() 
    {
    	int a = 3;
    	Object b = a;
    	double c= (double) ((int)b);
    	assertEquals(3.0, c);
    	assertEquals(3,(int)b);
    }   
        
}
