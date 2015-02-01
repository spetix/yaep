package it.fe.cassano.yeap.tokenizer;

import it.fe.cassano.astvisassignsample.ccparser.Token;


public interface ITokenizer {

	Token getNextToken();
	Token getToken(int index);
}
