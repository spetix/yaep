package it.fe.cassano.astvisassignsample.visitor;

import it.fe.cassano.astvisassignsample.ast.DivideExp;
import it.fe.cassano.astvisassignsample.ast.FunExp;
import it.fe.cassano.astvisassignsample.ast.MinusExp;
import it.fe.cassano.astvisassignsample.ast.NumExp;
import it.fe.cassano.astvisassignsample.ast.PlusExp;
import it.fe.cassano.astvisassignsample.ast.ProductExp;
import it.fe.cassano.astvisassignsample.ast.RealExp;

public interface IVisitor {

	public void visit(final NumExp exp);
	public void visit(final PlusExp exp);
	public void visit(final MinusExp exp);
	public void visit(final ProductExp exp);
	public void visit(final DivideExp exp);
	public void visit(final RealExp realExp);
	public void visit(final FunExp funExp);
	
	
	
}
