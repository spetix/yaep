package it.fe.cassano.yaep.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AssignmentTest  {
	
	
    @Test
    void testBuildAssignment()
    {
    	Exp a = new AssignExp( new IdentExp("TEST"), new NumExp(1));
    	assertEquals("(TEST=(1))", a.toString());
    }
    
    @Test
    void testBuildAssignmentWithExpRV()
    {
    	Exp e = new PlusExp(new NumExp(1), new NumExp(1));
    	Exp a = new AssignExp( new IdentExp("TEST"), e);
    	assertEquals("(TEST=((1)+(1)))", a.toString());
    }

}
