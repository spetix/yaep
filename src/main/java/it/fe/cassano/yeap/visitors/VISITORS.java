package it.fe.cassano.yeap.visitors;

import java.util.List;
import java.util.Vector;

import it.fe.cassano.yeap.visitors.factories.EvalVisitorFactoryMethod;
import it.fe.cassano.yeap.visitors.factories.IVisitorFactoryMethod;

public enum VISITORS {
	
	EvalVisitor("Evaluate Expression", new EvalVisitorFactoryMethod()),
	
	;
	
	
	private final String description;
	private final IVisitorFactoryMethod method;

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
