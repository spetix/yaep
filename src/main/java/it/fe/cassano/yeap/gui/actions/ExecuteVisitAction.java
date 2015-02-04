package it.fe.cassano.yeap.gui.actions;

import it.fe.cassano.yeap.ast.Exp;
import it.fe.cassano.yeap.ccparser.ExpressionParser;
import it.fe.cassano.yeap.ccparser.ParseException;
import it.fe.cassano.yeap.visitors.IEnvironment;
import it.fe.cassano.yeap.visitors.IVisitor;
import it.fe.cassano.yeap.visitors.VISITORS;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecuteVisitAction extends AbstractAction implements Action {


	protected final Component parentComponent;
	protected final JEditorPane editor;
	private JComboBox<String> box;
	private JEditorPane out;
	private IEnvironment env;

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
	public ExecuteVisitAction(final Component parentComponent, final JEditorPane editor, final JComboBox<String> box, final JEditorPane out, final IEnvironment env) {
		super("Execute");
		this.parentComponent = parentComponent;
		this.editor = editor;
		this.box = box;
		this.out = out;
		this.env = env;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		LOGGER.debug("received event {}", actionEvent.getActionCommand());
		LOGGER.warn("canned visitor at to TypeVisitor at the moment!");
		int strategy = this.box.getSelectedIndex();
		IVisitor v = VISITORS.EvalVisitor.method.getInstance(this.env);
		Writer wri = new StringWriter();
		ExpressionParser p = new ExpressionParser(new StringReader(this.editor.getText()));
		Exp e = null;
		try {
			e = p.initialGoal();
			v.visit(e);
			for (final Pair<String, Object> key : v.getResults())
			{
				wri.append(key.getLeft() + " ==> " + key.getRight());
				wri.append("\n");
			}
			wri.append("" +v.getVal());
		} catch (ParseException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		out.setText(wri.toString());
		
	}

}