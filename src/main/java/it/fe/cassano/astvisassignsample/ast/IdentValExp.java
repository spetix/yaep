package it.fe.cassano.astvisassignsample.ast;

import it.fe.cassano.astvisassignsample.visitor.IVisitor;

public class IdentValExp extends IdentExp {

	public IdentValExp(String name) {
		super(name);
	}
	
	@Override
	public void accept(IVisitor v)
	{
		((IVisitor) v).visit(this);
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}
	
	@Override 
	public String getName()
	{
		return this.name;
	}

}
