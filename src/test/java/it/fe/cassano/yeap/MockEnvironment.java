package it.fe.cassano.yeap;

import java.util.HashMap;

import it.fe.cassano.yeap.visitors.IEnvironment;

public class MockEnvironment implements IEnvironment {
	
	final HashMap<String, Object> env = new HashMap<String,Object>();

	@Override
	public void add(String key, Object value) {
		this.env.put(key,value);

	}

	@Override
	public void remove(String key) {
		this.env.remove(key);

	}

	@Override
	public void clear() {
		this.env.clear();
	}

	@Override
	public Object getVal(String key) {
		return this.env.get(key);
	}

}
