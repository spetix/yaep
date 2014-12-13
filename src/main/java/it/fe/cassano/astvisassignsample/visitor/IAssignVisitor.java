package it.fe.cassano.astvisassignsample.visitor;

import it.fe.cassano.astvisassignsample.ast.AssignExp;
import it.fe.cassano.astvisassignsample.ast.IdentExp;
import it.fe.cassano.astvisassignsample.ast.IdentValExp;
import it.fe.cassano.astvisassignsample.ast.SeqExp;

public interface IAssignVisitor extends IVisitor {

	public abstract void visit(IdentExp e);
	public abstract void visit(IdentValExp e);
	public abstract void visit(SeqExp seqExp);
	public abstract void visit(AssignExp assignExp);

}
