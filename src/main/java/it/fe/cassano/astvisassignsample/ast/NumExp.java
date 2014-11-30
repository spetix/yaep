package it.fe.cassano.astvisassignsample.ast;

import it.fe.cassano.astvisassignsample.visitor.IVisitor;


public class NumExp extends Exp {
	
	final Integer val;

	public NumExp(final Integer val)
	{
		this.val = val;
	}
	
	public String toString() {
		return "(" +this.val+")";
	}
	
	public Integer getValue()
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
