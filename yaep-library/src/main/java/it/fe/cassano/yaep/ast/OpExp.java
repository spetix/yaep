package it.fe.cassano.yaep.ast;


public abstract class OpExp extends Exp {

	protected Exp left, right;

	protected OpExp(final Exp left, final Exp right) {
		this.left = left;
		this.right = right;
	}

	public abstract String opName();

	// abstract public int eval();

	@Override
	public String toString() {
		return "("+left.toString() + opName() + right.toString()+")";
	}

	public Exp left() {
		return left;
	}

	public Exp right() {
		return right;
	}

	// note, cannot use visitor this way, no OpExp in visitor interface and an
	// interface doesn't help either
	// we don't want to use instanceof to understand the visited type..
	// public void accept(final IVisitor v)
	// {
	// v.visit(this);
	// }
	// it's possible to create it abstract in order to force an implementation
	// in all subclasses;

}
