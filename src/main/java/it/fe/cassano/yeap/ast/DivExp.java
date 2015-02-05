package it.fe.cassano.yeap.ast;

import it.fe.cassano.yeap.visitors.IVisitor;


public class DivExp extends OpExp {

	@Override
	public
	String opName() {
		return "/";
	}

	public DivExp(final Exp left, final Exp right)
	{
		super(left,right);
	}
	
	
	 public void accept(final IVisitor v) throws Exception
	 {
		 v.visit(this);
	 }
}
