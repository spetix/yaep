package it.fe.cassano.yeap.visitor;

import it.fe.cassano.yeap.ast.Exp;
import it.fe.cassano.yeap.ast.FunExp;
import it.fe.cassano.yeap.ast.NumExp;
import it.fe.cassano.yeap.ast.RealExp;
import it.fe.cassano.yeap.visitors.LispOutputVisitor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class LispOutputVisitorTest {


    
    @Test
	void testNumExp()
    {
    	NumExp e = new NumExp(3);
    	LispOutputVisitor v = new LispOutputVisitor();
    	v.visit(e);
    	assertEquals("3",v.getVal());
    }
    
    @Test
	void testRealExp()
    {
    	RealExp e = new RealExp(-3.21d);
    	LispOutputVisitor v = new LispOutputVisitor();
    	v.visit(e);
    	assertEquals("-3.21",v.getVal());
    }
    
    @Test
	void testFunExp() throws Exception
    {
    	FunExp e = new FunExp("myfun", Arrays.asList( new Exp[]{ new NumExp(3), new RealExp(3.2d)}	));
    	LispOutputVisitor v = new LispOutputVisitor();
    	v.visit(e);
    	assertEquals("(myfun 3 3.2)",v.getVal());	
    }
	
}
