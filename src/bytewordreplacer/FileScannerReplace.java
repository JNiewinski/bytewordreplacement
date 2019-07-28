package bytewordreplacer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

import static java.nio.file.StandardOpenOption.WRITE;
import static java.nio.file.StandardOpenOption.READ;

/** Class responsible for traversing file and replacing correct byte series
 * @author MKedzie
 *
 */

public class FileScannerReplace {
	/** File to be changed. Without 2nd verification of extension
	 * 
	 */
	File fileToChange;
	/** ByteBuffer with byte series to remove
	 * 
	 */
	ByteBuffer toRemove;
	/** ByteByffer with byte series to replace with 
	 * 
	 */
	ByteBuffer toAdd;
	/** 1 byte long ByteBuffer for reading file byte by byte
	 * 
	 */
	ByteBuffer readByte;
	/** Counter of replaced series
	 * 
	 */
	int amountReplaced;
	
	/** Class responsible for traversing file and replacing correct byte series
	 * @param toRemove
	 * @param toAdd
	 * @param file
	 */
	public FileScannerReplace(byte[] toRemove,	byte[] toAdd, File file) {
		
		// copying ByteBuffers into array, easier comparing of values
		this.toRemove = ByteBuffer.allocate(toRemove.length);
		for ( int i = 0; i<toRemove.length;i++)
		{
			this.toRemove.put(i, toRemove[i]);
		}
		this.toAdd = ByteBuffer.allocate(toAdd.length);
		for ( int i = 0; i<toAdd.length;i++)
		{
			this.toAdd.put(i, toAdd[i]);
		}
		
		this.fileToChange = file;
		this.readByte = ByteBuffer.allocate(1);
		//creation of temporary file, to which data is rewriten
		File tempFile = new File(file.toString().concat("_temp"));
		//store original filename in variable
		Path temp = (tempFile.toPath());
		
		ByteBuffer toRemoveFirst = ByteBuffer.allocate(1).put(toRemove[0]);
		
		byte[] readByteArray = readByte.array();
		
		this.amountReplaced = 0;
		
		try (
				// opening FileChannels to both temp and normal files
				FileChannel readStream = FileChannel.open(file.toPath(),StandardOpenOption.READ);
				FileChannel outStream = FileChannel.open(temp, new OpenOption[]{ StandardOpenOption.WRITE , StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING });
			)
		{
			while (readStream.read(this.readByte) != -1)  // read file until the end
			{
				int lastWrite = 0;
				if (this.readByte.get(0) == toRemove[0]) // if readed byte was first in series to remove
				{
						long mark = readStream.position(); // mark position in case there is no right byte series
						if(testSeries(readStream,readByte,toRemove))  // test if series is correct
							{
								lastWrite = outStream.write(this.toAdd.duplicate()); // yes, then write duplicate of ByteBuffer toAdd to file
								this.amountReplaced++;
								this.readByte.clear();
								continue;
							}
						else 
							{
								readStream.position(mark); // go to marked position
								continue; 
							}
				}
				
				this.readByte.position(0); // reset position in bytebuffer for reading
				lastWrite = outStream.write(this.readByte.duplicate()); // write next byte from main file to temp file
				this.readByte.clear(); // clear bytebuffer
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// delete main file, rename temp file to main file name
		Path fileName = file.toPath();
		file.delete();
		tempFile.renameTo(fileName.toFile());
		
	}
	
	
	/** check if there is right series of byte ahead of current position of file channel 
	 * @param readStream
	 * @param readByte
	 * @param toRemove
	 * @return
	 * @throws IOException
	 */
	boolean testSeries(FileChannel readStream, ByteBuffer readByte, byte[] toRemove) throws IOException
	{
		readByte.clear();
		for (int i = 1; i<toRemove.length;i++)
		{
			if ((readStream.read(readByte) == -1)||(toRemove[i] != readByte.get(0))) return false;
			readByte.clear();
			
		}
		return true;
	}
}
