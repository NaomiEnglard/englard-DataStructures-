package stringBag;

import java.util.Scanner;

public class TestDriver {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int numString;
		System.out.println("how many string should your bag hold?");
		if (input.hasNextInt()) {
			numString = input.nextInt();
		} else {
			System.out.println("error: you must enter an int");
			System.out.println("terminating test");
			return;
		}
		StringBagADT test = new StringBagADT(numString);
		input.nextLine(); // flush buffer
		int choice;
		do {
			System.out.println("choose one of the following menu options:");
			System.out
					.println("1. add a string\n2.clear bag\n3.remove a string\n4.display all string\n5.is the bag full\n6. is the bag empty");
			if (input.hasNextInt()) {
				choice = input.nextInt();
				input.nextLine(); // aflush buffer
			} else {
				System.out.println("error: you must enter an int");
				System.out.println("terminating test");
				return;
			}
			switch (choice) {
			case 1:
				System.out.println("write a string to add");
				String insertString = input.nextLine();
				test.insert(insertString);
				break;
			case 2:
				test.clear();
				break;
			case 3:

				try {
					System.out.println(test.remove());
				} catch (EmptyBagException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println(test.toString());
				break;
			case 5:
				System.out.println(test.isEmpty());
				break;
			case 6:
				System.out.println(test.isFull());
				break;
			}
		} while (choice > 0 && choice < 7);

		input.close();

	}
}
