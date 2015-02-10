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

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public interface IVisitor {

	/**
	 * returns the result of last evaluated expression
	 * @return
	 */
	public abstract Object getVal();
	
	/**
	 * return an immutable map containing the results of all parsed expressions (and their ordinal position)
	 * @return
	 */
	public abstract List<Pair<String, Object>> getResults();
	
	/* AST Visit related methods */
	
	public abstract void visit(final Exp exp) throws Exception;	
	public abstract void visit(final NumExp numExp);
	public abstract void visit(final PlusExp plusExp) throws Exception;
	public abstract void visit(final MinusExp minusExp) throws Exception;
	public abstract void visit(final MulExp mulExp) throws Exception;
	public abstract void visit(final DivExp divExp) throws Exception;
	public abstract void visit(final RealExp realExp);
	public abstract void visit(final FunExp funExp) throws Exception;
	public abstract void visit(final UnaryMinusExp unaryMinusExp) throws Exception;
	public abstract void visit(AssignExp assignExp) throws Exception;
	public abstract void visit(IdentExp identExp);
	public abstract void visit(IdentValExp identValExp);
	public abstract void visit(SeqExp seqExp) throws Exception;
	public abstract void visit(FunDefineExp funDefineExp) throws Exception;
	public abstract void visit(FunSignExp funSignExp) throws Exception;
	public abstract void visit(FunCodeExp funCodeExp) throws Exception;
	
	
	
}
