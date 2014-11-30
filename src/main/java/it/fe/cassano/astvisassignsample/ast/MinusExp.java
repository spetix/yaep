package it.fe.cassano.astvisassignsample.ast;

import it.fe.cassano.astvisassignsample.visitor.IVisitor;


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
	
//	@Override
//	public int eval() {
//		// TODO Auto-generated method stub
//		return left.eval() - right.eval();
//	}
	
	 public void accept(final IVisitor v)
	 {
		 v.visit(this);
	 }
}
