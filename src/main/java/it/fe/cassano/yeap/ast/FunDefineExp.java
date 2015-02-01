package it.fe.cassano.yeap.ast;

import it.fe.cassano.yeap.visitor.IVisitor;

public class FunDefineExp extends Exp {

	final protected FunCodeExp right;
	final protected FunSignExp left;

	public FunDefineExp(final FunSignExp left, final FunCodeExp right) {
		this.left = left;
	    this.right = right;
		// TODO Auto-generated constructor stub
	}

	public String opName() {
		return "=";
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);

	}

}
