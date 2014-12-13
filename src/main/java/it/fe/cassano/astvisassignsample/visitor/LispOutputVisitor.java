package it.fe.cassano.astvisassignsample.visitor;

import it.fe.cassano.astvisassignsample.ast.DivideExp;
import it.fe.cassano.astvisassignsample.ast.Exp;
import it.fe.cassano.astvisassignsample.ast.FunExp;
import it.fe.cassano.astvisassignsample.ast.MinusExp;
import it.fe.cassano.astvisassignsample.ast.NumExp;
import it.fe.cassano.astvisassignsample.ast.OpExp;
import it.fe.cassano.astvisassignsample.ast.PlusExp;
import it.fe.cassano.astvisassignsample.ast.ProductExp;
import it.fe.cassano.astvisassignsample.ast.RealExp;

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


}
