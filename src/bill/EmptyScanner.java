package bill;

public class EmptyScanner extends Exception {
	public EmptyScanner(){
		super("Scanner has nothing left to read");
	}
}
