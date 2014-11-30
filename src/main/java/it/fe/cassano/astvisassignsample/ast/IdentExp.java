package it.fe.cassano.astvisassignsample.ast;

import it.fe.cassano.astvisassignsample.visitor.IAssignVisitor;
import it.fe.cassano.astvisassignsample.visitor.IVisitor;

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
		((IAssignVisitor) v).visit(this);
	}
	
	
	
	@Override
	public String toString()
	{
		return name;
	}

}
