package it.fe.cassano.yaep.ast;

import it.fe.cassano.yaep.visitors.IVisitor;

public class IdentExp extends Exp {
	final String name;
	
	public IdentExp(final String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}

	@Override
	public void accept(IVisitor v) {
		((IVisitor) v).visit(this);
	}
	
	
	
	@Override
	public String toString()
	{
		return name;
	}

}
