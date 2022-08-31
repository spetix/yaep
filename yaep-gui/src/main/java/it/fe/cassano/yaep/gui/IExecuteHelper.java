package it.fe.cassano.yaep.gui;

import it.fe.cassano.yaep.visitors.VISITORS;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JTree;

public interface IExecuteHelper {

	JEditorPane getEditor();

	JComboBox<VISITORS> getVisitors();

	JEditorPane getOutput();

	JTree getTree();

}
