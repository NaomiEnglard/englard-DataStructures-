package stringBag;

public interface StringBagInterface1 {
	

		
			 //insert
			void insert(String insertString);
			//clear
			void clear();
			//toString
			String toString();
			//isFull
			boolean isFull();
			//is empty
			boolean isEmpty();
			//remove random string if not empty
				//select random string, remove it, return the string
			String remove() throws EmptyBagException;


}
