package bytewordreplacer;

import java.io.File;

import javax.swing.DefaultListModel;
/**
 * Class responsible for finding files to change and calling class responsible for this for each file
 * @author MKedzie
 *
 */
public class LogicLayer {

	protected UserInterface GUI;

	private FileListCreator FLC;

	byte[] toRemoveByte;
	
	byte[] toAddByte;
		
	/** Constructor. 
	 * @param rootPath path of folder 
	 * @param addListModel byte series to replace with
	 * @param removeListModel byte series to replace 
	 * @param extensions list of extensions in array form
	 * @param interfaceWrite output write class
	 */
	public LogicLayer(File rootPath,DefaultListModel<Integer> addListModel,DefaultListModel<Integer> removeListModel, String[] extensions, WriteOutput interfaceWrite) {
		// construction of byte arrays with both byte series
		toRemoveByte = new byte[removeListModel.size()];
		toAddByte = new byte[addListModel.size()];
		// filling byte arrays
		int i;		
		for(i=0;i<addListModel.size();i++)
		{
			toAddByte[i] = addListModel.elementAt(i).byteValue();
		}
		
		for(i=0;i<removeListModel.size();i++)
		{
			toRemoveByte[i] = removeListModel.elementAt(i).byteValue();
		}
		
		// some output at beginning of file
		interfaceWrite.writeToOutput("Sciezka folderu: ".concat(rootPath.getAbsolutePath().concat("\n")));
		interfaceWrite.writeToOutput("Zamiana bajtow: ");
		for (byte b : toRemoveByte)
		{
			interfaceWrite.writeToOutput(Integer.toString(((int)b+256)%256).concat(" "));
		}
		interfaceWrite.writeToOutput("\nNa: ");
		for (byte b : toAddByte)
		{
			interfaceWrite.writeToOutput(Integer.toString(((int)b+256)%256).concat(" "));
		}
		interfaceWrite.writeToOutput("\nObslugiwane rozszerzenia:");
		for (String s : extensions)
		{
			interfaceWrite.writeToOutput(s.concat(" "));
		}
		interfaceWrite.writeToOutput("\n");
		
		// creation of list of files to change
		this.FLC = new FileListCreator(rootPath, extensions);
		
		// for each file
		for(File file : this.FLC.foundFiles)
		{
			
			interfaceWrite.writeToOutput(file.toString()); // write file name
			FileScannerReplace fileScan = new FileScannerReplace(toRemoveByte, toAddByte, file); // do work
			interfaceWrite.writeToOutput(" Zmiana: ".concat(Integer.toString(fileScan.amountReplaced)).concat(" \n")); // print how many series replaced
		}
	}
	
}
