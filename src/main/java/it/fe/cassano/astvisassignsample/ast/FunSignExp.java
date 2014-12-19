package it.fe.cassano.astvisassignsample.ast;

import java.util.List;

import it.fe.cassano.astvisassignsample.visitor.ExpType;
import it.fe.cassano.astvisassignsample.visitor.IVisitor;

public class FunSignExp extends Exp{
	
	public FunSignExp(final String fName, final ExpType rType, final List<ExpType> params)
	{
		
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}
	

}
