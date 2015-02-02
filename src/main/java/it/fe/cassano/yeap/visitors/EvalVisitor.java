package it.fe.cassano.yeap.visitors;

import it.fe.cassano.yeap.ast.AssignExp;
import it.fe.cassano.yeap.ast.DivExp;
import it.fe.cassano.yeap.ast.Exp;
import it.fe.cassano.yeap.ast.FunCodeExp;
import it.fe.cassano.yeap.ast.FunDefineExp;
import it.fe.cassano.yeap.ast.FunExp;
import it.fe.cassano.yeap.ast.FunSignExp;
import it.fe.cassano.yeap.ast.IdentExp;
import it.fe.cassano.yeap.ast.IdentValExp;
import it.fe.cassano.yeap.ast.MinusExp;
import it.fe.cassano.yeap.ast.NumExp;
import it.fe.cassano.yeap.ast.PlusExp;
import it.fe.cassano.yeap.ast.MulExp;
import it.fe.cassano.yeap.ast.RealExp;
import it.fe.cassano.yeap.ast.SeqExp;
import it.fe.cassano.yeap.ast.UnaryMinusExp;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EvalVisitor implements IVisitor {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(EvalVisitor.class);

	protected Object result;
	protected final IEnvironment environment;

	public EvalVisitor(final IEnvironment env) {
		this.environment = env;
	}

	public void visit(AssignExp e) {
		e.left().accept(this);
		final String id = ((IdentExp) e.left()).getName();
		e.right().accept(this);
		result = getVal();
		this.environment.add(id, result);
	}

	public void visit(IdentExp e) {
		String id = e.getName();
		result = id;
	}

	public void visit(IdentValExp e) {
		String id = e.getName();
		Object val =  this.environment.getVal(id);
		if (val == null) {
			LOGGER.warn("Variable {} not defined in environment",id);
		} else {

			result = val;
		}

	}

	public void visit(SeqExp e) {
		e.left().accept(this);
		e.right().accept(this);
	}

	@Override
	public void visit(RealExp realExp) {
		realExp.accept(this);
	}

	public Object getVal() {
		return result;
	}

	public void visit(DivExp exp) {
		exp.left().accept(this);
		Object l = (Number) getVal();
		exp.right().accept(this);
		Number r = (Number) getVal();
		if ((l instanceof Double) || (r instanceof  Double))
		{
			result = (Double) l/(Double)r;
		}
		else
		{
			result = (Long)l/(Long)r;
		}
	}

	@Override
	public void visit(FunExp funExp) {
		// TODO Auto-generated method stub

	}

	public void visit(MinusExp exp) {
		exp.left().accept(this);
		Object l = getVal();
		exp.right().accept(this);
		Object r = getVal();
		if ((l instanceof Double) || (r instanceof  Double))
		{
			result = (Double) l-(Double)r;
		}
		else
		{
			result = (Long)l - (Long)r;
		}
	}



	public void visit(NumExp exp) {
		result = exp.getValue();
	}

	public void visit(PlusExp exp) {
		exp.left().accept(this);
		Object l =  getVal();
		exp.right().accept(this);
		Object r = getVal();
		if ((l instanceof Double) || (r instanceof  Double))
		{
			result = (Double) l+(Double)r;
		}
		else
		{
			result = (Long)l + (Long)r;
		}
	}

	public void visit(MulExp exp) {
		exp.left().accept(this);
		Object l = getVal();
		exp.right().accept(this);
		Object r = getVal();
		if ((l instanceof Double) || (r instanceof  Double))
		{
			result = (Double) l*(Double)r;
		}
		else
		{
			result = (Long)l * (Long)r;
		}
	}

	@Override
	public void visit(UnaryMinusExp unaryMinusExp) {
		Object l = getVal();
		if ((l instanceof Double))
		{
			result = -(Double) l;
		}
		else
		{
			result = -(Long)l;
		}
	}

	@Override
	public void visit(FunDefineExp funDefineExp) {
		funDefineExp.accept(this); 
		// TODO
		
	}

	@Override
	public void visit(FunSignExp funSignExp) {
		funSignExp.accept(this); 
		// TODO
	}

	@Override
	public void visit(FunCodeExp funCodeExp) {
		funCodeExp.accept(this); 
		// TODO
	}

	/**
	 * returns an unmodifiable rappresentation of current environment
	 * @return
	 */
	public Map<String,Object> getEnvironment() {
		return this.environment.toUnmodifiableMap();
	}

	@Override
	public void visit(Exp exp) {
		exp.accept(this); 
		// TODO
	}

}
