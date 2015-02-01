package it.fe.cassano.yeap.ast;

import java.util.List;

import it.fe.cassano.yeap.visitors.ExpType;
import it.fe.cassano.yeap.visitors.IVisitor;

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
		v.visit(this);
	}

}
