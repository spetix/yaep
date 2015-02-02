package it.fe.cassano.yeap.visitors;

import it.fe.cassano.yeap.ast.AssignExp;
import it.fe.cassano.yeap.ast.DivideExp;
import it.fe.cassano.yeap.ast.FunCodeExp;
import it.fe.cassano.yeap.ast.FunDefineExp;
import it.fe.cassano.yeap.ast.FunExp;
import it.fe.cassano.yeap.ast.FunSignExp;
import it.fe.cassano.yeap.ast.IdentExp;
import it.fe.cassano.yeap.ast.IdentValExp;
import it.fe.cassano.yeap.ast.MinusExp;
import it.fe.cassano.yeap.ast.NumExp;
import it.fe.cassano.yeap.ast.PlusExp;
import it.fe.cassano.yeap.ast.ProductExp;
import it.fe.cassano.yeap.ast.RealExp;
import it.fe.cassano.yeap.ast.SeqExp;
import it.fe.cassano.yeap.ast.UnaryMinusExp;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

public class EvalVisitor implements IVisitor {

	Object result;
	private IEnvironment environment;

	public EvalVisitor(final IEnvironment env) {
		this.environment = env;
	}

	public void visit(AssignExp e) {
		e.left().accept(this);
		final String id = ((IdentExp) e.left()).getName();
		e.right().accept(this);
		result = getVal();
		this.environment.add(id, result);
	}

	public void visit(IdentExp e) {
		String id = e.getName();
		result = id;
	}

	public void visit(IdentValExp e) {
		String id = e.getName();
		Integer val = ((Integer) this.environment.getVal(id)).intValue();
		if (val == null) {
			// boom!
		} else {

			result = val;
		}

	}

	public Map<String, Object> getEnvironment() {
		return Collections.<String, Object> unmodifiableMap(env.toMap());
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
		exp.left().accept(this);
		Number l = (Number) getVal();
		exp.right().accept(this);
		Number r = (Number) getVal();
		//result = l / r;
	}

	@Override
	public void visit(FunExp funExp) {
		// TODO Auto-generated method stub

	}

	public void visit(MinusExp exp) {
		exp.left().accept(this);
		Integer l = ((Integer) getVal()).intValue();
		exp.right().accept(this);
		Integer r = ((Integer) getVal()).intValue();
		result = l - r;
	}

	// protected void visitOpExp(OpExp e){
	// e.left().accept(this); Integer sleft = getVal();
	// e.right().accept(this); Integer sright = getVal();
	// e.e
	// curs = "(" + e.opName() +sleft + " " + sright + ")";
	// }

	public void visit(NumExp exp) {
		result = exp.getValue();
	}

	public void visit(PlusExp exp) {
		exp.left().accept(this);
		Integer l = ((Integer) getVal()).intValue();
		exp.right().accept(this);
		Integer r = ((Integer) getVal()).intValue();
		result = l + r;
	}

	public void visit(ProductExp exp) {
		exp.left().accept(this);
		Integer l = ((Integer) getVal()).intValue();
		exp.right().accept(this);
		Integer r = ((Integer) getVal()).intValue();
		result = l * r;
	}

	@Override
	public void visit(UnaryMinusExp unaryMinusExp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FunDefineExp funDefineExp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FunSignExp funSignExp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FunCodeExp funCodeExp) {
		// TODO Auto-generated method stub
		
	}

	public Object getEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

}
