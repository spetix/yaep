package it.fe.cassano.yaep.models;

import java.util.Map;

public interface IEnvironment<T> {
	
	/**
	 * Add variable and its value to the environment,
	 * if variable exists, the value is overwritten
	 * 
	 * @param key
	 * @param value
	 */
	public void add(final String key, final T value);
	
	/**
	 * remove given variable from environment
	 * @param key
	 */
	public void remove(final String key);
	
	/**
	 * Remove all elemements from environment
	 */
	public void clear();
	
	/**
	 * 
	 */
	public T getVal(final String key);
	
	public Map<String, T> toUnmodifiableMap();
}
