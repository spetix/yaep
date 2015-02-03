package it.fe.cassano.yeap.visitors.factories;

import it.fe.cassano.yeap.visitors.IEnvironment;
import it.fe.cassano.yeap.visitors.IVisitor;
import it.fe.cassano.yeap.visitors.LispOutputVisitor;

public class LispOutputVisitorFactoryMethod implements IVisitorFactoryMethod {

	@Override
	public IVisitor getInstance(IEnvironment env) {
		return new LispOutputVisitor();
	}

}
