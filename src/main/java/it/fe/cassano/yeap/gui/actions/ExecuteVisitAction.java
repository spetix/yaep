package it.fe.cassano.yeap.gui.actions;

import it.fe.cassano.yeap.ast.Exp;
import it.fe.cassano.yeap.ccparser.ExpressionParser;
import it.fe.cassano.yeap.models.IEnvironment;
import it.fe.cassano.yeap.visitors.IVisitor;
import it.fe.cassano.yeap.visitors.VISITORS;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecuteVisitAction extends AbstractAction implements Action {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6131238241286881230L;
	protected final Component parentComponent;
	protected final JEditorPane editor;
	private JComboBox<String> box;
	private JEditorPane out;
	private IEnvironment env;
	private IEnvironment fun;

	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExecuteVisitAction.class);

	/**
	 * 
	 * @param parentComponent
	 *            a parent component of the one using this action
	 * @param editor
	 *            the JEditorPane to be cleared
	 */
	public ExecuteVisitAction(final Component parentComponent, final JEditorPane editor, final JComboBox<String> box, final JEditorPane out, final IEnvironment env, final IEnvironment fun) {
		super("Execute");
		this.parentComponent = parentComponent;
		this.editor = editor;
		this.box = box;
		this.out = out;
		this.env = env;
		this.fun = fun;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		LOGGER.debug("received event {}", actionEvent.getActionCommand());
		LOGGER.warn("canned visitor at to TypeVisitor at the moment!");
		int strategy = this.box.getSelectedIndex();
		IVisitor v = VISITORS.EvalVisitor.method.getInstance(this.env,this.fun);
		StringWriter wri = new StringWriter();
		final Reader parserInput = new StringReader(StringUtils.trim(this.editor.getText())+";");
		ExpressionParser p = new ExpressionParser(parserInput);
		Exp e = null;
		try {
			e = p.s();
			v.visit(e);
			
			for (final Pair<String, Object> key : v.getResults())
			{
				wri.append(key.getLeft() + " ==> " + key.getRight());
				wri.append("\n");
			}
			wri.append("" +v.getVal());
		} catch ( Exception e1) {
			LOGGER.warn("problem occurred during action");
			LOGGER.trace("see stack trace {}",e);
			wri.append(e1.getMessage());
		}
		wri.flush();
		out.setText(wri.toString());
		IOUtils.closeQuietly(wri);
		IOUtils.closeQuietly(parserInput);
		
	}

}