package it.fe.cassano.yaep.visitors.factories;

import it.fe.cassano.yaep.models.IEnvironment;
import it.fe.cassano.yaep.visitors.IVisitor;
import it.fe.cassano.yaep.visitors.ShowTreeVisitor;

public class ShowTreeVisitorFactoryMethod implements IVisitorFactoryMethod {

	@Override
	public IVisitor getInstance(final IEnvironment env,final IEnvironment fun) {	
		return new ShowTreeVisitor(env,fun);
	}

}
