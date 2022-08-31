package it.fe.cassano.yaep;

import it.fe.cassano.yaep.models.IEnvironment;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

final public class MockEnvironment implements IEnvironment {
	
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

	@Override
	public Map<String, Object> toUnmodifiableMap() {
		return  Collections.<String, Object> unmodifiableMap(this.env);
	}

}
