package it.fe.cassano.yeap.visitors.factories;

import it.fe.cassano.yeap.visitors.EvalVisitor;
import it.fe.cassano.yeap.visitors.IEnvironment;
import it.fe.cassano.yeap.visitors.IVisitor;

public class EvalVisitorFactoryMethod implements IVisitorFactoryMethod {

	@Override
	public IVisitor getInstance(IEnvironment env) {	
		return new EvalVisitor(env);
	}

}
