package it.fe.cassano.yeap.ast;

import it.fe.cassano.yeap.visitors.IVisitor;

public class SeqExp extends OpExp {

	public SeqExp(final Exp l,final Exp r) {
		super(l,r);
	}

	@Override
	public void accept(IVisitor v) throws Exception {
		v.visit(this);	
	}

	@Override
	public String opName() {
		return ";";
	}
	
	

}
