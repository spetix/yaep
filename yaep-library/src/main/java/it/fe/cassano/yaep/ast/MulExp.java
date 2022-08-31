package it.fe.cassano.yaep.ast;

import it.fe.cassano.yaep.visitors.IVisitor;


public class MulExp extends OpExp {

	@Override
	public
	String opName() {
		return "*";
	}

	public MulExp(final Exp left, final Exp right)
	{
		super(left,right);
	}
	

	 public void accept(final IVisitor v) throws Exception
	 {
	 v.visit(this);
	 }
}
