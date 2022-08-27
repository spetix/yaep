package it.fe.cassano.yeap.ast;

import it.fe.cassano.yeap.visitors.IVisitor;

public class AssignExp extends OpExp {
	
	public AssignExp(final Exp l, final Exp r)
	{
		super(l,r);
	}

	@Override
	public String opName() {
		return "=";
	}

	

	@Override
	public void accept(final IVisitor v) throws Exception {
		v.visit(this);
	}

}
