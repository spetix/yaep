package it.fe.cassano.astvisassignsample.ast;

import it.fe.cassano.astvisassignsample.visitor.IVisitor;


public class PlusExp extends OpExp {

	@Override
	public String opName() {
		return "+";
	}

	public PlusExp(final Exp left, final Exp right)
	{
		super(left,right);
	}


// using visitor now
//	@Override
//	public int eval() {
//		// TODO Auto-generated method stub
//		return left.eval() + right.eval();
//	}
	
	public void accept(final IVisitor v)
	{
		v.visit(this);
	}
	
	
}
