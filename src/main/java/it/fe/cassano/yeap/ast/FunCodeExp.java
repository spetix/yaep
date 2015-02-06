package it.fe.cassano.yeap.ast;

import java.util.List;

import it.fe.cassano.yeap.visitors.ExpType;
import it.fe.cassano.yeap.visitors.IVisitor;

public class FunCodeExp extends Exp {
	
	final public String jFunName;
	final public ExpType retType;
	final public List<ExpType> params;

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
	
	@Override
	public  String toString()
	{
		return FunSignExp.produceSignature(jFunName, params)+":"+retType;
	}

}
