package it.fe.cassano.astvisassignsample.parser;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import it.fe.cassano.astvisassignsample.ast.Exp;
import it.fe.cassano.astvisassignsample.ast.MinusExp;
import it.fe.cassano.astvisassignsample.ast.PlusExp;
import it.fe.cassano.astvisassignsample.tokenizer.ITokenizer;

public class AssignmentParser extends Parser {

	public AssignmentParser(ITokenizer tokenizer) {
		super(tokenizer);
	}
	
	@Override
	public Exp parseExp() throws IOException 
	{
//		Exp assign = parseAssign();
		Exp termSeq = parseTerm();
		if (tokenizer.hasNext()) 
		{	
			if (StringUtils.equals("+",tokenizer.peekCurrent())) {
				tokenizer.nextToken();
				Exp right = parseTerm();
				if (right != null) { // costruzione APT a sinistra
					termSeq = new PlusExp(termSeq, right);
				} else {
					return null;
				}
			} else if (StringUtils.equals("-",tokenizer.peekCurrent())) {
				tokenizer.nextToken();
				Exp right = parseTerm();
				if (right != null) // costruzione APT a sinistra
					termSeq = new MinusExp(termSeq, right);
				else
					return null;
			} else
				return termSeq;
		}
		return termSeq;

	}

}
