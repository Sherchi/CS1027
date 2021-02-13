
/**
 * @author Darwin
 *Empty Stack Exception
 * gives the error msg of when the stack is empty
 * 
 */
public class EmptyStackException extends RuntimeException{
	
	public EmptyStackException(String Message) {
		super("The " + Message + " Is Empty");
	}
	
}
