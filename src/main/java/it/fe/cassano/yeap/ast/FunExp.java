package it.fe.cassano.yeap.ast;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.fe.cassano.yeap.visitor.IVisitor;


public class FunExp extends Exp {
	
	
	final protected List<Exp> params;
	final protected String name;

	public FunExp(final String name, final List<Exp> params)
	{
		this.name = name;
		this.params = params;
	}
	
	public String toString() 
	{
		String plist = StringUtils.join(params,",");
		return "(" +this.name+"("+plist+"))";
	}
	
	public Float getValue()
	{
		// TODO
		return 0F;
	}
	
//	@Override
//	public int eval() {
//		return this.val;
//	}
	
	 public void accept(final IVisitor v)
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
