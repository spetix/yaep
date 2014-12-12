package it.fe.cassano.astvisassignsample.ast;

import it.fe.cassano.astvisassignsample.visitor.IAssignVisitor;
import it.fe.cassano.astvisassignsample.visitor.IVisitor;

public class ExpParams extends OpExp {

	public ExpParams(Exp l ) {
		super(l,null);
	}
	public ExpParams(Exp l, Exp r) {
		super(l,r);
	}

	@Override
	public void accept(IVisitor v) {
		((IAssignVisitor) v).visit(this);	
	}

	@Override
	public String opName() {
		return ",";
	}

}
