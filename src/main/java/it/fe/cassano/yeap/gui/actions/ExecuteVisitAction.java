package it.fe.cassano.yeap.gui.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecuteVisitAction extends AbstractAction implements Action {


	protected final Component parentComponent;
	protected final JEditorPane editor;
	private JComboBox<String> box;
	private JEditorPane out;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExecuteVisitAction.class);

	/**
	 * 
	 * @param parentComponent
	 *            a parent component of the one using this action
	 * @param editor
	 *            the JEditorPane to be cleared
	 */
	public ExecuteVisitAction(final Component parentComponent, final JEditorPane editor, final JComboBox<String> box, final JEditorPane out) {
		super("Execute");
		this.parentComponent = parentComponent;
		this.editor = editor;
		this.box = box;
		this.out = out;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		LOGGER.debug("received event {}", actionEvent.getActionCommand());
	
	}

}