package it.fe.cassano.astvisassignsample.visitor;

import it.fe.cassano.astvisassignsample.ast.AssignExp;
import it.fe.cassano.astvisassignsample.ast.IdentExp;
import it.fe.cassano.astvisassignsample.ast.IdentValExp;
import it.fe.cassano.astvisassignsample.ast.RealExp;
import it.fe.cassano.astvisassignsample.ast.SeqExp;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

public class EvalWithAssignVisitor extends EvalVisitor implements IVisitor,IAssignVisitor {
	
	
	Map<String,Object> env = new Hashtable<String,Object>();


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



	

}
