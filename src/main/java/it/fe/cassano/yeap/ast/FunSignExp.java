package it.fe.cassano.yeap.ast;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.fe.cassano.yeap.visitors.ExpType;
import it.fe.cassano.yeap.visitors.IVisitor;

public class FunSignExp extends Exp{
	
	private final List<ExpType> params;
	private final String fName;
	//final public ExpType rType;

	/*
	public FunSignExp(final String fName, final ExpType rType, final List<ExpType> params)
	{
		this.fName = fName;
		this.params = params;
		this.rType = rType;
	}
	*/
	
	public FunSignExp(final String fName, final List<ExpType> params)
	{
		this.fName = fName;
		this.params = params;
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}
	
	public String signature()
	{
		return FunSignExp.signature(this.fName, params);
	}
	
	public static String signature (String name, List<ExpType> inputParams)
	{		
		return name +"(" + StringUtils.join(inputParams,",") +")";
	}
	

}
