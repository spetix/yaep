package it.fe.cassano.yeap.gui.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clear editor window
 * 
 * @author ccassano
 *
 */
public class NewAction extends AbstractAction implements Action {
	protected final Component parentComponent;
	protected final JEditorPane editor;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(NewAction.class);

	/**
	 * 
	 * @param parentComponent
	 *            a parent component of the one using this action
	 * @param editor
	 *            the JEditorPane to be cleared
	 */
	public NewAction(final Component parentComponent, final JEditorPane editor) {
		super("New");
		this.parentComponent = parentComponent;
		this.editor = editor;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		LOGGER.debug("received event {}", actionEvent.getActionCommand());

		int reply = JOptionPane.showConfirmDialog(parentComponent,
				"Do you want to clear the editor?", "Yes",
				JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.OK_OPTION) {
			editor.setText("");
			LOGGER.info("Editor cleared");
		} else {
			LOGGER.info("New file action cancelled");
		}

	}

}