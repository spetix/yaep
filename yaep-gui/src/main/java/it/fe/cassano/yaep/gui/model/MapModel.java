package it.fe.cassano.yaep.gui.model;

import java.util.Collections;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.collections4.map.LinkedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.fe.cassano.yaep.models.IEnvironment;

public class MapModel extends AbstractTableModel implements
		IEnvironment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2143919124920177349L;

	private final static Logger LOGGER = LoggerFactory
			.getLogger(MapModel.class);

	private final String[] headers;
	private final LinkedMap<String, Object> environment;

	public MapModel(final String keyHeader, final String valueHeader, LinkedMap<String,Object> entity) {
		super();
		headers = new String[2];
		headers[0]=keyHeader;
		headers[1]=valueHeader;

		LOGGER.info("Initializing function library");
		this.environment = entity;
	}

	@Override
	public int getRowCount() {
		LOGGER.info("Current environment size, {}", this.environment.size());

		return this.environment.size();
	}

	@Override
	public int getColumnCount() {
		LOGGER.debug("Available columns {}", headers.length);
		return headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		LOGGER.debug("requested environment at({},{}", rowIndex, columnIndex);
		if (rowIndex < environment.size()) {
			switch (columnIndex) {
			case 0:
				return (Object) environment.get(rowIndex);
			case 1:
				return environment.getValue(rowIndex);
			default:
				return "";
			}

		} else {
			LOGGER.warn("requested unexistent row: {}", rowIndex);
			return "";
		}
	}

	/**
	 * none of the cell is editable
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public String getColumnName(int col) {
		LOGGER.info("Requested {} column name");
		return headers[col];
	}

	@Override
	public Class getColumnClass(int c) {
		LOGGER.debug("Requested column {} class", c);

		return getValueAt(0, c).getClass();
	}

	/*
	 * Implement IEnvironment interface:
	 */
	@Override
	public void add(String key, Object value) {
		this.environment.put(key, value);
		this.fireTableDataChanged();
	}

	@Override
	public void remove(String key) {
		this.environment.remove(key);
		this.fireTableDataChanged();

	}

	@Override
	public void clear() {
		this.environment.clear();
		this.fireTableDataChanged();
	}

	@Override
	public Object getVal(String key) {
		return this.environment.get(key);
	}

	@Override
	public Map<String, Object> toUnmodifiableMap() {
		return  Collections.<String, Object> unmodifiableMap(this.environment);
	}
}
