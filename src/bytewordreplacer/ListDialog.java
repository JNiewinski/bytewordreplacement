package bytewordreplacer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;



public class ListDialog{
	protected JDialog dialog;
	protected JList<Integer> list;
	protected DefaultListModel<Integer> listModel;
	
	
	public ListDialog(){
		this.listModel = new DefaultListModel<Integer>();
		common();
	}
	
	public ListDialog(DefaultListModel<Integer> rootList) {
		try {
			
			this.listModel = new DefaultListModel<Integer>();
			listIteration(rootList, listModel);
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
		common();
		
	}
	
	private void common() {
		dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setSize(400, 400);
		
		JPanel panel = new JPanel();
		
		JButton replaceButton = new JButton("Replace");
				
		JButton addButton = new JButton("Add");
				
		JButton removeButton = new JButton("Remove");
		
		JSpinner valueSpinner = new JSpinner();
		valueSpinner.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		
		JButton btnNewButton = new JButton("End");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Range 0 - 255");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addComponent(removeButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
						.addComponent(addButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
						.addComponent(replaceButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
						.addComponent(valueSpinner, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(replaceButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(addButton, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(removeButton, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addGap(7)
					.addComponent(valueSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(118)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{300, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JList<Integer> listJList = new JList<Integer>(listModel);
		listJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_listJList = new GridBagConstraints();
		gbc_listJList.fill = GridBagConstraints.BOTH;
		gbc_listJList.gridx = 0;
		gbc_listJList.gridy = 0;
		panel_1.add(listJList, gbc_listJList);
		GroupLayout groupLayout = new GroupLayout(dialog.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		dialog.getContentPane().setLayout(groupLayout);
		dialog.setVisible(true);	

		replaceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listJList.getSelectedIndex();
				int value = (int) valueSpinner.getValue();
				Integer toAdd = new Integer(value);
				if ( index == -1 ) ;
				else listModel.setElementAt(toAdd, index);			
			}
		});
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listJList.getSelectedIndex();
				int value = (int) valueSpinner.getValue();
				Integer toAdd = new Integer(value);
				if ( index == -1 ) listModel.addElement(toAdd);
				else listModel.add(index, toAdd);
			}
		});
		
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listJList.getSelectedIndex();
				if ( index == -1 ) ;
				else listModel.remove(index);
			}
		});



		
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ListDialog window = new ListDialog();
					window.dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void listIteration(DefaultListModel<Integer> source, DefaultListModel<Integer> destination)
	{
		int i;
		if (source.getSize() > 0) for ( i = 0; i<source.size();i++ )destination.addElement(source.get(i));
	}

}