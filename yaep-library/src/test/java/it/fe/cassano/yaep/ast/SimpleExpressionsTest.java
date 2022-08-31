package it.fe.cassano.yaep.ast;



import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class SimpleExpressionsTest  {

@Test
    void testSum()
    {
    	OpExp sum = new PlusExp(new NumExp(3),new NumExp(5));
    	System.out.println(sum.toString());
        assertTrue( sum.toString().equals("((3)+(5))") );
    }

    @Test
    void testMinus()
    {
    	OpExp minus = new MinusExp(new NumExp(5),new NumExp(3));
    	assertTrue( minus.toString().equals("((5)-(3))") );
    }
    @Test
    void testProduct()
    {
    	OpExp product = new MulExp(new NumExp(5),new NumExp(3));
    	System.out.println(product.toString());
        assertTrue( product.toString().equals("((5)*(3))") );
    }
    
    @Test
    void testDivide()
    {
    	OpExp divide = new DivExp(new NumExp(5),new NumExp(3));
    	System.out.println(divide.toString());
        assertTrue( divide.toString().equals("((5)/(3))") );
    }
    

    
}


