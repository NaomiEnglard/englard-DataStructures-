package bill;

public class ListEmptyException extends Exception {
	public ListEmptyException() {
		super("empty list");
	}
}
