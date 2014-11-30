package it.fe.cassano.astvisassignsample.parser;

import it.fe.cassano.astvisassignsample.ast.DivideExp;
import it.fe.cassano.astvisassignsample.ast.Exp;
import it.fe.cassano.astvisassignsample.ast.MinusExp;
import it.fe.cassano.astvisassignsample.ast.NumExp;
import it.fe.cassano.astvisassignsample.ast.PlusExp;
import it.fe.cassano.astvisassignsample.ast.ProductExp;
import it.fe.cassano.astvisassignsample.tokenizer.ITokenizer;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class Parser {

	protected ITokenizer tokenizer;

	public Parser(final ITokenizer tokenizer) {
		this.tokenizer = tokenizer;
	}

	public Exp parseExp() throws IOException {
		if (tokenizer.hasNext()) {
			Exp termSeq = parseTerm();
			while (tokenizer.hasNext()) {
				;
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

		} else {
			return null; // will cause a npe
		}
	}

	 Exp parseTerm() throws IOException {
		if (tokenizer.hasNext()) {
			Exp factorTerm = parseFactor();
			while (tokenizer.hasNext()) {
				if (StringUtils.equals("*",tokenizer.peekCurrent())) {
					tokenizer.nextToken();
					Exp right = parseTerm();
					if (right != null) {
						factorTerm = new ProductExp(factorTerm, right);
					} else
						return null;
				} else if (StringUtils.equals("/",tokenizer.peekCurrent())) {
					tokenizer.nextToken();
					Exp right = parseTerm();
					if (right != null) {// costruzione APT a sinistra
						
						factorTerm = new DivideExp(factorTerm, right);
					} else {
						return null;
					}
				} else {
					return factorTerm;
				}

			}
			return factorTerm;
		} else {
			return null; // will cause a npe
		}
	}

	Exp parseFactor() throws NumberFormatException, IOException {
		if (this.tokenizer.hasNext()) {
			String currentToken = this.tokenizer.getCurrent();
			if (currentToken.equals("(")) {
				Exp innerExp = parseExp();
				if (this.tokenizer.hasNext()
						&& this.tokenizer.getCurrent().equals(")")) {
					return innerExp;
				} else
					return null;
			} else if (NumberUtils.isNumber(currentToken)) {
				return new NumExp(Integer.parseInt(currentToken));
			} else
				return null;
		}
		else return null;

	}
}
