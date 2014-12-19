package it.fe.cassano.astvisassignsample.ast;

import java.util.List;

import it.fe.cassano.astvisassignsample.visitor.ExpType;
import it.fe.cassano.astvisassignsample.visitor.IVisitor;

public class FunCodeExp extends Exp {
	
	final protected String jFunName;
	final protected ExpType retType;
	final protected List<ExpType> params;

	public FunCodeExp(final String jFunName,final ExpType retType, final List<ExpType> params)
	{
		this.jFunName = jFunName;
		this.retType = retType;
		this.params = params;
	}

	@Override
	public void accept(IVisitor v) {
		// TODO Auto-generated method stub

	}

}
