package it.fe.cassano.yeap.functionlibrary;

import it.fe.cassano.yeap.visitors.ExpType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.reflect.MethodUtils;

public class Callme<T extends Number>
{
	final private Vector<Class<?>> p;
	final Pattern m = Pattern.compile("(?<cname>[A-Za-z0-9\\.]+)\\.(?<fname>[a-z][a-zA-Z0-9]+)");
	final private Method function;
	
	
	public Callme(final String method, final List<ExpType> params) throws ClassNotFoundException, NoSuchMethodException, SecurityException
	{
		this.p = new Vector<Class<?>>();
		for(ExpType t:params)
		{
			p.add(t.classType());
		}
		Class<?>[] pType = new  Class<?>[p.size()];
		pType = p.toArray(pType);
		Matcher mm = m.matcher(method);
		mm.find();
		final String funName = mm.group("fname");
		Class<?> funClass = Class.forName(mm.group("cname"));
		
		this.function = MethodUtils.getMatchingAccessibleMethod(funClass, funName, pType);	
		
	}
	

	
	@SuppressWarnings("unchecked")
	public
	T invoke(List<? extends Object> params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		return (T) this.function.invoke(null, params.toArray());
		
	}
	
	
	
	
}