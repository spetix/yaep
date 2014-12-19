package it.fe.cassano.astvisassignsample.visitor;

import it.fe.cassano.astvisassignsample.ast.AssignExp;
import it.fe.cassano.astvisassignsample.ast.DivideExp;
import it.fe.cassano.astvisassignsample.ast.FunExp;
import it.fe.cassano.astvisassignsample.ast.IdentExp;
import it.fe.cassano.astvisassignsample.ast.IdentValExp;
import it.fe.cassano.astvisassignsample.ast.MinusExp;
import it.fe.cassano.astvisassignsample.ast.NumExp;
import it.fe.cassano.astvisassignsample.ast.PlusExp;
import it.fe.cassano.astvisassignsample.ast.ProductExp;
import it.fe.cassano.astvisassignsample.ast.RealExp;
import it.fe.cassano.astvisassignsample.ast.SeqExp;
import it.fe.cassano.astvisassignsample.ast.UnaryMinusExp;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

public class EvalWithAssignVisitor implements IVisitor {
	
	
	Map<String,Object> env = new Hashtable<String,Object>();
	Object result;


	public void visit(AssignExp e) {
		e.left().accept(this);
		final String id = ((IdentExp)e.left()).getName();
		e.right().accept(this); 
		result = getVal();
		this.env.put(id,result);
	}

	public void visit(IdentExp e) {
		String id = e.getName();
		result = id;
	}

	public void visit(IdentValExp e) {
		String id = e.getName();
		Integer val = ((Integer) env.get(id)).intValue();
		if (val == null)
		{
		 // boom!
		}
		else
		{
			
			result = val;
		}
		
	}
	
	public Map<String,Object> getEnvironment()
	{
		return Collections.<String,Object>unmodifiableMap(env);
	}

	public void visit(SeqExp e) {
		e.left().accept(this);
		e.right().accept(this);
		
	}

	@Override
	public void visit(RealExp realExp) {
		// TODO Auto-generated method stub
		
	}

	public Object getVal() {
		return result;
	}

	public void visit(DivideExp exp) {
		exp.left().accept(this); Number l = (Number) getVal();
		exp.right().accept(this); Number r = (Number) getVal();
		result = l/r;
	}

	@Override
	public void visit(FunExp funExp) {
		// TODO Auto-generated method stub
		
	}

	public void visit(MinusExp exp) {
		exp.left().accept(this); Integer l = ((Integer) getVal()).intValue();
		exp.right().accept(this); Integer r = ((Integer) getVal()).intValue();
		result = l-r;
	}

	//	protected void visitOpExp(OpExp e){
	//		e.left().accept(this); Integer sleft = getVal();
	//		e.right().accept(this); Integer sright = getVal();
	//		e.e
	//		curs = "(" + e.opName() +sleft + " " + sright + ")";
	//		}
	
		public void visit(NumExp exp) {
			result = exp.getValue();
		}

	public void visit(PlusExp exp) {
		exp.left().accept(this); Integer l = ((Integer) getVal()).intValue();
		exp.right().accept(this); Integer r = ((Integer) getVal()).intValue();
		result = l+r;
	}

	public void visit(ProductExp exp) {
		exp.left().accept(this); Integer l = ((Integer) getVal()).intValue();
		exp.right().accept(this); Integer r = ((Integer) getVal()).intValue();
		result = l*r;
	}



	@Override
	public void visit(UnaryMinusExp unaryMinusExp) {
		// TODO Auto-generated method stub
		
	}



	

}
