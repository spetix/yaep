package it.fe.cassano.yeap.ast;

import it.fe.cassano.yeap.visitors.IVisitor;

public class FunDefineExp extends OpExp {


	public FunDefineExp(final FunSignExp left, final FunCodeExp right) {
		super(left,right);
	}

	public String opName() {
		return "=";
	}

	@Override
	public void accept(IVisitor v) throws Exception {
		v.visit(this);

	}

}
