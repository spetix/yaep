package it.fe.cassano.yaep.visitors.factories;

import it.fe.cassano.yaep.models.IEnvironment;
import it.fe.cassano.yaep.visitors.EvalVisitor;
import it.fe.cassano.yaep.visitors.IVisitor;

public class EvalVisitorFactoryMethod implements IVisitorFactoryMethod {

	@Override
	public IVisitor getInstance(final IEnvironment env,final IEnvironment fun) {	
		return new EvalVisitor(env,fun);
	}

}
