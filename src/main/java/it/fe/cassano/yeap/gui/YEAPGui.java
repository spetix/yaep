package it.fe.cassano.yeap.gui;

import it.fe.cassano.yeap.gui.actions.AboutAction;
import it.fe.cassano.yeap.gui.actions.ClearEnvironmentAction;
import it.fe.cassano.yeap.gui.actions.CloseAction;
import it.fe.cassano.yeap.gui.actions.EnvironmentTableModel;
import it.fe.cassano.yeap.gui.actions.ExecuteVisitAction;
import it.fe.cassano.yeap.gui.actions.NewAction;
import it.fe.cassano.yeap.gui.actions.OpenAction;
import it.fe.cassano.yeap.gui.actions.SaveAction;
import it.fe.cassano.yeap.visitors.VISITORS;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

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
import javax.swing.JTable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YEAPGui {

	private final static Logger LOGGER = LoggerFactory.getLogger(YEAPGui.class);
	
	private JFrame frame;
	private JEditorPane editor;

	private JEditorPane outputPane;

	private JTable environment;

	private EnvironmentTableModel envDataModel;
	
	

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
		this.envDataModel = new EnvironmentTableModel();
		this.environment = new JTable(envDataModel);
		this.environment.setPreferredScrollableViewportSize(new Dimension(200, 300));		
		
		generateWindow();
		generateWindowStructure();
		generateMenu();
		
		
		this.frame.pack();
	
	}

	/**
	 * Create internal window layout
	 */
	private void generateWindowStructure() 
	{
		LOGGER.debug("setup application panels");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));
		
		
		
		JScrollPane editorScrollPane = new JScrollPane(editor);
		editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setMinimumSize(new Dimension(600, 400));
		
		
		JComboBox<String> visitorType = new JComboBox<String>(VISITORS.getVisitors());
		JButton executeButton = new JButton(new ExecuteVisitAction(frame,editor,visitorType,outputPane));
		
		JButton clearEnvironment = new JButton( new ClearEnvironmentAction(frame, envDataModel));
		
		JPanel envPanel = new JPanel();
		envPanel.setLayout(new BorderLayout());
		envPanel.setBorder(BorderFactory.createTitledBorder("Environment"));
		envPanel.setMinimumSize(new Dimension(70,200));
		
		envPanel.add(environment);
		
		
		
		Box visitorBox = Box.createHorizontalBox();
		JLabel visitorLabel = new JLabel("Action: ");
		visitorBox.add(visitorLabel);
		visitorBox.add(visitorType);
		Box commandPanel = Box.createVerticalBox();
		commandPanel.add(visitorBox);
		commandPanel.add(executeButton);
		commandPanel.add(clearEnvironment);
		commandPanel.add(envPanel);
		commandPanel.add(Box.createVerticalGlue());
		
		JPanel mainEditor = new JPanel();
		mainEditor.setLayout(new BorderLayout());
		mainEditor.setBorder(BorderFactory.createTitledBorder("Expression Editor"));
	    mainEditor.add(editorScrollPane);
		
		mainPanel.add(mainEditor);
		mainPanel.add(commandPanel);
		
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new BorderLayout());
		resultPanel.setBorder(BorderFactory.createTitledBorder("Evaluation Output"));
		resultPanel.add(outputPane);
		
		Box app = Box.createVerticalBox();
		app.add(mainPanel);
		app.add(resultPanel);
		frame.getContentPane().add(app);
		
	}

	private void generateWindow() 
	{
		LOGGER.debug("setup main window");
		frame.setBounds(100, 100, 900, 600);
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

}
