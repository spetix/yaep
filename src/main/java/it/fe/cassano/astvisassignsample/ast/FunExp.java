package it.fe.cassano.astvisassignsample.ast;

import it.fe.cassano.astvisassignsample.visitor.IVisitor;


public class FunExp extends Exp {
	
	
	final protected Exp params;
	final protected String name;

	public FunExp(final String name, final Exp params)
	{
		this.name = name;
		this.params = params;
	}
	
	public String toString() {
		return "(" +this.name+"("+params.toString()+"))";
	}
	
	public Float getValue()
	{
		// TODO
		return 0F;
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
