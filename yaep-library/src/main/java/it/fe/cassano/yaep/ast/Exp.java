package it.fe.cassano.yaep.ast;

import it.fe.cassano.yaep.visitors.IVisitor;

public abstract class Exp {

	public abstract void accept(IVisitor v) throws Exception; 

}
