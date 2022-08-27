package it.fe.cassano.yeap.gui.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Select and open a file in an editor
 * 
 * @author ccassano
 *
 */
public class OpenAction extends AbstractAction implements Action {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(OpenAction.class);
	protected final Component parentComponent;
	protected final JEditorPane editor;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param parentComponent
	 *             a parent component of the one using this action
	 * @param editor
	 *             the JEditorPane to use for editing opened file
	 */
	public OpenAction(final Component parentComponent, final JEditorPane editor) {
		super("Open");
		this.parentComponent = parentComponent;
		this.editor = editor;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		LOGGER.debug("received event {}", actionEvent.getActionCommand());

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Expressions & Text Files", "expr", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(parentComponent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			LOGGER.debug("Loading file {}", chooser.getSelectedFile()
					.getAbsolutePath());
			openFileInEditor(chooser.getSelectedFile());
		} else {
			LOGGER.info("Open file action cancelled");
		}
	}

	/**
	 * Performs the open action, with the selected file
	 * 
	 * @param selectedFile
	 */
	private void openFileInEditor(File selectedFile) {
		try {
			editor.setPage(selectedFile.toURI().toURL());
		} catch (Exception e) {
			LOGGER.warn("failed loading file {} into editor",
					selectedFile.getAbsolutePath());
			LOGGER.trace("see stack trace {}", e);
		}
	}

}