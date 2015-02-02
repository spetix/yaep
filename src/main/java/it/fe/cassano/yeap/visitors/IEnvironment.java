package it.fe.cassano.yeap.visitors;

public interface IEnvironment {
	
	/**
	 * Add variable and its value to the environment,
	 * if variable exists, the value is overwritten
	 * 
	 * @param key
	 * @param value
	 */
	public void add(final String key, final Object value);
	
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
	public Object getVal(final String key);
}
