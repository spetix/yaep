package it.fe.cassano.yeap.visitors;

import it.fe.cassano.yeap.ast.AssignExp;
import it.fe.cassano.yeap.ast.DivideExp;
import it.fe.cassano.yeap.ast.FunCodeExp;
import it.fe.cassano.yeap.ast.FunDefineExp;
import it.fe.cassano.yeap.ast.FunExp;
import it.fe.cassano.yeap.ast.FunSignExp;
import it.fe.cassano.yeap.ast.IdentExp;
import it.fe.cassano.yeap.ast.IdentValExp;
import it.fe.cassano.yeap.ast.MinusExp;
import it.fe.cassano.yeap.ast.NumExp;
import it.fe.cassano.yeap.ast.PlusExp;
import it.fe.cassano.yeap.ast.ProductExp;
import it.fe.cassano.yeap.ast.RealExp;
import it.fe.cassano.yeap.ast.SeqExp;
import it.fe.cassano.yeap.ast.UnaryMinusExp;

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
	public void visit(FunCodeExp funCodeExp);
	
	
	
}
