package it.fe.cassano.astvisassignsample.tokenizer;

import java.io.IOException;

public interface ITokenizer {
	
	public String getCurrent() throws IOException;
	
	public boolean hasNext() throws IOException;
	
	public int nextTokenIs() throws IOException;

	public String peekCurrent();

	public int nextToken() throws IOException;
	

}
