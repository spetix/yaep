package it.fe.cassano.yeap.visitors.factories;

import it.fe.cassano.yeap.models.IEnvironment;
import it.fe.cassano.yeap.visitors.EvalVisitor;
import it.fe.cassano.yeap.visitors.IVisitor;

public class EvalVisitorFactoryMethod implements IVisitorFactoryMethod {

	@Override
	public IVisitor getInstance(final IEnvironment env,final IEnvironment fun) {	
		return new EvalVisitor(env,fun);
	}

}
