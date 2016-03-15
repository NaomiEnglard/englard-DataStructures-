package pharmacy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import School.NotFoundException;

public class maintianPharmaceutical {
	public static void main(String[] args) {
		PharmacyList pharmacy = null;
		Scanner keyboard = new Scanner(System.in);

		try {

			pharmacy = new PharmacyList();
			pharmacy.connectData("./randomAccess.txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			Scanner aFile = new Scanner(new File("./pharmacyCompanies.txt"));
			while (aFile.hasNext()) {
				try {
					pharmacy.addCompany(aFile.next().trim(), aFile.next()
							.trim(), aFile.nextLine());
				} catch (DuplicateDataException | IOException e) {
					e.printStackTrace();
				} catch (InvalidDataException e) {
					e.printStackTrace();
				}

			}
			aFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int choice;
		do {
			menu();
			System.out
					.println("write the number choice of the menu option u want");
			choice = keyboard.nextInt();
			keyboard.nextLine();// flush
			switch (choice) {
			case 1:
				System.out.print("what is the comapany's name?: ");
				String name = keyboard.nextLine();
				System.out.print("what is the comapany's code?: ");
				String code = keyboard.nextLine();
				System.out.print("what is the comapany's phone number?: ");
				String number = keyboard.nextLine();
				try {
					pharmacy.addCompany(code, number, name);
				} catch (DuplicateDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				System.out
						.print("what is the comapany's name you want to remove?: ");
				name = keyboard.nextLine();
				try {
					pharmacy.removeCompany(name.trim());
				} catch (FileNotFoundException e1) {
					System.out
							.println("This company is no longer active or in the list");
				}
				break;
			case 3:
				System.out.print("what is the comapany's code?: ");
				code = keyboard.nextLine();
				System.out.print("what is the comapany's new phone number?: ");
				number = keyboard.nextLine();
				try {
					pharmacy.modifyCompanyPhone(code, number);
					System.out.println("phone number modified");
				} catch (NotFoundException e) {
					System.out.println("the company was not found in the list");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				System.out.print("what is the comapany's code?: ");
				code = keyboard.nextLine();
				try {
					System.out.println(pharmacy.findCompanyCode(code));
				} catch (NotFoundException e) {
					System.out.println("company code not in list");
				} catch(IOException e){
					e.getMessage();
				}
				break;
			case 5:
				System.out.print("what is the comapany's name?: ");
				name = keyboard.nextLine();
				try {
					System.out.println(pharmacy.findCompanyName(name));
				} catch (NotFoundException e) {
					System.out.println("company name not in list");
				} catch(IOException e){
					e.getMessage();
				}
				break;
			case 6:
				try {
					System.out.println(pharmacy.infoOnFile());
				}  catch (NotFoundException e) {
					System.out.println("company code not in list");
				} catch(IOException e){
					e.getMessage();
				}
				break;
			case 7:
				break;

			}
		} while (choice != 7);
		keyboard.close();
		System.exit(0);

	}

	private static void menu() {
		System.out
				.println("1. Add a pharmaceutical company \n2.Remove a company \n3.Modify company phone number \n"
						+ "4.Display company information given company code \n5. Dispaly company infomration given company name"
						+ "\n6.List information about each company on file, in alphabetical order \n7.End the application");

	}
}
