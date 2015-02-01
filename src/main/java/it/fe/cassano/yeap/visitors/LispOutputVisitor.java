package it.fe.cassano.yeap.visitors;

import it.fe.cassano.yeap.ast.DivideExp;
import it.fe.cassano.yeap.ast.Exp;
import it.fe.cassano.yeap.ast.FunExp;
import it.fe.cassano.yeap.ast.MinusExp;
import it.fe.cassano.yeap.ast.NumExp;
import it.fe.cassano.yeap.ast.OpExp;
import it.fe.cassano.yeap.ast.PlusExp;
import it.fe.cassano.yeap.ast.ProductExp;
import it.fe.cassano.yeap.ast.RealExp;
import it.fe.cassano.yeap.ast.UnaryMinusExp;

import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

public class LispOutputVisitor implements IVisitor, IEval<String> {

	String curs = ""; // memorizzazione risultato
	public String getVal() { return curs; } // lettura risultato
	protected void visitOpExp(OpExp e){
	e.left().accept(this); String sleft = getVal();
	e.right().accept(this); String sright = getVal();
	curs = "(" + e.opName() + " " + sleft + " " + sright + ")";
	}
	public void visit( PlusExp e ) { visitOpExp(e); }
	public void visit( MinusExp e ) { visitOpExp(e); }
	public void visit( ProductExp e ) { visitOpExp(e); }
	public void visit( DivideExp e ) { visitOpExp(e); }
	public void visit( NumExp e ) {
	curs = "" + e.getValue();
	}
	@Override
	public void visit(RealExp e) {
		curs = ""+ e.getValue();
	}
	@Override
	public void visit(FunExp funExp) {
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
	public void visit(UnaryMinusExp e) {
		e.expr().accept(this);
		String ex = getVal();
		curs = "(- "+ex+")";
		
	}


}
