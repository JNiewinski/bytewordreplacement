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

public class FileScannerReplace {
	File fileToChange;
	ByteBuffer toRemove;
	ByteBuffer toAdd;
	ByteBuffer readByte;
	int amountReplaced;
	
	public FileScannerReplace(byte[] toRemove,	byte[] toAdd, File file) {
		
	
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
		File tempFile = new File(file.toString().concat("_temp"));
		Path temp = (tempFile.toPath());
		
		ByteBuffer toRemoveFirst = ByteBuffer.allocate(1).put(toRemove[0]);
		byte[] readByteArray = readByte.array();
		
		this.amountReplaced = 0;
		
		try(
				FileChannel readStream = FileChannel.open(file.toPath(),StandardOpenOption.READ);
				FileChannel outStream = FileChannel.open(temp, new OpenOption[]{ StandardOpenOption.WRITE , StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING });
				
				)
		{
			while (readStream.read(this.readByte) != -1)
			{
				int lastWrite = 0;
				if (this.readByte.get(0) == toRemove[0])
				{
						long mark = readStream.position();
						if(testSeries(readStream,readByte,toRemove)) 
							{
								lastWrite = outStream.write(this.toAdd.duplicate());
								this.amountReplaced++;
								this.readByte.clear();
								continue;
							}
						else 
							{
								readStream.position(mark);
								readStream.read(this.readByte);
							}
				}
				this.readByte.position(0);
				lastWrite = outStream.write(this.readByte.duplicate());
				this.readByte.clear();
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Path fileName = file.toPath();
		file.delete();
		tempFile.renameTo(fileName.toFile());
		
	}
	
	
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
