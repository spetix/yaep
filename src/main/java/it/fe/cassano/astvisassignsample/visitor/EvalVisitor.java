package it.fe.cassano.astvisassignsample.visitor;

import it.fe.cassano.astvisassignsample.ast.DivideExp;
import it.fe.cassano.astvisassignsample.ast.FunExp;
import it.fe.cassano.astvisassignsample.ast.MinusExp;
import it.fe.cassano.astvisassignsample.ast.NumExp;
import it.fe.cassano.astvisassignsample.ast.PlusExp;
import it.fe.cassano.astvisassignsample.ast.ProductExp;
import it.fe.cassano.astvisassignsample.ast.RealExp;

public class EvalVisitor implements IVisitor {
	
	Object result;
	
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

	public void visit(MinusExp exp) {
		exp.left().accept(this); Integer l = ((Integer) getVal()).intValue();
		exp.right().accept(this); Integer r = ((Integer) getVal()).intValue();
		result = l-r;
	}

	public void visit(ProductExp exp) {
		exp.left().accept(this); Integer l = ((Integer) getVal()).intValue();
		exp.right().accept(this); Integer r = ((Integer) getVal()).intValue();
		result = l*r;
	}

	public void visit(DivideExp exp) {
		exp.left().accept(this); Integer l = ((Integer) getVal()).intValue();
		exp.right().accept(this); Integer r = ((Integer) getVal()).intValue();
		result = l/r;
	}

	public Object getVal() {
		return result;
	}

	@Override
	public void visit(RealExp realExp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FunExp funExp) {
		// TODO Auto-generated method stub
		
	}

}
