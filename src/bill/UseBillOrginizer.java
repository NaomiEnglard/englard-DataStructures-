package bill;

import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

import School.NotFoundException;
import pharmacy.DuplicateDataException;

public class UseBillOrginizer {
	public static void main(String args[]) {

		Scanner keyboard = new Scanner(System.in);
		BillOrganizer bills = new BillOrganizer();
		int menuChoice;
		do {
			menuChoice = menu();
			switch (menuChoice) {
			case 1:
				System.out.print("Where did you spend money? ");
				String vendorName = keyboard.nextLine();
				System.out
						.print("What category did u spend money on (choose a number)? ");
				chooseType();
				int billTypeNum = keyboard.nextInt();
				keyboard.nextLine();
				BillType type = null;
				if (billTypeNum == 1) {
					type = BillType.CLOTHING;
				} else if (billTypeNum == 2) {
					type = BillType.EDUCATION;
				} else if (billTypeNum == 3) {
					type = BillType.FOOD;
				} else if (billTypeNum == 4) {
					type = BillType.GROCERIES;
				} else if (billTypeNum == 5) {
					type = BillType.PHONE;
				} else if (billTypeNum == 6) {
					type = BillType.TRAVEL;
				} else if (billTypeNum == 7) {
					type = BillType.UTILITIES;
				}

				System.out
						.print("What date did you spend the money(write in this form 2007-12-25)? ");
				String dateAsString = keyboard.nextLine();
				String[] token = dateAsString.split("-");
				Calendar dueDate = Calendar.getInstance();
				dueDate.set(Integer.parseInt(token[0]),
						Integer.parseInt(token[1]), Integer.parseInt(token[2]));

				System.out.print("how much money did you spend? ");
				double amountDue = keyboard.nextDouble();
				keyboard.nextLine();// clear out buffer
				try {
					bills.insert(new Bill(vendorName, amountDue, dueDate, type));
				} catch (DuplicateDataException e) {
					System.out.println("this bill is already in the list");
				}

				break;
			case 2:
				System.out.println("Total " + bills.totalBill());
				break;
			case 3:
				System.out
						.print("Choose one of the following number choices, as the way you would like to view your bills");
				choicesCriteria();
				int criteria = keyboard.nextInt();
				keyboard.nextLine();
				if (criteria == 1) {
					System.out.println(bills.toString(bills.iteratorByDate()));
				} else if (criteria == 2) {
					System.out
							.println(bills.toString(bills.iteratorByAmount()));
				} else if (criteria == 3) {
					System.out.println(bills.toString(bills
							.iteratorByBillType()));
				} // else {
					// System.out.println(bills);
				// }

				break;
			case 4:
				System.out
						.println("Choose one of the following categories to pay the first bill of that category");
				choicesCriteria();
				criteria = keyboard.nextInt();
				keyboard.nextLine();
				Bill payed = null;
				try {
					if (criteria == 1) {

						payed = bills.payNextBill(BillCriteria.BILLDUEDATE);

					} else if (criteria == 2) {
						payed =bills.payNextBill(BillCriteria.BILLDUEDATE);
					} else if (criteria == 3) {
						payed=bills.payNextBill(BillCriteria.BILLTYPE);
					}
				} catch (ListEmptyException | NotFoundException e) {
					System.out.println("there are no bills to pay");
				}
				System.out.printf("You just payed an $%.2f bill", payed.getAmountDue() );
				break;
			case 10:
				System.out.println("Opening the System");
				try {
					bills = new BillOrganizer("BillOrginizer.ser");
				} catch (ClassNotFoundException | IOException e) {

					e.printStackTrace();
				}
				break;
			case 0:
				System.out.println("closing the system");
				try {
					bills.closeOrginizer();
				} catch (IOException e) {

					e.printStackTrace();
				}
				break;
			}
		} while (menuChoice != 0);
		keyboard.close();
	}

	private static void chooseType() {
		for (BillType bill : BillType.values()) {
			System.out.println();
			System.out.print(bill.ordinal() + 1);
			System.out.print(". ");
			System.out.print(bill);
		}
	}

	private static void choicesCriteria() {

		for (BillCriteria criteria : BillCriteria.values()) {
			System.out.println();
			System.out.print(criteria.ordinal() + 1);
			System.out.print(". ");
			System.out.print(criteria);
		}

	}

	private static int menu() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Choose one of the following menu choices");
		System.out
				.println("1. Add a bill\n2. get current total\n3. View Bills\n4. Pay Bill \n0. Exit \n10. resore");

		return keyboard.nextInt();

	}
}
