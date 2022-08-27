package it.fe.cassano.yeap.visitors.factories;

import it.fe.cassano.yeap.models.IEnvironment;
import it.fe.cassano.yeap.visitors.IVisitor;
import it.fe.cassano.yeap.visitors.ShowTreeVisitor;

public class ShowTreeVisitorFactoryMethod implements IVisitorFactoryMethod {

	@Override
	public IVisitor getInstance(final IEnvironment env,final IEnvironment fun) {	
		return new ShowTreeVisitor(env,fun);
	}

}
