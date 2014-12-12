package it.fe.cassano.astvisassignsample.ast;

import it.fe.cassano.astvisassignsample.visitor.IVisitor;


public class RealExp extends Exp {
	
	final Float val;

	public RealExp(final Float val)
	{
		this.val = val;
	}
	
	public String toString() {
		return "(" +this.val+")";
	}
	
	public Float getValue()
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
