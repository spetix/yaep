package it.fe.cassano.yeap.gui.actions;

import it.fe.cassano.yeap.models.IEnvironment;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClearEnvironmentAction extends AbstractAction implements Action {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -986607530524718878L;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ClearEnvironmentAction.class);
	private final Component parentComponent;

	private IEnvironment dataModel;

	public ClearEnvironmentAction(final Component parentComponent, final IEnvironment dataModel) {
		super("Clear");
		this.parentComponent = parentComponent;
		this.dataModel = dataModel;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		LOGGER.debug("received event {}", actionEvent.getActionCommand());
		this.dataModel.clear();
	}


}
