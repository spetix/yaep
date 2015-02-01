package it.fe.cassano.yeap.gui.actions;

import it.fe.cassano.yeap.visitors.IEnvironment;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.apache.commons.collections4.map.LinkedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnvironmentTableModel extends AbstractTableModel implements
		TableModel, IEnvironment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 543996928565940083L;
	private final static Logger LOGGER = LoggerFactory
			.getLogger(EnvironmentTableModel.class);

	private final String[] headers = { "Var", "Value" };
	private final LinkedMap<String, Object> environment;

	public EnvironmentTableModel() {
		super();

		LOGGER.info("Initializing environment");
		this.environment = new LinkedMap<String, Object>();
		this.environment.put("A", 33);
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
		LOGGER.info("cell {} {} editable information requested", row, col);
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
}
