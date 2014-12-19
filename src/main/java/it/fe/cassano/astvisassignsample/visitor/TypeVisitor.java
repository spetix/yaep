package it.fe.cassano.astvisassignsample.visitor;

import java.util.Collections;
import java.util.Map;

import it.fe.cassano.astvisassignsample.ast.AssignExp;
import it.fe.cassano.astvisassignsample.ast.DivideExp;
import it.fe.cassano.astvisassignsample.ast.Exp;
import it.fe.cassano.astvisassignsample.ast.FunCodeExp;
import it.fe.cassano.astvisassignsample.ast.FunDefineExp;
import it.fe.cassano.astvisassignsample.ast.FunExp;
import it.fe.cassano.astvisassignsample.ast.FunSignExp;
import it.fe.cassano.astvisassignsample.ast.IdentExp;
import it.fe.cassano.astvisassignsample.ast.IdentValExp;
import it.fe.cassano.astvisassignsample.ast.MinusExp;
import it.fe.cassano.astvisassignsample.ast.NumExp;
import it.fe.cassano.astvisassignsample.ast.OpExp;
import it.fe.cassano.astvisassignsample.ast.PlusExp;
import it.fe.cassano.astvisassignsample.ast.ProductExp;
import it.fe.cassano.astvisassignsample.ast.RealExp;
import it.fe.cassano.astvisassignsample.ast.SeqExp;
import it.fe.cassano.astvisassignsample.ast.UnaryMinusExp;

public class TypeVisitor implements IVisitor {
	
	Object type;
	protected final Map<String, ExpType> env;
	protected final Map<String, FunCodeExp> library;

	public TypeVisitor(final Map<String, ExpType> env, final Map<String,FunCodeExp> library) {
		this.env=env;
		this.library=library;
		
	}
	
	public TypeVisitor(final Map<String, ExpType> env)
	{
		this.env = env;
		this.library = Collections.<String,FunCodeExp>emptyMap();
	}
	
	public Object getVal()
	{
		return type;
	}
	
	void visitOpExp(OpExp exp)
	{
		exp.left().accept(this); ExpType lType =  (ExpType) getVal();
		exp.right().accept(this); ExpType rType = (ExpType) getVal();
		
		type = ExpType.evalReturnType(lType,rType);
		
	}
	

	@Override
	public void visit(NumExp exp) {
		type = ExpType.NumExp;

	}

	@Override
	public void visit(PlusExp exp) {
		visitOpExp(exp);

	}

	@Override
	public void visit(MinusExp exp) {
		visitOpExp(exp);
	}

	@Override
	public void visit(ProductExp exp) {
		visitOpExp(exp);
	}

	@Override
	public void visit(DivideExp exp) {
		visitOpExp(exp);
	}

	@Override
	public void visit(RealExp realExp) {
		type = ExpType.RealExp;
	}

	@Override
	public void visit(FunExp funExp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(UnaryMinusExp unaryMinusExp) {
		unaryMinusExp.expr().accept(this); ExpType eType = (ExpType) getVal();
		type = eType;
	}

	@Override
	public void visit(AssignExp assignExp) {
		assignExp.left().accept(this);
		final String id = ((IdentExp)assignExp.left()).getName();
		assignExp.right().accept(this); 
		type = getVal();
		this.env.put(id,(ExpType) type);

	}

	@Override
	public void visit(IdentExp e) {
		String id = e.getName();
		type = id;
	}

	@Override
	public void visit(IdentValExp e) {
		String id = e.getName();
		 type = ((ExpType) env.get(id));
	}

	/**
	 * Sequence type is conventionally set to last type seen in sequence.
	 * This make sense as all previous parts of sequence are just assignments.
	 * @SeqExp a sequence
	 */
	@Override
	public void visit(SeqExp seqExp) {
		seqExp.left().accept(this); ExpType lType = (ExpType) getVal();
		seqExp.right().accept(this); ExpType rType = (ExpType) getVal();
		type = rType; 
	}

	@Override
	public void visit(FunDefineExp funDefineExp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FunSignExp funSignExp) {
		// TODO Auto-generated method stub
		
	}

}
