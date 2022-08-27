package it.fe.cassano.yeap.gui;

import it.fe.cassano.yeap.visitors.VISITORS;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JTree;

public interface IExecuteHelper {

	JEditorPane getEditor();

	JComboBox<VISITORS> getVisitors();

	JEditorPane getOutput();

	JTree getTree();

}
