package it.fe.cassano.yeap.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Close the application
 * @author ccassano
 *
 */
public class CloseAction extends AbstractAction implements Action {
	private final static Logger LOGGER = LoggerFactory.getLogger(CloseAction.class);
	protected JFrame parentComponent;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CloseAction(final JFrame parentComponent) {
		super("Quit");
		this.parentComponent = parentComponent;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		LOGGER.debug("received event {}", actionEvent.getActionCommand());
		
		this.parentComponent.dispose();
	}


}