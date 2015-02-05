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

import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

public class LispOutputVisitor implements IVisitor, IEval<String> {

	String curs = ""; // memorizzazione risultato
	public LispOutputVisitor() {
		
	}
	public String getVal() { return curs; } // lettura risultato
	protected void visitOpExp(OpExp e) throws Exception{
	e.left().accept(this); String sleft = getVal();
	e.right().accept(this); String sright = getVal();
	curs = "(" + e.opName() + " " + sleft + " " + sright + ")";
	}
	public void visit( PlusExp e ) throws Exception { visitOpExp(e); }
	public void visit( MinusExp e ) throws Exception { visitOpExp(e); }
	public void visit( MulExp e ) throws Exception { visitOpExp(e); }
	public void visit( DivExp e ) throws Exception { visitOpExp(e); }
	public void visit( NumExp e ) {
	curs = "" + e.getValue();
	}
	@Override
	public void visit(RealExp e) {
		curs = ""+ e.getValue();
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
		curs = "(" + funExp.opName() + " " + StringUtils.join(res," ") + ")";
		
		
	}
	@Override
	public void visit(UnaryMinusExp e) throws Exception {
		e.expr().accept(this);
		String ex = getVal();
		curs = "(- "+ex+")";
		
	}
	@Override
	public void visit(AssignExp assignExp) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(IdentExp e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(IdentValExp e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(SeqExp seqExp) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(FunDefineExp funDefineExp) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(FunSignExp funSignExp) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(FunCodeExp funCodeExp) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(Exp exp) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Pair<String, Object>> getResults() {
		// TODO Auto-generated method stub
		return null;
	}


}
