package bytewordreplacer;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;


public class FileListCreator {
	protected File directoryPath;
	protected Collection<File> foundFiles;
		
	
	public FileListCreator(File directoryPath, String[] fileExt)
	{
		this.directoryPath = directoryPath;
		this.foundFiles = FileUtils.listFiles(directoryPath, fileExt,true);
	}
	
}
