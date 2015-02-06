package it.fe.cassano.yeap.visitors.factories;

import it.fe.cassano.yeap.models.IEnvironment;
import it.fe.cassano.yeap.visitors.IVisitor;


/**
 * Method to obtain a visitor instance
 * @author ccassano
 *
 */
public interface IVisitorFactoryMethod {

	public IVisitor getInstance(final IEnvironment env,final IEnvironment fun);
}
