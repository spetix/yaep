package it.fe.cassano.yeap;

import it.fe.cassano.yeap.ccparser.ParseException;
import it.fe.cassano.yeap.functionlibrary.Callme;
import it.fe.cassano.yeap.visitors.ExpType;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class ReflectionTest {
    

    @Test
    void testLoadFun() throws IOException, ParseException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException
    {
    	Object[] par = { 4.0d };
        Object t = MethodUtils.invokeStaticMethod(Class.forName("java.lang.Math"), "sqrt", par );
        assertEquals(2.0d,(Double)t);
    }
    
    @Test
    void testLoadFun2() throws IOException, ParseException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException
    {
    	List<Number> par = new Vector<Number>();
    	par.add(new Double(4.0));
    	List<ExpType> pType = new Vector<ExpType>();
    	pType.add(ExpType.RealExp);
    	
        Callme<Double> fun = new Callme<Double>("java.lang.Math.sqrt", pType );
        assertEquals(2.0d,fun.invoke(par));
    }
    
   
    
        
}
