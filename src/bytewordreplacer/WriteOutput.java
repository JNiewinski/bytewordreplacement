package bytewordreplacer;

/** Interface to make possible writting to JTextArea without refference to this area. Security reason
 * 
 * @author komp
 *
 */
public interface WriteOutput {
	void writeToOutput(String text);
}
