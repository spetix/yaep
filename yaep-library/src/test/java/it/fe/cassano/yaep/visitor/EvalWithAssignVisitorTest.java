package it.fe.cassano.yaep.visitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.fe.cassano.yaep.MockEnvironment;
import it.fe.cassano.yaep.ast.AssignExp;
import it.fe.cassano.yaep.ast.IdentExp;
import it.fe.cassano.yaep.ast.NumExp;
import it.fe.cassano.yaep.ast.PlusExp;
import it.fe.cassano.yaep.visitors.EvalVisitor;


class EvalWithAssignVisitorTest {
	
	
    @Test
    void testSum() throws Exception
    {
    	PlusExp sum = new PlusExp(new NumExp(3),new NumExp(5));
    	System.out.println(sum.toString());
        assertTrue( sum.toString().equals("((3)+(5))") );
        EvalVisitor v = new EvalVisitor(new MockEnvironment(),new MockEnvironment());
        v.visit(sum);
        assertEquals(8, ((Integer) v.getVal()).intValue());    
    }

    @Test
    void testAssignVal() throws Exception
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"),new NumExp(5));
    	System.out.println(assignment.toString());
        assertTrue( assignment.toString().equals("(A=(5))") );
        EvalVisitor v = new EvalVisitor(new MockEnvironment(),new MockEnvironment());
        v.visit(assignment);
        assertEquals(5, ((Integer) v.getVal()).intValue());    
    }
    
    @Test
    void testAssignExpr() throws Exception
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"),new PlusExp(new NumExp(3),new NumExp(5)));
    	System.out.println(assignment.toString());
        assertTrue( assignment.toString().equals("(A=((3)+(5)))") );
        EvalVisitor v = new EvalVisitor(new MockEnvironment(),new MockEnvironment());
        v.visit(assignment);
        assertEquals(8, ((Integer) v.getVal()).intValue());    
    }
    
    @Test
    void testAssignEnv() throws Exception
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"),new NumExp(5));
    	System.out.println(assignment.toString());
    	EvalVisitor v = new EvalVisitor(new MockEnvironment(),new MockEnvironment());
        v.visit(assignment); 
        assertEquals(1, v.getEnvironment().size());    
        assertTrue(v.getEnvironment().containsKey("A"));
        assertEquals(5,((Integer) v.getEnvironment().get("A")).intValue());
    }
    
    @Test
    void testAssignedTwice() throws Exception
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"),new NumExp(5));
    	System.out.println(assignment.toString());
    	EvalVisitor v = new EvalVisitor(new MockEnvironment(),new MockEnvironment());
        v.visit(assignment); 
        AssignExp assignment2 = new AssignExp(new IdentExp("A"),new NumExp(7));
    	System.out.println(assignment2.toString());
    	v.visit(assignment2); 
        assertEquals(1, v.getEnvironment().size());    
        assertTrue(v.getEnvironment().containsKey("A"));
        assertEquals(7,((Integer) v.getEnvironment().get("A")).intValue());
    }
    
    @Test
    void testDoubleAssignment() throws Exception
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"), new AssignExp(new IdentExp("A"), new NumExp(5)));
    	System.out.println(assignment.toString());
    	EvalVisitor v = new EvalVisitor(new MockEnvironment(),new MockEnvironment());
        v.visit(assignment); 
        assertEquals(1, v.getEnvironment().size());    
        assertTrue(v.getEnvironment().containsKey("A"));
        assertEquals(5,((Integer) v.getEnvironment().get("A")).intValue());
    }
    
    @Test
    void testDoubleAssignmentEnv() throws Exception
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"), new AssignExp(new IdentExp("B"), new NumExp(5)));
    	System.out.println(assignment.toString());
    	EvalVisitor v = new EvalVisitor(new MockEnvironment(),new MockEnvironment());
        v.visit(assignment); 
        // Expression result:
        assertEquals(5, ((Integer) v.getVal()).intValue());   
        // Environment status:
        assertEquals(2, v.getEnvironment().size());    
        assertTrue(v.getEnvironment().containsKey("A"));
        assertEquals(5,((Integer) v.getEnvironment().get("A")).intValue());
        assertTrue(v.getEnvironment().containsKey("B"));
        assertEquals(5,((Integer) v.getEnvironment().get("B")).intValue());
    }

}
