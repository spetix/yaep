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

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

public class LispOutputVisitor implements IVisitor, IEval<String> {

	String output= ""; // memorizzazione risultato
	public LispOutputVisitor() {
		
	}
	public String getVal() { return output; } // lettura risultato
	
	
	protected void visitToOpExpression(OpExp e) throws Exception
	{
	e.left().accept(this); String sleft = getVal();
	e.right().accept(this); String sright = getVal();
	output = "(" + e.opName() + " " + sleft + " " + sright + ")";
	}
	
	public void visit( PlusExp e ) throws Exception { visitToOpExpression(e); }
	public void visit( MinusExp e ) throws Exception { visitToOpExpression(e); }
	public void visit( MulExp e ) throws Exception { visitToOpExpression(e); }
	public void visit( DivExp e ) throws Exception { visitToOpExpression(e); }
	public void visit( NumExp e ) {
	output = "" + e.getValue();
	}
	@Override
	public void visit(RealExp e) {
		output = ""+ e.getValue();
	}
	@Override
	public void visit(FunExp funExp) throws Exception {
		List<Exp> params = funExp.getParams();
		List<String> res = new Vector<String>();
		for (Exp p: params)
		{
			p.accept(this);
			res.add(getVal());
		}
		output = "(" + funExp.opName() + " " + StringUtils.join(res," ") + ")";
		
		
	}
	@Override
	public void visit(UnaryMinusExp e) throws Exception {
		e.expr().accept(this);
		String ex = getVal();
		output = "(- "+ex+")";
		
	}
	@Override
	public void visit(AssignExp assignExp) throws Exception {
		visitToOpExpression(assignExp);
		
	}
	@Override
	public void visit(IdentExp e) {
		output = e.getName();
		
	}
	@Override
	public void visit(IdentValExp e) {
		output = e.getName();
		
	}
	@Override
	public void visit(SeqExp seqExp) throws Exception {
		seqExp.left().accept(this);
		String rLeft = getVal();
		seqExp.right().accept(this);
		String rRight = getVal();
		output = rLeft + "\n" + rRight;	
	}
	@Override
	public void visit(FunDefineExp funDefineExp) throws Exception {
		funDefineExp.left().accept(this);
		String rLeft = getVal();
		funDefineExp.right().accept(this);
		String rRight = getVal();
		output = "(deffun " + rLeft + " " + rRight + ")";
	}
	
	@Override
	public void visit(FunSignExp funSignExp) {
		output = funSignExp.name + "(" + StringUtils.join(funSignExp.getParams()," ") + ")";
		
	}
	@Override
	public void visit(FunCodeExp funCodeExp) {
		StringBuffer sb = new StringBuffer();
		for (ExpType t : funCodeExp.getParams())
		{
				sb.append(" ");
				sb.append(t.getClass().getSimpleName());
		}
		output = "(call " +funCodeExp.jFunName + sb.toString() + ")";
		
	}
	@Override
	public void visit(Exp exp) throws Exception {
		exp.accept(this);
	}
	
	/** 
	 * getVal contains the final production, no need of getResults
	 */
	@Override
	public List<Pair<String, Object>> getResults() {
		return Collections.<Pair<String,Object>>emptyList();
	}

}
