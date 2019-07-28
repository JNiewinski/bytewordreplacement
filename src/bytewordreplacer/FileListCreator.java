package bytewordreplacer;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;


/**	Wrapper class for using FileUtils from Apache Commons
 * @author MKedzie
 *
 */
public class FileListCreator {
	
	protected File directoryPath;
	
	protected Collection<File> foundFiles;
		
	
	/**	
	 * @param directoryPath this parameter must be a link to directory; directoryPath.isDirectory() should return true
	 * @param fileExt Array of strings of file extensions wanted to be listed
	 */
	public FileListCreator(File directoryPath, String[] fileExt)
	{
		this.directoryPath = directoryPath;
		this.foundFiles = FileUtils.listFiles(directoryPath, fileExt,true);
	}
	
}
