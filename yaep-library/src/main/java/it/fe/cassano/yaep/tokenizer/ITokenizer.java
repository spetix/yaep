package it.fe.cassano.yaep.tokenizer;

import it.fe.cassano.yaep.ccparser.Token;


public interface ITokenizer {

	Token getNextToken();
	Token getToken(int index);
}
