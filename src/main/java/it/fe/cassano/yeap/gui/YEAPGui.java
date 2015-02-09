package it.fe.cassano.yeap.gui;

import it.fe.cassano.yeap.gui.actions.AboutAction;
import it.fe.cassano.yeap.gui.actions.ClearEnvironmentAction;
import it.fe.cassano.yeap.gui.actions.CloseAction;
import it.fe.cassano.yeap.gui.actions.ExecuteVisitAction;
import it.fe.cassano.yeap.gui.actions.NewAction;
import it.fe.cassano.yeap.gui.actions.OpenAction;
import it.fe.cassano.yeap.gui.actions.SaveAction;
import it.fe.cassano.yeap.models.MapModel;
import it.fe.cassano.yeap.visitors.VISITORS;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YEAPGui implements IExecuteHelper{

	private final static Logger LOGGER = LoggerFactory.getLogger(YEAPGui.class);
	
	private JFrame frame;
	private  JEditorPane editor;

	private  JEditorPane outputPane;

	private  JTable environment;

	private final MapModel envDataModel;

	private final MapModel envFunctionsModel;

	private JTable functions;

	private JComboBox<VISITORS> visitors;

	private JTree tree;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YEAPGui window = new YEAPGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public YEAPGui() {
		this.envDataModel = new MapModel("Var","Value");
		this.envFunctionsModel = new MapModel("Alias","Java name");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LOGGER.info("preparing GUI window");
		
		this.frame = new JFrame();
		this.editor = new JEditorPane();
		this.outputPane = new JEditorPane();
		// Var list:
		this.environment = new JTable(envDataModel);
		this.environment.setPreferredScrollableViewportSize(new Dimension(200, 300));
		// Function list:
		this.functions = new JTable(envFunctionsModel);
		this.functions.setPreferredScrollableViewportSize(new Dimension(200, 300));
		
		this.tree = new JTree();
		this.tree.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		
		
		generateWindow();
		generateWindowStructure();
		generateMenu();
		
		//this.frame.pack();
	
	}

	/**
	 * Create internal window layout
	 */
	private void generateWindowStructure() 
	{
		LOGGER.debug("setup application panels");
		try
		{
		UIManager.setLookAndFeel(
	            UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
//		JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
//		mainPanel.setOneTouchExpandable(true);
//		//mainPanel.setDividerLocation(500);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));
		
		
		
		JScrollPane editorScrollPane = new JScrollPane(editor);
		editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setMinimumSize(new Dimension(800, 400));
		
		Box commandPanel = Box.createVerticalBox();
		
		JButton executeButton = new JButton(new ExecuteVisitAction(commandPanel,this, this.envDataModel, this.envFunctionsModel));
		executeButton.setText("Process visit!");
		//executeButton.setMinimumSize(new Dimension(300,20));
		JButton clearEnvironment = new JButton( new ClearEnvironmentAction(commandPanel, envDataModel));
		clearEnvironment.setText("Clear var store");
		clearEnvironment.setMinimumSize(new Dimension(300,20));
		JButton clearFunctions = new JButton(new ClearEnvironmentAction(commandPanel, envFunctionsModel));
		clearFunctions.setText("Clear fun store");
		//clearFunctions.setMinimumSize(new Dimension(300,20));
		
		
		JPanel envPanel = new JPanel();
		envPanel.setLayout(new BorderLayout());
		envPanel.setBorder(BorderFactory.createTitledBorder("Defined variables"));
		envPanel.setMinimumSize(new Dimension(200,200));
		envPanel.add(environment);
		
		
		JPanel funPanel = new JPanel();
		funPanel.setLayout(new BorderLayout());
		funPanel.setBorder(BorderFactory.createTitledBorder("Defined Functions"));
		funPanel.setMinimumSize(new Dimension(200,200));		
		funPanel.add(functions);
		
		Box visitorBox = Box.createHorizontalBox();
		JLabel visitorLabel = new JLabel("Action: ");
		visitorBox.add(visitorLabel);
		visitorBox.add(getVisitors());
		
		commandPanel.add(visitorBox);
		commandPanel.add(executeButton);
		commandPanel.add(clearEnvironment);
		commandPanel.add(clearFunctions);		
		commandPanel.add(envPanel);
		commandPanel.add(funPanel);
		commandPanel.add(Box.createVerticalGlue());
	
		JPanel mainEditor = new JPanel();
		mainEditor.setLayout(new BorderLayout());
		mainEditor.setBorder(BorderFactory.createTitledBorder("Expression Editor"));
	    mainEditor.add(editorScrollPane);
		mainPanel.add(mainEditor);
		mainPanel.add(commandPanel);
		
		JPanel resultTextPanel = new JPanel();
		resultTextPanel.setLayout(new BorderLayout());
		resultTextPanel.setBorder(BorderFactory.createTitledBorder("Evaluation Output"));
		resultTextPanel.add(outputPane);
		
		JPanel resultTreePanel = new JPanel();
		resultTreePanel.setLayout(new BorderLayout());
		resultTreePanel.setBorder(BorderFactory.createTitledBorder("Evaluation Output"));
		resultTreePanel.add(tree);
		this.tree.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		JSplitPane resultPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,resultTextPanel,resultTreePanel);
		
//		Box app = Box.createVerticalBox();
//		app.add(mainPanel);
//		app.add(resultPanel);
		JSplitPane app = new JSplitPane(JSplitPane.VERTICAL_SPLIT,mainPanel,resultPanel);
		app.setOneTouchExpandable(true);
		app.setDividerLocation(0.5);
		//app.setDividerLocation(500);
		frame.getContentPane().add(app);
		
	}

	private void generateWindow() 
	{
		LOGGER.debug("setup main window");
		frame.setSize(1200, 700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));	
	}
	
	/** 
	 * Adds menu file and its items to frame
	 * @param frame
	 */
	private void generateMenu()
	{
		LOGGER.debug("setup menu options");
		JMenuBar mb = new JMenuBar();
		
		// Add Items to menu bar:
		JMenu mnFile = new JMenu("File");
		
		JMenuItem itemNew = new JMenuItem(new NewAction(frame,editor));
		JMenuItem itemOpen = new JMenuItem(new OpenAction(frame,editor));
		JMenuItem itemSave = new JMenuItem(new SaveAction(frame,editor));
		JMenuItem itemQuit = new JMenuItem(new CloseAction(frame));
	
		JMenu mnHelp = new JMenu("?");
		
		JMenuItem itemAbout = new JMenuItem(new AboutAction(frame));
		mnHelp.add(itemAbout);
		
		// Actions and Listeners:
		
		mnFile.add(itemNew);
		mnFile.add(itemOpen);
		mnFile.add(itemSave);
		mnFile.add(itemQuit);
		mb.add(mnFile);
		mb.add(mnHelp);
		
		frame.setJMenuBar(mb);
		
	}

	@Override
	public JEditorPane getEditor() {
		return this.editor;
	}

	@Override
	public JComboBox<VISITORS> getVisitors() {
		if (this.visitors == null)
		{
			this.visitors = new JComboBox<VISITORS>(VISITORS.values());
			
		}
		return this.visitors;
	}

	@Override
	public JEditorPane getOutput() {
		return this.outputPane;
	}

	@Override
	public JTree getTree() {
		return this.tree;
	}

}
