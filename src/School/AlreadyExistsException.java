package School;

public class AlreadyExistsException extends Exception {
public AlreadyExistsException(){
	super("item in list already, may not insert duplicate");
}
}
