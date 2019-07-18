package bytewordreplacer;

import java.io.File;

import javax.swing.DefaultListModel;

public class LogicLayer {
	protected UserInterface GUI;
	private FileListCreator FLC;
	byte[] toRemoveByte;
	byte[] toAddByte;
		
	public LogicLayer(File rootPath,DefaultListModel<Integer> addListModel,DefaultListModel<Integer> removeListModel, String[] extensions, WriteOutput interfaceWrite) {
		
		toRemoveByte = new byte[removeListModel.size()];
		toAddByte = new byte[addListModel.size()];
		
		int i;
		for(i=0;i<addListModel.size();i++)
		{
			toAddByte[i] = addListModel.elementAt(i).byteValue();
		}
		
		for(i=0;i<removeListModel.size();i++)
		{
			toRemoveByte[i] = removeListModel.elementAt(i).byteValue();
		}
		
		
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
		this.FLC = new FileListCreator(rootPath, extensions);
		
		for(File file : this.FLC.foundFiles)
		{
			
			interfaceWrite.writeToOutput(file.toString());
			FileScannerReplace fileScan = new FileScannerReplace(toRemoveByte, toAddByte, file);
			interfaceWrite.writeToOutput(" Zmiana: ".concat(Integer.toString(fileScan.amountReplaced)).concat(" \n"));
		}
	}
	
}
