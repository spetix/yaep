package it.fe.cassano.yeap.ast;

import it.fe.cassano.yeap.visitors.IVisitor;


public class MinusExp extends OpExp {

	@Override
	public
	String opName() {
		return "-";
	}

	public MinusExp(final Exp left, final Exp right)
	{
		super(left,right);
	}
	

	
	 public void accept(final IVisitor v) throws Exception
	 {
		 v.visit(this);
	 }
}
