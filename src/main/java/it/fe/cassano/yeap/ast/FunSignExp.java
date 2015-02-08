package it.fe.cassano.yeap.ast;

import it.fe.cassano.yeap.visitors.ExpType;
import it.fe.cassano.yeap.visitors.IVisitor;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class FunSignExp extends Exp{
	
	private final List<ExpType> params;
	public final String name;
	//final public ExpType rType;

	/*
	public FunSignExp(final String name, final ExpType rType, final List<ExpType> params)
	{
		this.fName = name;
		this.params = params;
		this.rType = rType;
	}
	*/
	
	public FunSignExp(final String fName, final List<ExpType> params)
	{
		this.name = fName;
		this.params = params;
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}
	
	public String signature()
	{
		return FunSignExp.produceSignature(this.name, params);
	}
	
	@Override
	public String toString()
	{
		return this.signature();
	}
	
	public static String produceSignature (String name, List<ExpType> inputParams)
	{		
		return name +"(" + StringUtils.join(inputParams,",") +")";
	}

	public List<ExpType> getParams() {
		return Collections.unmodifiableList(this.params);
	}


}
