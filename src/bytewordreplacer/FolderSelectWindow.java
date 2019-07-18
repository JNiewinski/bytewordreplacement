package bytewordreplacer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;



public class FolderSelectWindow{
	protected JDialog dialog;
	protected File file;
	private JFileChooser fileChooser;
	public FolderSelectWindow() {
		common();
	}
	public FolderSelectWindow(File startfile) {
		common();
		if(startfile.exists()) {
			fileChooser.setCurrentDirectory(startfile);
		}
	}
	
	private void common() {
		
		dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setSize(400, 400);
		file = null;
		fileChooser = new JFileChooser() {
			@Override
			public void approveSelection()
			{
				file = this.getSelectedFile();
				System.out.print(file.toString());
				dialog.dispose();
			}
			@Override
			public void cancelSelection() {
				System.out.print("Wyjscie");
				dialog.dispose();
			}
			};
			
			
		// TODO uzupelnic
		// fileChooser.setFileFilter(filter); 
		fileChooser.setFileSelectionMode(fileChooser.DIRECTORIES_ONLY);
		dialog.getContentPane().add(fileChooser, BorderLayout.CENTER);
		dialog.setVisible(true);
	}
	
	public File getFile() {
		return this.file;
	}

	
}