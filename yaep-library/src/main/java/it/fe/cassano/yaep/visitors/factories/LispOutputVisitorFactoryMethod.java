package it.fe.cassano.yaep.visitors.factories;

import it.fe.cassano.yaep.models.IEnvironment;
import it.fe.cassano.yaep.visitors.IVisitor;
import it.fe.cassano.yaep.visitors.LispOutputVisitor;

public class LispOutputVisitorFactoryMethod implements IVisitorFactoryMethod {

	@Override
	public IVisitor getInstance(IEnvironment env,final IEnvironment fun) {
		return new LispOutputVisitor();
	}

}
