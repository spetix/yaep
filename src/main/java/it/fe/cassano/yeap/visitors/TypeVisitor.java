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
import it.fe.cassano.yeap.ast.NumExp;
import it.fe.cassano.yeap.ast.OpExp;
import it.fe.cassano.yeap.ast.PlusExp;
import it.fe.cassano.yeap.ast.MulExp;
import it.fe.cassano.yeap.ast.RealExp;
import it.fe.cassano.yeap.ast.SeqExp;
import it.fe.cassano.yeap.ast.UnaryMinusExp;

import java.util.HashMap;
import java.util.Map;

public class TypeVisitor implements IVisitor {
	
	Object type;
	protected final IEnvironment env;
	protected final Map<String, FunCodeExp> library = new HashMap<String,FunCodeExp>();

	public TypeVisitor(IEnvironment env) {
	
		this.env = env;
		//this.library = Collections.<String,FunCodeExp>emptyMap();
	}
	
	public Object getVal()
	{
		return type;
	}
	
	public void visit(Exp e)
	{
		e.accept(this);
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
	public void visit(MulExp exp) {
		visitOpExp(exp);
	}

	@Override
	public void visit(DivExp exp) {
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
		this.env.add(id,(ExpType) type);

	}

	@Override
	public void visit(IdentExp e) {
		String id = e.getName();
		type = id;
	}

	@Override
	public void visit(IdentValExp e) {
		String id = e.getName();
		 type = ((ExpType) this.env.getVal(id));
	}

	/**
	 * Sequence type is conventionally set to last type seen in sequence.
	 * This make sense as all previous parts of sequence are just assignments.
	 * @SeqExp a sequence
	 */
	@Override
	public void visit(SeqExp seqExp) {
		// seqExp.left().accept(this); ExpType lType = (ExpType) getVal();
		seqExp.right().accept(this); ExpType rType = (ExpType) getVal();
		type = rType; 
	}

	@Override
	public void visit(FunDefineExp funDefineExp) {
		funDefineExp.accept(this);
	}

	@Override
	public void visit(FunSignExp funSignExp) {
		funSignExp.accept(this); ExpType numtype = (ExpType) getVal();
		type = numtype;
		
	}

	@Override
	public void visit(FunCodeExp funCodeExp) {
		funCodeExp.accept(this); 
	}

}
