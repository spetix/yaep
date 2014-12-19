package it.fe.cassano.astvisassignsample.ast;

import it.fe.cassano.astvisassignsample.visitor.IVisitor;

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
	public void accept(final IVisitor v) {
		v.visit(this);
	}

}
