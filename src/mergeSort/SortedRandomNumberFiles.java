package mergeSort;

/*
 * create a list of random numbers
 * ask user for size of list and highest number
 * store list in a file  ** the first value in the file is the SIZE f array not a reg number
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import pharmacy.InvalidDataException;

public class SortedRandomNumberFiles {

	public static void main(String args[]) {
		// create menu ?
		// step 1
		try {
			Scanner in = new Scanner(System.in);
			System.out.print("how many numbers would you like to genrerate?(at least 100): ");
			int amount = in.nextInt();
			System.out
					.print("what is the highest number you like to genrerate?: ");
			int highestValue = in.nextInt();
			in.nextLine();// flush
			RandomGenerator rand = new RandomGenerator(amount, highestValue);
			rand.writeToFile("RandomNumList.txt");
			System.out.println("Files will be saved to RandomNumList.txt ");
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// step 2
		SortFile makeFiles = new SortFile("RandomNumList.txt");
		try {
			makeFiles.getSortedFileName();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
