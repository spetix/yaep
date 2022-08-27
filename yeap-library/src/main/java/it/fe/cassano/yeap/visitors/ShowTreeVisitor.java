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
import it.fe.cassano.yeap.ast.OpExp;
import it.fe.cassano.yeap.ast.PlusExp;
import it.fe.cassano.yeap.ast.RealExp;
import it.fe.cassano.yeap.ast.SeqExp;
import it.fe.cassano.yeap.ast.UnaryMinusExp;
import it.fe.cassano.yeap.models.IEnvironment;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Produce an rappresentation of an Expression Tree. 
 * @author ccassano
 *
 */
public class ShowTreeVisitor implements IVisitor {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(ShowTreeVisitor.class);
	
	protected DefaultMutableTreeNode result;

	protected final IEnvironment fun;

	protected final IEnvironment env;

	
	/**
	 * EvalVisitor constructor. Needs to know which is the current environment
	 * 
	 * @param env, variables definitions present in environment
	 * @param funLibrary, function aliased in environment
	 */
	public ShowTreeVisitor(final IEnvironment env,IEnvironment fun) {
		this.env = env;
		this.fun = fun;
	}
	
	/**
	 * Build tree node method valid for all opExp derived classes
	 * @param e
	 * @throws Exception
	 */
	protected void visitToOpExpression(OpExp e) throws Exception
	{
		DefaultMutableTreeNode tNode = new DefaultMutableTreeNode(e.opName());
		e.left().accept(this);
		DefaultMutableTreeNode lval = (DefaultMutableTreeNode) getVal();
		
		tNode.add(lval);

		e.right().accept(this);
		DefaultMutableTreeNode rval = (DefaultMutableTreeNode) getVal();
		tNode.add(rval);

		result = tNode;
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
		return Collections.unmodifiableList(Collections.<Pair<String,Object>>emptyList());
	}

	/**
	 * returns an unmodifiable rappresentation of current environment
	 * 
	 * @return
	 */
	public Map<String, Object> getEnvironment() {
		return this.env.toUnmodifiableMap();
	}

	/* VISIT Functions */

	/**
	 * Visit of an assignement expression: - visit the left part to obtain the
	 * id - visit the right part to get the value to associate - set in to the
	 * environment the pair.
	 * 
	 * @throws Exception
	 */
	public void visit(AssignExp assignExp) throws Exception {
		visitToOpExpression(assignExp);
	}

	/**
	 * Visit IdentExp, obtain the identifier name
	 */
	public void visit(IdentExp e) {
		result = new DefaultMutableTreeNode(e.getName());

	}

	/**
	 * Visit gets the Identifier name 
	 */
	public void visit(IdentValExp identValExp) throws NoSuchElementException {
		result = new DefaultMutableTreeNode(identValExp.getName());
	}

	/**
	 * visit sequence, if getVal is number return the expression result.
	 * 
	 * @throws Exception
	 */
	public void visit(SeqExp seqExp) throws Exception {
		visitToOpExpression(seqExp);
	}

	/**
	 * Obtain a realExp value
	 */
	@Override
	public void visit(RealExp realExp) {
		result = new DefaultMutableTreeNode(realExp.getValue());
	}

	/**
	 * Visit DIV
	 * 
	 * @throws Exception
	 */
	public void visit(DivExp divExp) throws Exception {
		visitToOpExpression(divExp);
	}

	/**
	 * Evaluate a java function.
	 */
	@Override
	public void visit(FunExp funExp) throws Exception {
		// Get function name:
		final String fName = funExp.opName();
		final DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(fName);
		// Params evaluation is necessary to obtain the signatureç
		for (final Exp e : funExp.getParams()) {
			e.accept(this);
			final DefaultMutableTreeNode paramNode = (DefaultMutableTreeNode) getVal();
			treeNode.add(paramNode);
		}
		
		result = treeNode;
		
	}

	public void visit(final MinusExp minusExp) throws Exception {
		visitToOpExpression(minusExp);
	}

	public void visit(final NumExp numExp) {
		result = new DefaultMutableTreeNode(numExp.getValue());
	}

	public void visit(final PlusExp minusExp) throws Exception {
		visitToOpExpression(minusExp);
	}

	public void visit(final MulExp mulExp) throws Exception {
		visitToOpExpression(mulExp);

	}

	@Override
	public void visit(UnaryMinusExp unaryMinusExp) throws Exception {
		unaryMinusExp.expr().accept(this);
		final DefaultMutableTreeNode unaryMinusNode = new DefaultMutableTreeNode(unaryMinusExp.opName());
		final DefaultMutableTreeNode tmpNode = (DefaultMutableTreeNode) getVal();
		unaryMinusNode.add(tmpNode);
		result= unaryMinusNode;
	}

	@Override
	public void visit(FunDefineExp funDefineExp) throws Exception {
		visitToOpExpression(funDefineExp);
	}

	@Override
	public void visit(FunSignExp funSignExp) {
		final DefaultMutableTreeNode funSignExpNode = new DefaultMutableTreeNode(funSignExp.name);
		result = funSignExpNode;
	}

	/**
	 * Visit FunCodeExp, currently it returns itself.
	 */
	@Override
	public void visit(FunCodeExp funCodeExp) {
		final DefaultMutableTreeNode funCodeExpNode = new DefaultMutableTreeNode(FunSignExp.produceSignature(funCodeExp.jFunName, funCodeExp.getParams()));
		result = funCodeExpNode;
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
		DefaultMutableTreeNode child = (DefaultMutableTreeNode) getVal();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		root.add(child);
		result = root;
	}

}
