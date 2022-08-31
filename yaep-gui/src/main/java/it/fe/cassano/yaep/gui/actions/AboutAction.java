package it.fe.cassano.yaep.gui.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

public class AboutAction extends AbstractAction implements Action {
	protected Component parentComponent;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AboutAction(final Component parentComponent) {
		super("About");
		this.parentComponent = parentComponent;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		System.out.println(actionEvent.getActionCommand());
		JOptionPane.showMessageDialog(parentComponent, "Credits: thanks to all..", "yaep!",
				JOptionPane.INFORMATION_MESSAGE);
	}


}
