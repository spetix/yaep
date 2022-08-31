package it.fe.cassano.yaep;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CastingTest {
   
    @Test
    void testCasting() 
    {
    	int a = 3;
    	Object b = a;
    	double c= (double) ((int)b);
    	assertEquals(3.0, c);
    	assertEquals(3,(int)b);
    }
    
    @Test
    void testCasting2() 
    {
    	int a = 3;
    	Object b = a;
    	double c= (double) ((int)b);
    	assertEquals(3.0, c);
    	assertEquals(3,(int)b);
    }   
        
}
