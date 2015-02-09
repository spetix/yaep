package it.fe.cassano.yeap.gui.actions;

import it.fe.cassano.yeap.ast.Exp;
import it.fe.cassano.yeap.ccparser.ExpressionParser;
import it.fe.cassano.yeap.gui.IExecuteHelper;
import it.fe.cassano.yeap.models.IEnvironment;
import it.fe.cassano.yeap.visitors.IVisitor;
import it.fe.cassano.yeap.visitors.VISITORS;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

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
	private JComboBox<VISITORS> box;
	private JEditorPane out;
	private IEnvironment env;
	private IEnvironment fun;
	private JTree tree;

	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExecuteVisitAction.class);

	/**
	 * 
	 * @param parentComponent
	 *            a parent component of the one using this action
	 * @param editor
	 *            the JEditorPane to be cleared
	 */
	public ExecuteVisitAction(final Component parentComponent, final IExecuteHelper mainFrame, final IEnvironment env, final IEnvironment fun) {
		super("Execute");
		this.parentComponent = parentComponent;
		this.editor = mainFrame.getEditor();
		this.box = mainFrame.getVisitors();
		this.out = mainFrame.getOutput();
		this.tree = mainFrame.getTree();
		this.env = env;
		this.fun = fun;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		LOGGER.debug("received event {}", actionEvent.getActionCommand());
		// IVisitor v = VISITORS.EvalVisitor.method.getInstance(this.env,this.fun);
		// IVisitor v = VISITORS.LispVisitor.method.getInstance(this.env, this.fun);
		
		VISITORS strategy = (VISITORS) this.box.getSelectedItem();
		IVisitor visitorInstance = strategy.method.getInstance(this.env,this.fun);
		StringWriter wri = new StringWriter();
		final Reader parserInput = new StringReader(StringUtils.trim(this.editor.getText())+";");
		ExpressionParser parserInstance = new ExpressionParser(parserInput);
		Exp ast = null;
		try {
			ast = parserInstance.s(); // parse input and generate AST
			visitorInstance.visit(ast); // Visit AST
			// Produce results:
			if (visitorInstance.getResults().size()>0)
			{ // Evaluate:
				for (final Pair<String, Object> key : visitorInstance.getResults())
				{
					wri.append(key.getLeft() + " ==> " + key.getRight());
					wri.append("\n");
				}
			}
			else if (visitorInstance.getVal() instanceof DefaultMutableTreeNode)
			{ // Show tree:
				this.tree.setModel(new DefaultTreeModel((TreeNode) visitorInstance.getVal()));
				this.tree.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				this.tree.repaint();
			} else 
			{ // one expression or lisp visitor
				wri.append("" +visitorInstance.getVal());
			}
		} catch ( Exception e) {
			LOGGER.warn("problem occurred during action");
			LOGGER.trace("see stack trace {}",e);
			wri.append(e.getMessage());
		}
		wri.flush();
		out.setText(wri.toString());
		IOUtils.closeQuietly(wri);
		IOUtils.closeQuietly(parserInput);
		
	}

}