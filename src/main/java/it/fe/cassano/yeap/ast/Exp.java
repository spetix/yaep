package it.fe.cassano.yeap.ast;

import it.fe.cassano.yeap.visitors.IVisitor;

public abstract class Exp {

//  Using visitor now
//	abstract public int eval();
	
	// defined here in order to force an implementation in all the subclasses:
	public abstract void accept(IVisitor v); 

}
