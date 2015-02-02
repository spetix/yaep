package it.fe.cassano.yeap.ast;

import it.fe.cassano.yeap.visitors.IVisitor;


public class RealExp extends Exp {
	
	final double val;

	public RealExp(final double val)
	{
		this.val = val;
	}
	
	public String toString() {
		return "(" +this.val+")";
	}
	
	public double getValue()
	{
		return val;
	}
	
//	@Override
//	public int eval() {
//		return this.val;
//	}
	
	 public void accept(final IVisitor v)
	 {
	 v.visit(this);
	 }
}
