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
import it.fe.cassano.yeap.functionlibrary.Callme;
import it.fe.cassano.yeap.models.IEnvironment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Produce an evalutation of an Expression Tree. If more than an expression is
 * present it's possible to collect the list of results using the method
 * getResults getVal returns last evaluated result getEnvironment is a map
 * containing the list of variables evaluated so far
 * 
 * @author ccassano
 *
 */
public class EvalVisitor implements IVisitor {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(EvalVisitor.class);

	protected Object result;
	protected final IEnvironment environment;
	protected final IEnvironment funLibrary;
	protected final List<Pair<String, Object>> sequenceResults;

	private int expNumber;

	/**
	 * EvalVisitor constructor. Needs to know which is the current environment
	 * 
	 * @param env, variables definitions present in environment
	 * @param funLibrary, function aliased in environment
	 */
	public EvalVisitor(final IEnvironment env, final IEnvironment funLibrary) {
		this.environment = env;
		this.funLibrary = funLibrary;
		sequenceResults = new ArrayList<Pair<String, Object>>();
		this.expNumber = 0; // Count of evaluated expression;
	}

	/**
	 * Method to obtain last evaluated expr
	 */
	public Object getVal() {
		LOGGER.debug("last evaluation returned {}", result);
		return result;
	}

	/**
	 * Results collected so far
	 */
	public List<Pair<String, Object>> getResults() {
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

	/**
	 * Visit of an assignement expression: - visit the left part to obtain the
	 * id - visit the right part to get the value to associate - set in to the
	 * environment the pair.
	 * 
	 * @throws Exception
	 */
	public void visit(AssignExp e) throws Exception {
		// Visiting (hopefully) identExp:
		e.left().accept(this);
		final String id = (String) getVal();

		// Visiting the sub-tree containing the value;
		e.right().accept(this);
		result = getVal();
		this.environment.add(id, result);
	}

	/**
	 * Visit IdentExp, obtain the identifier name
	 */
	public void visit(IdentExp e) {
		String id = e.getName();
		result = id;
	}

	/**
	 * Visit gets the Identifier name and gets its value from the environment
	 */
	public void visit(IdentValExp e) throws NoSuchElementException {
		String id = e.getName();
		Object val = this.environment.getVal(id);
		if (val == null) {
			LOGGER.warn("Variable {} not defined in environment", id);
			throw new NoSuchElementException(id + " is undefined");
		} else {

			result = val;
		}

	}

	/**
	 * visit sequence, if getVal is number return the expression result.
	 * 
	 * @throws Exception
	 */
	public void visit(SeqExp e) throws Exception {
		e.left().accept(this);
		Object lResult = getVal();

		if ((lResult != null) && !(e.left() instanceof SeqExp)) {
			// We have a result, adding it to result list
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

	/**
	 * Obtain a realExp value
	 */
	@Override
	public void visit(RealExp realExp) {
		result = realExp.getValue();
	}

	/**
	 * Evaluate division: - evaluate expression on the left - evaluate
	 * expression on the right - fix the data type and do operation
	 * 
	 * @throws Exception
	 */
	public void visit(DivExp exp) throws Exception {
		exp.left().accept(this);
		Object l = getVal();
		exp.right().accept(this);
		Object r = getVal();
		// OMG! i believe there is a simpler way...
		/*
		 * if (l instanceof Integer && r instanceof Integer) { result = (int) l
		 * / (int) r; }
		 * 
		 * else if (l instanceof Integer && r instanceof Double) { double tl =
		 * (int) l; double res = tl / ((double) r); result = res; } else if (r
		 * instanceof Integer && l instanceof Double) { double tr = (int) r;
		 * double res = ((double) l) / tr; result = res; }
		 * 
		 * else { result = (double) (((double) l) / ((double) r)); }
		 */
		if (l instanceof Integer && r instanceof Integer) {
			result = (int) l / (int) r;
		} else {
			result = (double) ((l instanceof Double) ? ((double) l) : ((int) l))
					/ ((r instanceof Double) ? ((double) r) : ((int) r));
		}
	}

	/**
	 * Evaluate a java function.
	 */
	@Override
	public void visit(FunExp funExp) throws Exception {
		// Get function name:
		final String fName = funExp.opName();

		// Params evaluation is necessary to obtain the signatureç
		List<Object> evaluedParams = new ArrayList<Object>();
		List<ExpType> pTypes = new ArrayList<ExpType>();
		for (final Exp e : funExp.getParams()) {
			e.accept(this);
			Object tmpVal = getVal();
			if (tmpVal instanceof Double)
			{
				pTypes.add(ExpType.RealExp);
			}
			else if (tmpVal instanceof Integer)
			{
				pTypes.add(ExpType.NumExp);
			}
			else
			{
				throw new Exception("parameter contains invalid type");
			}
			evaluedParams.add(tmpVal);
		}
		
		final String signature = FunSignExp.produceSignature(fName, pTypes);
		final FunCodeExp funCodeExp = (FunCodeExp) this.funLibrary.getVal(signature);

		// Call function:
		switch (funCodeExp.retType) {
		case RealExp: {
			Callme<Double> theFunction;
			theFunction = new Callme<Double>(funCodeExp.jFunName,
					funCodeExp.params);
			result = theFunction.invoke(evaluedParams);
			break;
		}
		case NumExp: {
			Callme<Double> theFunction = new Callme<Double>(
					funCodeExp.jFunName, funCodeExp.params);
			result = theFunction.invoke(evaluedParams);
			break;
		}
		default: {
			throw new Exception("UnknownType");
		}

		}

	}

	public void visit(MinusExp exp) throws Exception {
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

	public void visit(PlusExp exp) throws Exception {
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

	public void visit(MulExp exp) throws Exception {
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
	public void visit(FunDefineExp funDefineExp) throws Exception {
		// visiting the alias part:
		funDefineExp.left().accept(this);
		String fId = (String) getVal();
		funDefineExp.right().accept(this);
		this.funLibrary.add(fId, (FunCodeExp) getVal());;
	}

	@Override
	public void visit(FunSignExp funSignExp) {
		result = funSignExp.signature();
	}

	/**
	 * Visit FunCodeExp, currently it returns itself.
	 */
	@Override
	public void visit(FunCodeExp funCodeExp) {
		result = funCodeExp;
	}

	/**
	 * Where everything begins...
	 * 
	 * @throws Exception
	 * 
	 */
	@Override
	public void visit(Exp exp) throws Exception {
		exp.accept(this);
	}

}
