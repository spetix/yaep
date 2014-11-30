package it.fe.cassano.astvisassignsample.tokenizer;

public class Token {

	final private Object val;
	final private TOKTYPE type;

	public Token(final TOKTYPE t, final Object val) {
		this.val = val;
		this.type = t;
	}

	public Object getVal() {
		return val;
	}

	public TOKTYPE getType() {
		return type;
	}

}
