package it.fe.cassano.yeap;

import it.fe.cassano.yeap.ccparser.ParseException;
import it.fe.cassano.yeap.functionlibrary.Callme;
import it.fe.cassano.yeap.visitors.ExpType;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * Unit test for simple App.
 */
public class ReflectionTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ReflectionTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(ReflectionTest.class);

        return ts;
    }

    /**
     * Rigourous Test :-)
     * @throws IOException 
     * @throws ParseException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws NoSuchMethodException 
     * @throws ClassNotFoundException 
     */
    public void testLoadFun() throws IOException, ParseException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException
    {
    	Object[] par = { 4.0d };
        Object t = MethodUtils.invokeStaticMethod(Class.forName("java.lang.Math"), "sqrt", par );
        assertEquals(2.0d,(Double)t);
    }
    
    public void testLoadFun2() throws IOException, ParseException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException
    {
    	List<Number> par = new Vector<Number>();
    	par.add(new Double(4.0));
    	List<ExpType> pType = new Vector<ExpType>();
    	pType.add(ExpType.RealExp);
    	
        Callme<Double> fun = new Callme<Double>("java.lang.Math.sqrt", pType );
        assertEquals(2.0d,fun.invoke(par));
    }
    
   
    
        
}
