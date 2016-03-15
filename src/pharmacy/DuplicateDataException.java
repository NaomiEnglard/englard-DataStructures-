package pharmacy;

public class DuplicateDataException extends Exception {
	public DuplicateDataException(){
		super("Data already in list");
	}
}
