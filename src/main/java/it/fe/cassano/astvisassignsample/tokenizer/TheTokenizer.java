package it.fe.cassano.astvisassignsample.tokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Queue;
import java.util.Stack;

public class TheTokenizer implements ITokenizer {

	private BufferedReader reader;
	private Queue<String> currentLine;

	public TheTokenizer(final Reader r) {
		this.reader = new BufferedReader(r);
	}

	public String getCurrent() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasNext() throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	public int nextTokenIs() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	public String peekCurrent() {
		// TODO Auto-generated method stub
		return null;
	}
//
//	public void nextToken() throws IOException {
//		if (this.currentLine.isEmpty())
//		{
//			enqueue()
//		}
//		return 0;
//	}

	public int nextToken() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

}
