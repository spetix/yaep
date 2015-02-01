package it.fe.cassano.yeap.ast;

import it.fe.cassano.yeap.visitor.IVisitor;

public class SeqExp extends OpExp {

	public SeqExp(final Exp l,final Exp r) {
		super(l,r);
	}

	@Override
	public void accept(IVisitor v) {
		((IVisitor) v).visit(this);	
	}

	@Override
	public String opName() {
		return ",";
	}

}
