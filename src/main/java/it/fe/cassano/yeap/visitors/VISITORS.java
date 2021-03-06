package it.fe.cassano.yeap.visitors;

import it.fe.cassano.yeap.visitors.factories.EvalVisitorFactoryMethod;
import it.fe.cassano.yeap.visitors.factories.IVisitorFactoryMethod;
import it.fe.cassano.yeap.visitors.factories.LispOutputVisitorFactoryMethod;
import it.fe.cassano.yeap.visitors.factories.ShowTreeVisitorFactoryMethod;

import java.util.Vector;

public enum VISITORS {
	
	EvalVisitor("Evaluate Expression", new EvalVisitorFactoryMethod()),
	ShowTreeVisitor("Show Expression tree", new ShowTreeVisitorFactoryMethod()),
	LispVisitor("Produce lisp-like code", new LispOutputVisitorFactoryMethod()),
	
	;
	
	
	public final String description;
	public final IVisitorFactoryMethod method;

	VISITORS(final String description, final IVisitorFactoryMethod method)
	{
		this.description = description;
		this.method = method;
	}
	
	@Override
	public String toString()
	{
		return this.description;
		
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
