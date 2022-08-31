package it.fe.cassano.yaep.visitors;

import it.fe.cassano.yaep.ast.AssignExp;
import it.fe.cassano.yaep.ast.DivExp;
import it.fe.cassano.yaep.ast.Exp;
import it.fe.cassano.yaep.ast.FunCodeExp;
import it.fe.cassano.yaep.ast.FunDefineExp;
import it.fe.cassano.yaep.ast.FunExp;
import it.fe.cassano.yaep.ast.FunSignExp;
import it.fe.cassano.yaep.ast.IdentExp;
import it.fe.cassano.yaep.ast.IdentValExp;
import it.fe.cassano.yaep.ast.MinusExp;
import it.fe.cassano.yaep.ast.MulExp;
import it.fe.cassano.yaep.ast.NumExp;
import it.fe.cassano.yaep.ast.PlusExp;
import it.fe.cassano.yaep.ast.RealExp;
import it.fe.cassano.yaep.ast.SeqExp;
import it.fe.cassano.yaep.ast.UnaryMinusExp;

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
