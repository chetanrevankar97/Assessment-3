import java.util.*;

public class invalidException extends Exception{
	String message;

	public invalidException(String message) {
		super(message);
		this.message = message;
	}
	
	
	
}
