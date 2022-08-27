package it.fe.cassano.yeap.tokenizer;

import it.fe.cassano.yeap.ccparser.Token;


public interface ITokenizer {

	Token getNextToken();
	Token getToken(int index);
}
