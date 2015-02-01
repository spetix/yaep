package it.fe.cassano.yeap.ast;

import it.fe.cassano.yeap.visitor.IVisitor;

public class UnaryMinusExp extends Exp {

	protected final Exp e;

	public UnaryMinusExp(final Exp e) {
		this.e = e;
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}
	
	public String opName()
	{
		return "-";
	}
	
	public String toString()
	{
		return "(" + this.opName() + e.toString() + ")";
	}
	
	public Exp expr()
	{
		return this.e;
	}

}
