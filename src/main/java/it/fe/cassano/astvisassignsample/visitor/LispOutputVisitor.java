package it.fe.cassano.astvisassignsample.visitor;

import it.fe.cassano.astvisassignsample.ast.DivideExp;
import it.fe.cassano.astvisassignsample.ast.FunExp;
import it.fe.cassano.astvisassignsample.ast.MinusExp;
import it.fe.cassano.astvisassignsample.ast.NumExp;
import it.fe.cassano.astvisassignsample.ast.OpExp;
import it.fe.cassano.astvisassignsample.ast.PlusExp;
import it.fe.cassano.astvisassignsample.ast.ProductExp;
import it.fe.cassano.astvisassignsample.ast.RealExp;

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
	public void visit(RealExp realExp) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(FunExp funExp) {
		// TODO Auto-generated method stub
		
	}

}
