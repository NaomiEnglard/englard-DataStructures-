package mergeSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import pharmacy.InvalidDataException;

public class RandomGenerator {

	private int highestNumber;
	private int[] randomList;

	public RandomGenerator(int listSize, int highestNumber) throws InvalidDataException {
		if(listSize < 100){
			throw new InvalidDataException();
		}
		randomList = new int[listSize];
		this.highestNumber = highestNumber;
		generateList();
	}

	public int[] getList() {

		return randomList;
	}

	private void generateList() {
		Random rand = new Random();
		for (int i = 0; i < randomList.length; i++) {
			randomList[i] = rand.nextInt(this.highestNumber) + 1;
		}

	}

	public void writeToFile(String fileName) throws FileNotFoundException {
		PrintWriter writeOut = new PrintWriter(new File(fileName));
		writeOut.printf("%d ", this.randomList.length);// first value is size of
														// array
		for (int i = 0; i < randomList.length; i++) {
			writeOut.printf("%d ", randomList[i]);// there is a space btw each
													// number
		}
		writeOut.close();
	}

}
