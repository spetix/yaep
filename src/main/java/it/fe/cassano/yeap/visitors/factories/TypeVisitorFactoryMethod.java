package it.fe.cassano.yeap.visitors.factories;

import it.fe.cassano.yeap.models.IEnvironment;
import it.fe.cassano.yeap.visitors.IVisitor;
import it.fe.cassano.yeap.visitors.TypeVisitor;

public class TypeVisitorFactoryMethod implements IVisitorFactoryMethod {

	@Override
	public IVisitor getInstance(final IEnvironment env, final IEnvironment fun) {
		// TODO Auto-generated method stub
		return new TypeVisitor(env);
	}

}
