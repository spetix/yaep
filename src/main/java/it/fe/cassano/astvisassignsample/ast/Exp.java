package it.fe.cassano.astvisassignsample.ast;

import it.fe.cassano.astvisassignsample.visitor.IVisitor;

public abstract class Exp {

//  Using visitor now
//	abstract public int eval();
	
	// defined here in order to force an implementation in all the subclasses:
	public abstract void accept(IVisitor v); 

}
