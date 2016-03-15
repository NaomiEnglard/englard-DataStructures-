package stringBag;

public class EmptyBagException extends Exception {
	public EmptyBagException(){
		super("the bag is empty nothing van be removed");
	}
}
