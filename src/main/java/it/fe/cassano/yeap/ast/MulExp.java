package it.fe.cassano.yeap.ast;

import it.fe.cassano.yeap.visitors.IVisitor;


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
	
//	@Override
//	public int eval() {
//		// TODO Auto-generated method stub
//		return left.eval() * right.eval();
//	}
	
	 public void accept(final IVisitor v)
	 {
	 v.visit(this);
	 }
}
