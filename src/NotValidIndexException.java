
public class NotValidIndexException extends Exception{
	private String message = "Please input a valid index";
	public String getMessage() {
		return this.message;
	}
}
