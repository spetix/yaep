package it.fe.cassano.yeap.visitor;

import it.fe.cassano.yeap.MockEnvironment;
import it.fe.cassano.yeap.ast.AssignExp;
import it.fe.cassano.yeap.ast.IdentExp;
import it.fe.cassano.yeap.ast.NumExp;
import it.fe.cassano.yeap.ast.PlusExp;
import it.fe.cassano.yeap.visitors.EvalVisitor;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



public class EvalWithAssignVisitorTest extends TestCase {
	
	public EvalWithAssignVisitorTest(String testName)
	{
		super(testName);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	final TestSuite ts = new TestSuite();
    	ts.addTestSuite(EvalWithAssignVisitorTest.class);
  
        return ts;
    }
    
    public void testSum() throws Exception
    {
    	PlusExp sum = new PlusExp(new NumExp(3),new NumExp(5));
    	System.out.println(sum.toString());
        assertTrue( sum.toString().equals("((3)+(5))") );
        EvalVisitor v = new EvalVisitor(new MockEnvironment());
        v.visit(sum);
        assertEquals(8, ((Integer) v.getVal()).intValue());    
    }

    public void testAssignVal() throws Exception
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"),new NumExp(5));
    	System.out.println(assignment.toString());
        assertTrue( assignment.toString().equals("(A=(5))") );
        EvalVisitor v = new EvalVisitor(new MockEnvironment());
        v.visit(assignment);
        assertEquals(5, ((Integer) v.getVal()).intValue());    
    }
    
    public void testAssignExpr() throws Exception
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"),new PlusExp(new NumExp(3),new NumExp(5)));
    	System.out.println(assignment.toString());
        assertTrue( assignment.toString().equals("(A=((3)+(5)))") );
        EvalVisitor v = new EvalVisitor(new MockEnvironment());
        v.visit(assignment);
        assertEquals(8, ((Integer) v.getVal()).intValue());    
    }
    
    public void testAssignEnv() throws Exception
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"),new NumExp(5));
    	System.out.println(assignment.toString());
    	EvalVisitor v = new EvalVisitor(new MockEnvironment());
        v.visit(assignment); 
        assertEquals(1, v.getEnvironment().size());    
        assertTrue(v.getEnvironment().containsKey("A"));
        assertEquals(5,((Integer) v.getEnvironment().get("A")).intValue());
    }
    
    public void testAssignedTwice() throws Exception
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"),new NumExp(5));
    	System.out.println(assignment.toString());
    	EvalVisitor v = new EvalVisitor(new MockEnvironment());
        v.visit(assignment); 
        AssignExp assignment2 = new AssignExp(new IdentExp("A"),new NumExp(7));
    	System.out.println(assignment2.toString());
    	v.visit(assignment2); 
        assertEquals(1, v.getEnvironment().size());    
        assertTrue(v.getEnvironment().containsKey("A"));
        assertEquals(7,((Integer) v.getEnvironment().get("A")).intValue());
    }
    
    public void testDoubleAssignment() throws Exception
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"), new AssignExp(new IdentExp("A"), new NumExp(5)));
    	System.out.println(assignment.toString());
    	EvalVisitor v = new EvalVisitor(new MockEnvironment());
        v.visit(assignment); 
        assertEquals(1, v.getEnvironment().size());    
        assertTrue(v.getEnvironment().containsKey("A"));
        assertEquals(5,((Integer) v.getEnvironment().get("A")).intValue());
    }
    
    public void testDoubleAssignmentEnv() throws Exception
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"), new AssignExp(new IdentExp("B"), new NumExp(5)));
    	System.out.println(assignment.toString());
    	EvalVisitor v = new EvalVisitor(new MockEnvironment());
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
