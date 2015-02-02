package it.fe.cassano.yeap.visitors.factories;

import it.fe.cassano.yeap.visitors.IEnvironment;
import it.fe.cassano.yeap.visitors.IVisitor;
import it.fe.cassano.yeap.visitors.TypeVisitor;

public class TypeVisitorFactoryMethod implements IVisitorFactoryMethod {

	@Override
	public IVisitor getInstance(IEnvironment env) {
		// TODO Auto-generated method stub
		return new TypeVisitor(env);
	}

}
