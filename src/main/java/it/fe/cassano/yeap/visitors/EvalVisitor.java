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
import it.fe.cassano.yeap.ast.MulExp;
import it.fe.cassano.yeap.ast.NumExp;
import it.fe.cassano.yeap.ast.PlusExp;
import it.fe.cassano.yeap.ast.RealExp;
import it.fe.cassano.yeap.ast.SeqExp;
import it.fe.cassano.yeap.ast.UnaryMinusExp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EvalVisitor implements IVisitor {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(EvalVisitor.class);

	protected Object result;
	protected final IEnvironment environment;
	protected final Map<String, FunCodeExp> functionAliases;
	protected final List<Pair<String,Object>> sequenceResults;

	private int expNumber;

	/**
	 * EvalVisitor constructor. Needs to know which is the current environment
	 * 
	 * @param env
	 */
	public EvalVisitor(final IEnvironment env) {
		this.environment = env;
		functionAliases = new HashMap<String, FunCodeExp>();
		sequenceResults = new ArrayList<Pair<String,Object>>();
		this.expNumber = 0; // Count of evaluated expression;
	}

	/**
	 * Method to obtain last evaluated expr
	 */
	public Object getVal() {
		LOGGER.debug("last evaluation returned {}", result);
		return result;
	}

	public List<Pair<String,Object>> getResults() {
		return Collections.unmodifiableList(this.sequenceResults);
	}

	/**
	 * returns an unmodifiable rappresentation of current environment
	 * 
	 * @return
	 */
	public Map<String, Object> getEnvironment() {
		return this.environment.toUnmodifiableMap();
	}

	/* VISIT Functions */

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
		Object val = this.environment.getVal(id);
		if (val == null) {
			LOGGER.warn("Variable {} not defined in environment", id);
		} else {

			result = val;
		}

	}

	/**
	 * visit sequence, if getVal is number return the expression result.
	 */
	public void visit(SeqExp e) {
		e.left().accept(this);
		Object lResult = getVal();
		if ((lResult != null) && !(e.left() instanceof SeqExp)) 
		{
			final String key = "" + this.expNumber + ":" + e.left().toString();
			this.sequenceResults.add(Pair.of(key, lResult));
			this.expNumber++;
			LOGGER.debug("Evalued {} as {}", key, lResult);
		}

		e.right().accept(this);
		Object rResult = getVal();
		if (rResult != null && !(e.right() instanceof SeqExp)) {
			final String key = "" + this.expNumber + ":" + e.right().toString();
			this.sequenceResults.add(Pair.of(key, rResult));
			this.expNumber++;
			LOGGER.debug("Evalued {} as {}", key, rResult);
		}
	}

	@Override
	public void visit(RealExp realExp) {
		result = realExp.getValue();
	}

	public void visit(DivExp exp) {
		exp.left().accept(this);
		Object l = getVal();
		exp.right().accept(this);
		Object r = getVal();
		// OMG!
		if (l instanceof Integer && r instanceof Integer) {
			result = (int) l / (int) r;
		}

		else if (l instanceof Integer && r instanceof Double) {
			double tl = (int) l;
			double res = tl / ((double) r);
			result = res;
		} else if (r instanceof Integer && l instanceof Double) {
			double tr = (int) r;
			double res = ((double) l) / tr;
			result = res;
		}

		else {
			result = (double) (((double) l) / ((double) r));
		}
	}

	@Override
	public void visit(FunExp funExp) {
		/*
		 * List<Object> evaluedParams = new ArrayList<Object>(); funExp for( Exp
		 * param: funExp.getParams()) { param.accept(this);
		 * evaluedParams.add(getVal()); } Callme fEval = new CallMe(funExp)
		 */

	}

	public void visit(MinusExp exp) {
		exp.left().accept(this);
		Object l = getVal();
		exp.right().accept(this);
		Object r = getVal();
		// OMG!
		if (l instanceof Integer && r instanceof Integer) {
			result = (int) l - (int) r;
		}

		else if (l instanceof Integer && r instanceof Double) {
			double tl = (int) l;
			double res = tl - ((double) r);
			result = res;
		} else if (r instanceof Integer && l instanceof Double) {
			double tr = (int) r;
			double res = ((double) l) - tr;
			result = res;
		}

		else {
			result = (double) (((double) l) - ((double) r));
		}
	}

	public void visit(NumExp exp) {
		result = exp.getValue();
	}

	public void visit(PlusExp exp) {
		exp.left().accept(this);
		Object l = getVal();
		exp.right().accept(this);
		Object r = getVal();
		// OMG!
		if (l instanceof Integer && r instanceof Integer) {
			result = (int) l + (int) r;
		}

		else if (l instanceof Integer && r instanceof Double) {
			double tl = (int) l;
			double res = tl + ((double) r);
			result = res;
		} else if (r instanceof Integer && l instanceof Double) {
			double tr = (int) r;
			double res = ((double) l) + tr;
			result = res;
		}

		else {
			result = (double) (((double) l) + ((double) r));
		}
	}

	public void visit(MulExp exp) {
		exp.left().accept(this);
		Object l = getVal();
		exp.right().accept(this);
		Object r = getVal();
		// OMG!
		if (l instanceof Integer && r instanceof Integer) {
			result = (int) l * (int) r;
		}

		else if (l instanceof Integer && r instanceof Double) {
			double tl = (int) l;
			double res = tl * ((double) r);
			result = res;
		} else if (r instanceof Integer && l instanceof Double) {
			double tr = (int) r;
			double res = ((double) l) * tr;
			result = res;
		}

		else {
			result = (double) (((double) l) * ((double) r));
		}

	}

	@Override
	public void visit(UnaryMinusExp unaryMinusExp) {
		Object l = getVal();
		if ((l instanceof Double)) {
			result = -(double) l;
		} else {
			result = -(int) l;
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

	@Override
	public void visit(Exp exp) {
		exp.accept(this);
		// TODO
	}

}
