package School;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class ManageSchool {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		School aSchool = null;
		String[] address = new String[4];
		Address theAddress = null;
		try {
			// create a school based on the four textfiles given
			// get info needed to make a school

			System.out.print("what is the name of the school? ");
			String name = keyboard.nextLine();
			System.out
					.print("What is the address? Format: street,city,state,zip ");
			// split up address to make a address that fits in the address
			address = keyboard.nextLine().split(",");
			theAddress = new Address(address[0], address[1], address[2],
					address[3]);
			System.out.print("what is the phone number? ");
			String phoneNumber = keyboard.nextLine();

			String teacherFile = "./teachers.txt";
			String studentFile = "./students.txt";
			String departmentFile = "./departments.txt";
			String courseFile = "./courses.txt";
			// testing
			// aSchool = new School("name", theAddress, "1", teacherFile,
			// studentFile, departmentFile, courseFile);
			aSchool = new School(name, theAddress, phoneNumber, teacherFile,
					studentFile, departmentFile, courseFile);

		} catch (FileNotFoundException e) {
			System.out.println("The Files needed to access data are not found");
		} catch (InvalidDateException e) {
			System.out
					.println("The phone numbers must be writen in xxxxxxxxxx form, the school was not created");
		} catch (pharmacy.InvalidDataException e) {
			System.out.println("The zip code must be 5 or 9 digits long");
		} 
		int choice;
		do {
			menu();
			choice = keyboard.nextInt();
			choice = checkChoice(choice); // if invalid choice is entered,

			Semester semester;

			switch (choice) {
			case 1:

				// add teacher
				System.out
						.print("Fill in the following infromation for the new teacher: \nID: ");
				Integer ID = keyboard.nextInt();
				keyboard.nextLine();// flush keyboard after int
				System.out.print("First Name: ");
				String FirstName = keyboard.nextLine();
				System.out.print("Last Name: ");
				String LastName = keyboard.nextLine();
				System.out.print("Address (street,city,state,zipcode): ");
				String[] tempAddress = keyboard.nextLine().split(",");
				Address anAddress = null;
				try {
					anAddress = new Address(tempAddress[0], tempAddress[1],
							tempAddress[2], tempAddress[3]);
				} catch (InvalidDataException e) {
					System.out
							.println("The zip code must be 5 or 9 digits long");
				}
				System.out.print("Phone Number: ");
				String phone = keyboard.nextLine();
				System.out.print("Gender ");
				// save upercase F or M for gender
				char Gender = keyboard.nextLine().toUpperCase().charAt(0);
				System.out.print("hire Date mm/dd/yyyy : ");
				String hireDate = keyboard.nextLine();
				System.out.print("Date Of Birth mm/dd/yyyy : ");
				String DOB = keyboard.nextLine();
				System.out.println("chose one of the following job Type: ");
				displayEmoplyeeType();
				String jobType = keyboard.nextLine().toUpperCase();
				System.out.print("Department: ");
				String department = keyboard.nextLine();
				System.out.print("Social Security Number: ");
				String socialsecurity = keyboard.nextLine();
				System.out
						.print("Choose from the follwing list which degree you have: ");
				displayDegree();
				Degree degree = Degree.valueOf(keyboard.nextLine()
						.toUpperCase());
				System.out
						.print("Choose from the follwing list which Major you have: ");
				dispalyMajor();
				Major major = Major.valueOf(keyboard.nextLine().toUpperCase());
				System.out.print("Salary: ");
				Double salary = keyboard.nextDouble();
				keyboard.nextLine(); // flush keyboard
				// add teacher
				try {
					Teacher newTeacher = new Teacher(ID, FirstName, LastName,
							anAddress, phone, Gender, hireDate, DOB, jobType,
							department, socialsecurity, degree, major, salary);
					aSchool.addTeacher(newTeacher);
					System.out.println("a new teacher was added");
				} catch (InvalidDataException e) {
					System.out.println("invalid entry");
				}

				break;
			case 2:
				System.out
						.println("Fill in the following information about the new course: ");
				keyboard.nextLine();
				System.out.print("Course ID: ");
				String courseID = keyboard.nextLine();
				System.out.print("Course Title (description): ");
				String description = keyboard.nextLine();
				String departmentID;
				// display all department options -allow user to choose one
				departmentID = chooseDepId(aSchool); // choose a depId from
														// the list of
														// school depId
				System.out
						.print("Number of credits (do NOT write out the number): ");
				int credits = keyboard.nextInt();
				keyboard.nextLine(); // flush scanner
				Course newCourse;
				try {
					newCourse = new Course(courseID, description, departmentID,
							credits);
					aSchool.addCourse(newCourse);
					System.out.println("a new course was added");

				} catch (AlreadyExistsException e1) {
					System.out.println("this cousre already exists");
				} catch (pharmacy.InvalidDataException e) {
					System.out
							.println("A course can only have 3 or 4 credits, the course wasnot added");
				} 
				break;
			case 3:
				Department newDepartment = null;
				System.out.println("Fill in the following Data: ");
				keyboard.nextLine();
				System.out.print("Department ID: ");
				String depId = keyboard.nextLine();
				System.out.print("Department Name: ");
				String depName = keyboard.nextLine();
				System.out
						.println("Location, phone number, fax number, and department chair are options, do you want to fill them out? (y/n): ");
				// upercase first letter of answer to it will be Y or N
				char answer = keyboard.next().toUpperCase().charAt(0);
				if (answer == 'Y') {
					keyboard.nextLine();
					System.out.print("Location: ");
					String location = keyboard.nextLine();
					System.out.print("phone number: ");
					phone = keyboard.nextLine();
					System.out.print("Fax Number: ");
					String fax = keyboard.nextLine();
					System.out.print("Department chairperson: ");
					String depChiarperson = keyboard.nextLine();
					try {
						newDepartment = new Department(depId, depName,
								location, phone, fax, depChiarperson);

					} catch (pharmacy.InvalidDataException e) {
						System.out
								.println("Phone and fax numbers must be writen in xxxxxxxxxx form ");
					} 

				} else { // if answer is no
					try {
						newDepartment = new Department(depId, depName);
					} catch (pharmacy.InvalidDataException e) {
						System.out
								.println("Phone and fax numbers must be writen in xxxxxxxxxx form ");
					}

				}
				aSchool.addDepartment(newDepartment);
				System.out.println("a departmetn was added");
				break;
			case 4:
				System.out
						.print("what is the ID of the teacher you want to remove?");
				int id = keyboard.nextInt();
				keyboard.nextLine();
				try {
					aSchool.removeTeacher(id);
				} catch (NotFoundException e) {
					System.out.println("the id entered is not in the list");
				}
				System.out.println("a teacher was removed");
				break;
			case 5:
				System.out
						.print("what is the ID of the student you want to remove?");
				id = keyboard.nextInt();
				keyboard.nextLine();
				try {
					aSchool.removeStudent(id);
					System.out.println("a student was removed");
				} catch (NotFoundException e) {
					System.out.println("the id entered is not in the list");
				}
				break;
			case 6:
				keyboard.nextLine();
				System.out
						.print("what is the ID of the course you want to remove?");
				String cId = keyboard.nextLine();
				try {
					aSchool.removeCourse(cId);
					System.out.println("a course was removed");

				} catch (NotFoundException e) {
					System.out
							.println("the course id entered is not in the list");
				}
				break;
			case 7:
				try {
					System.out
							.print("what is the id of the teacher you want to modify? ");
					id = keyboard.nextInt();
					keyboard.nextLine(); // flush buffer
					System.out
							.print("what should this teachers new last name be? ");
					String newName = keyboard.nextLine();
					aSchool.modifyTeacherLastName(id, newName);
					System.out.println("a teachers last name was changed");

				} catch (NotFoundException e) {
					System.out.println("the id entered is not in the list");
				}
				break;
			case 8:
				System.out
						.print("what is the id of the teacher you want to modify? ");
				id = keyboard.nextInt();
				keyboard.nextLine(); // flush buffer
				System.out
						.print("what should this teachers new address (street,city,state,zip)? ");
				address = keyboard.nextLine().split(",");
				try {
					theAddress = new Address(address[0], address[1],
							address[2], address[3]);
					aSchool.modifyTeacherAddress(id, theAddress);
					System.out.println("address modified");
				} catch (InvalidDataException e) {
					System.out
							.println("The zip code must be 5 or 9 digits long");
				} catch (NotFoundException e) {
					System.out.println("the id entered is not in the list");
				}
				break;
			case 9:
				System.out
						.print("what is the id of the teacher you want to modify? ");
				id = keyboard.nextInt();
				keyboard.nextLine(); // flush buffer
				System.out
						.print("what is the teachers new degree? Choose from the following ");
				displayDegree();
				Degree newDegree = Degree.valueOf(keyboard.nextLine()
						.toUpperCase());
				System.out
						.println("what is the teachers Major? Choose from the following");
				dispalyMajor();
				Major newMajor = Major.valueOf(keyboard.nextLine()
						.toUpperCase());
				try {
					aSchool.modifyTeacherDegree(id, newDegree, newMajor);
					System.out.println("Degree modified");

				} catch (NotFoundException e) {
					System.out.println("the id entered is not in the list");
				}
				break;
			case 10:
				System.out
						.print("what is the id of the teacher you want to give a raise to? ");
				id = keyboard.nextInt();
				System.out.print("what percent should they be raised? ");
				Double percent = keyboard.nextDouble();
				keyboard.nextLine(); // flush
				try {
					aSchool.giveTeacherRaise(id, percent);
					System.out.print("taecher was given a raise");

				} catch (NotFoundException e) {
					System.out.println("the id entered is not in the list");
				}
				break;
			case 11:
				System.out
						.print("what is the id of the teacher you want to give a raise to? ");
				id = keyboard.nextInt();
				System.out.print("what is the new salary");
				int amount = keyboard.nextInt();
				keyboard.nextLine();
				try {
					aSchool.giveTeacherRaise(id, amount);
					System.out.println("raise succesfull");

				} catch (NotFoundException e) {
					System.out.println("the id entered is not in the list");
				}
				break;
			case 12:
				System.out
						.print("what is the id of the student you want to modify? ");
				id = keyboard.nextInt();
				keyboard.nextLine();
				System.out.print("what is the new last name? ");
				String newName = keyboard.nextLine();
				try {
					aSchool.modifyStudentLastName(id, newName);
					System.out.println("students name modified");

				} catch (NotFoundException e) {
					System.out.println("the id entered is not in the list");
				}
				break;
			case 13:
				System.out
						.print("what is the id of the student you want to modify? ");
				id = keyboard.nextInt();
				keyboard.nextLine(); // flush the buffer
				System.out
						.print("what is the new phone number name (xxxxxxxxxx format) ? ");
				String newPhoneNumber = keyboard.nextLine();
				try {
					aSchool.modifyStudentPhoneNumber(id, newPhoneNumber);
					System.out.println("students phone number modified");

				} catch (NotFoundException e) {
					System.out.println("the id entered is not in the list");
				}
				break;
			case 14:
				try {
					String courseId = null;
					System.out
							.print("What is the id of the student who completed a course");
					id = keyboard.nextInt();
					keyboard.nextLine(); // flush the buffer
					System.out.print("what is the course id?");
					courseId = keyboard.nextLine();
					System.out
							.print("which of the following letter grade did the student recieve (write out the whole word)?");
					displayGrades();
					Grade grade = Grade.valueOf(keyboard.nextLine()
							.toUpperCase());
					aSchool.addCompletedCourse(id, courseId, grade);
					System.out.println("course added");
				} catch (IllegalArgumentException e) {
					System.out
							.println("The grade entered is not one of the listed Grades");
				} catch (NotFoundException e) {
					System.out
							.println("check course id and student id to make sure they are valid");
				} catch (pharmacy.InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 15:
				System.out.print("what is the id of the student?");
				id = keyboard.nextInt();
				keyboard.nextLine(); // flush buffer
				Double GPA = null;
				try {
					GPA = aSchool.getStudentGPA(id);
					System.out.println(GPA);

				} catch (NotFoundException e) {
					System.out.println("the id entered is not in the list");
				}
				break;
			case 16:
				try {
					System.out.print("what is the id of the student?");
					id = keyboard.nextInt();
					keyboard.nextLine(); // flush buffer
					System.out.print("what is course id? ");
					courseID = keyboard.nextLine();
					String aGrade = aSchool.getGradeofCourse(id, courseID);
					System.out.println(aGrade);

				} catch (NotFoundException e) {
					System.out.println("this course is not in the list");
				} catch (pharmacy.InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 17:
				try {
					System.out.print("What is the students id?");
					id = keyboard.nextInt();
					keyboard.nextLine();
					System.out.print("what is the departmetn Id?");
					departmentID = keyboard.nextLine();
					ArrayList<CompletedCourse> cCL = aSchool
							.getCoursesbyDepartment(id, departmentID);
					System.out.println(cCL);
				} catch (NotFoundException e) {
					System.out
							.println("The id entered is not a student in the list of people in this school");
				}
				break;
			case 18:

				try {
					System.out.print("what is the students id?");
					id = keyboard.nextInt();
					keyboard.nextLine();
					System.out
							.print("which of the following grades you want to find?");
					displayGrades();
					Grade grade = Grade.valueOf(keyboard.nextLine());
					ArrayList<CompletedCourse> cCL = aSchool.getCoursesbyGrade(
							id, grade);
					System.out.println(cCL);
				} catch (NotFoundException e) {
					System.out
							.println("The id entered is not a student in the list of people in this school");
				}
				break;
			case 19:
				System.out.println(aSchool.getTeachersSortedByName());
				break;
			case 20:
				System.out.println(aSchool.getTeachers());
				break;
			case 21:
				System.out.println(aSchool.getStudentByName());
				break;
			case 22:
				System.out.println(aSchool.getStudents());
				break;
			case 23:
				try {
					System.out.print("what is the teachers id? ");
					id = keyboard.nextInt();
					keyboard.nextLine(); // flush
					System.out.print("what is the course id? ");
					courseID = keyboard.nextLine();
					System.out.print("what year is the course taught in? ");
					int year = keyboard.nextInt();
					keyboard.nextLine(); // flush
					System.out.println("which of the following semsester? ");
					displaySemesters();
					semester = Semester.valueOf(keyboard.nextLine()
							.toUpperCase());
					System.out.println("which of the following time slot?");
					displayTimeSlots();
					TimeSlot section = TimeSlot.valueOf(keyboard.nextLine()
							.toUpperCase());
					aSchool.addTaughtCourse(id, courseID, year, semester,
							section);
					System.out.println("taught course added");
				} catch (IllegalArgumentException e) {
					System.out
							.print("The semester or timeslot entered was not one of the listed semesters or time slots");
				} catch (pharmacy.InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 24:

				System.out.print("what is the teachers id? ");
				id = keyboard.nextInt();
				System.out.print("in what year?");
				int year = keyboard.nextInt();
				keyboard.nextLine(); // flush
				System.out.println("which of the following semsester? ");
				displaySemesters();
				semester = Semester.valueOf(keyboard.nextLine().toUpperCase());
				System.out.println(aSchool.howManyCoursesPerSemester(id, year,
						semester));

				break;
			}

		} while (choice != 0);
		keyboard.close();

	}

	private static String chooseDepId(School aSchool) {
		String departmentID;
		ArrayList<String> depID = new ArrayList<String>();
		Scanner keyboard = new Scanner(System.in);
		do {
			System.out.println("choose one of the following Depratment ID: ");
			for (String d : aSchool.getDepartmentID()) {
				System.out.print(d + "\t");
				depID.add(d);
			}
			departmentID = keyboard.nextLine();
		} while (!depID.contains(departmentID));
		keyboard.close();
		return departmentID;
	}

	private static int checkChoice(int choice) {
		Scanner input = new Scanner(System.in);
		while (choice < 0 || choice > 25) {

			System.out.println("please choose a choice from the list");

			menu();
			choice = input.nextInt();
		}
		// i can not close the scanner here or it will disrupt my input stream
		return choice;
	}

	public static void menu() {
		System.out
				.printf("choose one of the following choices:\n"
						+ "1.  Add a teacher \t\t2. Add a course \t\t3. Add a department"
						+ "\n4.  Remove a Teacher \t\t5. Remove a Student \t\t"
						+ "6. Remove a Course"
						+ "\n7.  Modify a Teacher's Last Name 8. Modify a teachers address"
						+ "\t9. Modify a Teacher's Degree \n10. Give a teacher a Raise \t11. Change teachers Salary"
						+ "\t12. Modify a Students Last Name \n13. Modify student phone Number 14. Add a completed Course"
						+ "\t15. Get Student's GPA \n16. Get grade of a course \t17. Get courses in  a department 18. Get courses with"
						+ "a certain grade  \n19. Teachers namse alphabatized 20. List of teachers by ID \t21. List of alphabatized studetns"
						+ "\n22."
						+ " List of students by ID \t23. Add a taugth course \t24. How many courses taught a semester \n");
	}

	private static void dispalyMajor() {
		for (Major aMajor : Major.values()) {
			System.out.print("\n" + (aMajor.ordinal() + 1) + ". " + aMajor);

		}

	}

	private static void displayDegree() {
		for (Degree aDegree : Degree.values()) {
			System.out.print("\n" + (aDegree.ordinal() + 1) + ". " + aDegree);
		}

	}

	private static void displayGrades() {
		for (Grade g : Grade.values()) {
			System.out.println(g.toString() + " = " + g.getGradeValue());
		}
	}

	private static void displaySemesters() {
		for (Semester s : Semester.values()) {
			System.out.println(s);
		}
	}

	private static void displayEmoplyeeType() {
		for (EmployeeType e : EmployeeType.values()) {
			System.out.println(e);
		}
	}

	private static void displayTimeSlots() {
		for (TimeSlot tS : TimeSlot.values()) {
			System.out.println(tS);
		}
	}
}
