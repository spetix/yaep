package it.fe.cassano.astvisassignsample.tokenizer;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;


public class ExpressionTokenizer extends StreamTokenizer implements ITokenizer {
	
	
	public ExpressionTokenizer(final Reader r) throws IOException
	{
		super(r);
		ExpressionTokenizer.configure(this);
		// needed to correctly obtain first get token:
		this.hasNext();
	}

	public String getCurrent() throws IOException {
		final String retval;
		switch(this.ttype)
		{
		case StreamTokenizer.TT_NUMBER: {retval = "" + (int)this.nval; break;}
		case StreamTokenizer.TT_WORD: {retval = this.sval; break;}
		default: retval="";
		}
		super.nextToken();
		return retval;
	}
	
	
	public String peekCurrent()
	{
		final String retval;
		switch(this.ttype)
		{
		case StreamTokenizer.TT_NUMBER: {retval = "" + (int)this.nval; break;}
		case StreamTokenizer.TT_WORD: {retval = this.sval; break;}
		default: retval="";
		}
		return retval;
	
	}

	public boolean hasNext() throws IOException {
		this.pushBack();
		int type = this.nextToken();
		return (type != StreamTokenizer.TT_EOF);
	}

	public int nextTokenIs() throws IOException {
		this.pushBack();
		return this.nextToken();
	}
	
	// TODO: obtain a decent way to externalize this:
	protected static void configure(final StreamTokenizer tokenizer) {
		tokenizer.resetSyntax();
		tokenizer.wordChars((int)'0',(int)'9');
		tokenizer.wordChars((int)'a',(int)'z');
		tokenizer.wordChars((int)'+',(int)'+');
		tokenizer.wordChars((int)'-',(int)'-');
		tokenizer.wordChars((int)'*',(int)'*');
		tokenizer.wordChars((int)'/',(int)'/');
		tokenizer.wordChars((int)'=',(int)'=');
		tokenizer.wordChars((int)'(',(int)'(');
		tokenizer.wordChars((int)')',(int)')');
		tokenizer.eolIsSignificant(false);	
		tokenizer.whitespaceChars((int)' ',(int)' ');
		// tokenizer.parseNumbers();
	}

	


}