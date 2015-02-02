package it.fe.cassano.yeap.visitors;

import it.fe.cassano.yeap.visitors.factories.EvalVisitorFactoryMethod;
import it.fe.cassano.yeap.visitors.factories.IVisitorFactoryMethod;
import it.fe.cassano.yeap.visitors.factories.TypeVisitorFactoryMethod;

import java.util.Vector;

public enum VISITORS {
	
	EvalVisitor("Evaluate Expression", new EvalVisitorFactoryMethod()),
	TypeVisitor("Show Types", new TypeVisitorFactoryMethod()),
	
	;
	
	
	public final String description;
	public final IVisitorFactoryMethod method;

	VISITORS(final String description, final IVisitorFactoryMethod method)
	{
		this.description = description;
		this.method = method;
	}

	public static Vector<String> getVisitors()
	{
		Vector<String> retVal = new Vector<String>();
		for (final VISITORS vis: VISITORS.values())
		{
			retVal.add(vis.description);
		}
		return retVal;
	}
}
