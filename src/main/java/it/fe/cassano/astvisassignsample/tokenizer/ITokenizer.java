package it.fe.cassano.astvisassignsample.tokenizer;

import it.fe.cassano.astvisassignsample.ccparser.Token;


public interface ITokenizer {

	Token getNextToken();
	Token getToken(int index);
}
