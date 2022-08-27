package it.fe.cassano.yeap.ast;

import it.fe.cassano.yeap.visitors.IVisitor;

public abstract class Exp {

	public abstract void accept(IVisitor v) throws Exception; 

}
