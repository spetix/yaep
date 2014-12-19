package it.fe.cassano.astvisassignsample.visitor;

import it.fe.cassano.astvisassignsample.ast.AssignExp;
import it.fe.cassano.astvisassignsample.ast.DivideExp;
import it.fe.cassano.astvisassignsample.ast.FunDefineExp;
import it.fe.cassano.astvisassignsample.ast.FunExp;
import it.fe.cassano.astvisassignsample.ast.FunSignExp;
import it.fe.cassano.astvisassignsample.ast.IdentExp;
import it.fe.cassano.astvisassignsample.ast.IdentValExp;
import it.fe.cassano.astvisassignsample.ast.MinusExp;
import it.fe.cassano.astvisassignsample.ast.NumExp;
import it.fe.cassano.astvisassignsample.ast.PlusExp;
import it.fe.cassano.astvisassignsample.ast.ProductExp;
import it.fe.cassano.astvisassignsample.ast.RealExp;
import it.fe.cassano.astvisassignsample.ast.SeqExp;
import it.fe.cassano.astvisassignsample.ast.UnaryMinusExp;

public interface IVisitor {

	public void visit(final NumExp exp);
	public void visit(final PlusExp exp);
	public void visit(final MinusExp exp);
	public void visit(final ProductExp exp);
	public void visit(final DivideExp exp);
	public void visit(final RealExp realExp);
	public void visit(final FunExp funExp);
	public void visit(final UnaryMinusExp unaryMinusExp);
	public abstract void visit(AssignExp assignExp);
	public abstract void visit(IdentExp e);
	public abstract void visit(IdentValExp e);
	public abstract void visit(SeqExp seqExp);
	public Object getVal();
	public void visit(FunDefineExp funDefineExp);
	public void visit(FunSignExp funSignExp);
	
	
	
}
