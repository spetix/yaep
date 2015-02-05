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
	public Object getVal();
	/**
	 * return an immutable map containing the results of all parsed expressions (and their ordinal position)
	 * @return
	 */
	public List<Pair<String, Object>> getResults();
	
	/* AST Visit related methods */
	
	public void visit(final Exp exp) throws Exception;	
	public void visit(final NumExp exp);
	public void visit(final PlusExp exp) throws Exception;
	public void visit(final MinusExp exp) throws Exception;
	public void visit(final MulExp exp) throws Exception;
	public void visit(final DivExp exp) throws Exception;
	public void visit(final RealExp realExp);
	public void visit(final FunExp funExp) throws Exception;
	public void visit(final UnaryMinusExp unaryMinusExp) throws Exception;
	public abstract void visit(AssignExp assignExp) throws Exception;
	public abstract void visit(IdentExp e);
	public abstract void visit(IdentValExp e);
	public abstract void visit(SeqExp seqExp) throws Exception;
	public void visit(FunDefineExp funDefineExp) throws Exception;
	public void visit(FunSignExp funSignExp);
	public void visit(FunCodeExp funCodeExp);
	
	
	
}
