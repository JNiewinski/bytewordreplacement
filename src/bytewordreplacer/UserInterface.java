package bytewordreplacer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JToolBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.Box;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import javax.swing.ListSelectionModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollBar;
import java.awt.Scrollbar;
import java.awt.ScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

public class UserInterface{
	
	protected WriteOutput writeOutputAppend;
	protected JFrame frmByteWordReplacer;
	private JTextField pathTextField;
	protected File rootPath;
	protected DefaultListModel<Integer> addListModel;
	protected DefaultListModel<Integer> removeListModel; 
	private JTextField extensionTextField;
	protected String outputText;
	protected JTextArea outputTextArea;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frmByteWordReplacer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		addListModel = new DefaultListModel<Integer>();
		removeListModel = new DefaultListModel<Integer>();
		
		frmByteWordReplacer = new JFrame();
		frmByteWordReplacer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmByteWordReplacer.setTitle("Byte word replacer");
		frmByteWordReplacer.setResizable(false);
		frmByteWordReplacer.setSize(600, 600);
		frmByteWordReplacer.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmByteWordReplacer.getContentPane().add(panel, BorderLayout.NORTH);
		
		pathTextField = new JTextField();
		pathTextField.setColumns(10);
		
		
		JButton pathButton = new JButton("Folder");
		pathButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showfilechooser();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(pathTextField, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pathButton, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(pathTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pathButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		frmByteWordReplacer.getContentPane().add(panel_1, BorderLayout.WEST);
		
		
		JList<Integer> removeList = new JList<Integer>(removeListModel);
		removeList.setBackground(new Color(240, 255, 240));
		removeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openList(removeListModel);
			}
		});
		
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(removeButton, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(10)
					.addComponent(removeList, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(10))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(removeButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(removeList, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JScrollPane removeScrollPane = new JScrollPane();
		removeScrollPane.setViewportView(removeList);
		removeList.setLayoutOrientation(JList.VERTICAL);
		panel_1.add(removeScrollPane);
		
		JPanel panel_2 = new JPanel();
		frmByteWordReplacer.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JButton startButton = new JButton("Start");
		
		JLabel lblSelectExtension = new JLabel("Write file extension");
		lblSelectExtension.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
					frmByteWordReplacer.dispose();
				}				
			}
		);
		
		extensionTextField = new JTextField();
		extensionTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		extensionTextField.setColumns(10);
		
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSelectExtension)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(extensionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
					.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addComponent(cancelButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
				.addComponent(startButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectExtension)
						.addComponent(extensionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		frmByteWordReplacer.getContentPane().add(panel_3, BorderLayout.EAST);
		
		
		
		
		JList<Integer> addList = new JList<Integer>(addListModel);
		addList.setBackground(new Color(240, 255, 240));
		addList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openList(addListModel);
			}
		});
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(addButton, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(10)
					.addComponent(addList, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(10))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(addButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(addList, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JScrollPane addScrollPane = new JScrollPane();
		addScrollPane.setViewportView(addList);
		addList.setLayoutOrientation(JList.VERTICAL);
		panel_3.add(addScrollPane);
		
		
		JPanel panel_4 = new JPanel();
		frmByteWordReplacer.getContentPane().add(panel_4, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
		);
		
		JTextArea outputTextArea = new JTextArea();
		outputTextArea.setEditable(false);
		scrollPane.setViewportView(outputTextArea);
		
		panel_4.setLayout(gl_panel_4);
		
		writeOutputAppend = new WriteOutput() {
			
			@Override
			public void writeToOutput(String text) {
				 outputTextArea.append(text);
			}
		};
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outputTextArea.setText("");
				rootPath = new File(pathTextField.getText());
				LogicLayer program = new LogicLayer(rootPath, addListModel, removeListModel, getExtensionsCorrection(),writeOutputAppend);			
			}
			
		});
		
		
	}
	
	private void openList(DefaultListModel<Integer> list) {
		frmByteWordReplacer.setEnabled(false);
		ListDialog newDialog = new ListDialog(list);
		newDialog.dialog.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e)
			  {
				frmByteWordReplacer.setEnabled(true);
				frmByteWordReplacer.toFront();
				list.clear();
				listIteration(newDialog.listModel, list);
			    System.out.println("WindowClosed");
			    frmByteWordReplacer.toFront();
			  }
			public void windowClosing(WindowEvent e)
			{
				frmByteWordReplacer.setEnabled(true);
				frmByteWordReplacer.toFront();
				list.clear();
				listIteration(newDialog.listModel, list);
			    System.out.println("WindowClosing");
			    frmByteWordReplacer.toFront();
			}
		});
	}
	
	private void showfilechooser() {
		frmByteWordReplacer.setEnabled(false);
		String filePath = pathTextField.getText();
		File fileTemp = new File(filePath);
		FolderSelectWindow folderSelectionWindow = new FolderSelectWindow(fileTemp);
		folderSelectionWindow.dialog.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e)
			  {
				frmByteWordReplacer.setEnabled(true);
				frmByteWordReplacer.toFront();
			    System.out.println("jdialog window closed event received");
			    frmByteWordReplacer.setAlwaysOnTop(true);
			    frmByteWordReplacer.setAlwaysOnTop(false);
			    rootPath = folderSelectionWindow.getFile();
			    pathTextField.setText(rootPath.toString());
			    frmByteWordReplacer.toFront();
			  }

			  public void windowClosing(WindowEvent e)
			  {
				frmByteWordReplacer.setEnabled(true);
				frmByteWordReplacer.toFront();
			    System.out.println("jdialog window closing event received");
			    frmByteWordReplacer.setAlwaysOnTop(true);
			    frmByteWordReplacer.setAlwaysOnTop(false);
			    frmByteWordReplacer.toFront();
			  }
		});
		
		
	}
	
	private void listIteration(DefaultListModel<Integer> source, DefaultListModel<Integer> destination)
	{
		int i;
		for ( i = 0; i<source.getSize();i++ )destination.addElement(source.get(i));
	}
	
	private String[] getExtensionsCorrection()
	{
		return extensionTextField.getText().split("(?=[,.])|\\s+");
		
	}
}
