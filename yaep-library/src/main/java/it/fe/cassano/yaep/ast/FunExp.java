package it.fe.cassano.yaep.ast;

import it.fe.cassano.yaep.visitors.IVisitor;

import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class FunExp extends Exp {
	
	
	final protected List<Exp> params;
	final protected String name;

	public FunExp(final String name, final List<Exp> params)
	{
		this.name = name;
		this.params = params;
	}
	
	@Override
	public String toString() 
	{
		String plist = StringUtils.join(params,",");
		return "(" +this.name+"("+plist+"))";
	}
	

	
	 public void accept(final IVisitor v) throws Exception
	 {
	 v.visit(this);
	 }
	 
	 public List<Exp> getParams()
	 {
		return params;
	 }
	 
	 public String opName()
	 {
		 return this.name;
	 }
}
