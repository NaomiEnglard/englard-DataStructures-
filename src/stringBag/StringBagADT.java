package stringBag;

import java.util.Random;

public class StringBagADT implements StringBagInterface1 {
	private String[] wordList;
	//use an array not an array list, since you want the bag to have a limmited size
	//arrayList will auto update size while array will only allow the number of string u let
	private int size; // how much is filled up

	// constructor
	StringBagADT(int length) {
		wordList = new String[length];
		size = -1;
	}

	// insert
	public void insert(String insertString) {
		// increment size and place
		if (!isFull()) {
			wordList[++size] = insertString;
		}
	}

	// clear
	public void clear() {
		for (int i = 0; i < size; i++) {
			wordList[i] = null;
		}
		size = -1;
	}

	// toString
	public String toString() {
		StringBuilder build = new StringBuilder();
		for (int i = 0; i <= size; i++) { 
			build.append(i + 1);
			build.append(". ");
			build.append(wordList[i]);
		}
		return build.toString();

	}

	// isFull
	public boolean isFull() {
		
		return  ((size+1) == wordList.length);
		
	}

	// is empty
	public boolean isEmpty() {
		return (size == -1);
	}

	// remove random string if not empty
	// select random string, remove it, return the string
	public String remove() throws EmptyBagException {
		Random rand = new Random();
		String remove = null;
		if(size == -1){
			throw new EmptyBagException();
		}
		if(size ==0){
			//then u cannot use random instead delete last word
			///so skip next few steps
			remove = wordList[0];
		}else{
		int choice = rand.nextInt(size); //select an int from 0 to the size of the list
		remove =  wordList[choice];
		//since words r not in order bring last word to fill in for this word and set last word
		//to null, decrement size
		wordList[choice] =wordList[size];
		}
		wordList[size] = null;
		size--;
		return remove;
	}

}
