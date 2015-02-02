package it.fe.cassano.yeap.visitors.factories;

import it.fe.cassano.yeap.visitors.IEnvironment;
import it.fe.cassano.yeap.visitors.IVisitor;

public interface IVisitorFactoryMethod {

	public IVisitor getInstance(final IEnvironment env);
}
