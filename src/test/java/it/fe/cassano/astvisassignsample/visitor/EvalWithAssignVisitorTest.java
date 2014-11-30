package it.fe.cassano.astvisassignsample.visitor;

import it.fe.cassano.astvisassignsample.ast.AssignExp;
import it.fe.cassano.astvisassignsample.ast.IdentExp;
import it.fe.cassano.astvisassignsample.ast.NumExp;
import it.fe.cassano.astvisassignsample.ast.PlusExp;
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
    
    public void testSum()
    {
    	PlusExp sum = new PlusExp(new NumExp(3),new NumExp(5));
    	System.out.println(sum.toString());
        assertTrue( sum.toString().equals("((3)+(5))") );
        EvalWithAssignVisitor v = new EvalWithAssignVisitor();
        v.visit(sum);
        assertEquals(8, ((Integer) v.getVal()).intValue());    
    }

    public void testAssignVal()
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"),new NumExp(5));
    	System.out.println(assignment.toString());
        assertTrue( assignment.toString().equals("(A=(5))") );
        EvalWithAssignVisitor v = new EvalWithAssignVisitor();
        v.visit(assignment);
        assertEquals(5, ((Integer) v.getVal()).intValue());    
    }
    
    public void testAssignExpr()
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"),new PlusExp(new NumExp(3),new NumExp(5)));
    	System.out.println(assignment.toString());
        assertTrue( assignment.toString().equals("(A=((3)+(5)))") );
        EvalWithAssignVisitor v = new EvalWithAssignVisitor();
        v.visit(assignment);
        assertEquals(8, ((Integer) v.getVal()).intValue());    
    }
    
    public void testAssignEnv()
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"),new NumExp(5));
    	System.out.println(assignment.toString());
        EvalWithAssignVisitor v = new EvalWithAssignVisitor();
        v.visit(assignment); 
        assertEquals(1, v.getEnvironment().size());    
        assertTrue(v.getEnvironment().containsKey("A"));
        assertEquals(5,((Integer) v.getEnvironment().get("A")).intValue());
    }
    
    public void testAssignedTwice()
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"),new NumExp(5));
    	System.out.println(assignment.toString());
        EvalWithAssignVisitor v = new EvalWithAssignVisitor();
        v.visit(assignment); 
        AssignExp assignment2 = new AssignExp(new IdentExp("A"),new NumExp(7));
    	System.out.println(assignment2.toString());
    	v.visit(assignment2); 
        assertEquals(1, v.getEnvironment().size());    
        assertTrue(v.getEnvironment().containsKey("A"));
        assertEquals(7,((Integer) v.getEnvironment().get("A")).intValue());
    }
    
    public void testDoubleAssignment()
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"), new AssignExp(new IdentExp("A"), new NumExp(5)));
    	System.out.println(assignment.toString());
        EvalWithAssignVisitor v = new EvalWithAssignVisitor();
        v.visit(assignment); 
        assertEquals(1, v.getEnvironment().size());    
        assertTrue(v.getEnvironment().containsKey("A"));
        assertEquals(5,((Integer) v.getEnvironment().get("A")).intValue());
    }
    
    public void testDoubleAssignmentEnv()
    {
    	AssignExp assignment = new AssignExp(new IdentExp("A"), new AssignExp(new IdentExp("B"), new NumExp(5)));
    	System.out.println(assignment.toString());
        EvalWithAssignVisitor v = new EvalWithAssignVisitor();
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
