package it.fe.cassano.yeap.gui.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Save editor contents to file action
 * 
 * @author ccassano
 *
 */
public class SaveAction extends AbstractAction implements Action {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(SaveAction.class);
	protected final Component parentComponent;
	protected final JEditorPane editor;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param parentComponent
	 *            a parent component of the one using this action
	 * @param editor
	 *            the JEditorPane to be saved
	 */
	public SaveAction(final Component parentComponent, final JEditorPane editor) {
		super("Save");
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
		int returnVal = chooser.showSaveDialog(parentComponent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			LOGGER.debug("Saving editor to {}", chooser.getSelectedFile()
					.getAbsolutePath());
			saveEditorTo(chooser.getSelectedFile());
		} else {
			LOGGER.info("Save action cancelled");
		}
	}

	/**
	 * performs the save action on selected file
	 * 
	 * @param selectedFile
	 */
	private void saveEditorTo(File selectedFile) {
		try {
			final FileWriter out = new FileWriter(selectedFile);
			out.write(editor.getText());
			out.flush();
			IOUtils.closeQuietly(out);
			LOGGER.info("saved {}", selectedFile.getAbsolutePath());
		} catch (Exception e) {
			LOGGER.warn("save action on {} failed",
					selectedFile.getAbsolutePath());
			LOGGER.trace("See stack trace {}", e);
		}
	}

}