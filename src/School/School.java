package School;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import pharmacy.InvalidDataException;


public class School {
	/*
	 * fix the get teacher method to sort by id ??sort by id fic getstudent
	 */
	private String SchoolName;
	private Address theAddress;
	private String PhoneNumber;
	private ArrayList<Person> personList; // to store references to instances of
											// Students and Teachers,
	private ArrayList<Course> courseList; // to store references to each Course
											// the School may offer
	private ArrayList<Department> departmentList;// to store references to each
													// Department in the School

	// Implement constructors.
	public School(String schoolname, Address address, String PhoneNumber)
			throws InvalidDateException, FileNotFoundException, InvalidDataException {
		this(schoolname, address, PhoneNumber, null, null, null, null);

	}

	public School(String schoolname, Address address, String phoneNumber,
			String teachFileName, String studentFileName,
			String departmentFileName, String courseFileName)
			throws FileNotFoundException, InvalidDateException, InvalidDataException {
		this.SchoolName = schoolname;
		this.theAddress = address;
		this.PhoneNumber = phoneNumber;
		personList = new ArrayList<Person>();
		departmentList = new ArrayList<Department>();
		courseList = new ArrayList<Course>();
		// read date in from each file
		// try {
		// create an array of files you want to read in
		File[] listOfFiles = new File[4];
		listOfFiles[0] = new File(teachFileName); // teacher file =employee
													// File
		listOfFiles[1] = new File(studentFileName); // student file
		listOfFiles[2] = new File(departmentFileName); // department File
		listOfFiles[3] = new File(courseFileName); // course File
		for (int i = 0; i < listOfFiles.length; i++) {
			Scanner aFile = new Scanner(listOfFiles[i]); // select a file to
															// read in
			// while file has more to read in
			while (aFile.hasNext()) {
				switch (i) {
				case 0: // techer
					// read in data from file about teacher
					Integer ID = aFile.nextInt();
					String FirstName = aFile.next();
					String LastName = aFile.next();
					// may not be a MidInitail String MidInitial;
					aFile.nextLine();
					String house = aFile.nextLine();
					String cityState = aFile.nextLine().trim();
					// split the cityState into city and state
					String[] token = cityState.split(",");
					String city = token[0];
					String state = token[1];
					String zipCode = aFile.next();
					Address anAddress = new Address(house, city, state, zipCode);
					String PhoneNumber = aFile.next();
					if (PhoneNumber.equals("N/A")) {
						PhoneNumber = null; // if the phone number is not
											// availble
					}
					char Gender = aFile.next().charAt(0); // use the first
															// letter of
															// input only m
															// = male f
															// =female
					String hireDate = aFile.next();
					String birthDate = aFile.next();
					String employeeType = aFile.next();
					String departmentCode = aFile.next();
					String socialSecurityNuber = aFile.next();
					Degree degree = Degree(aFile.next());
					Major major = Major(aFile.next());
					Double salary = aFile.nextDouble();
					// add to list of employee
					Teacher aTeacher = new Teacher(ID, FirstName, LastName,
							anAddress, PhoneNumber, Gender, hireDate,
							birthDate, employeeType, departmentCode,
							socialSecurityNuber, degree, major, salary);
					personList.add(aTeacher);
					// System.out.println(personList);
					break;

				case 1: // student
					Integer StudentID = aFile.nextInt();
					LastName = aFile.next();
					FirstName = aFile.next();
					String MidName = aFile.next();
					if (MidName.equals("N/A")) {
						MidName = null;
					}
					aFile.nextLine();
					cityState = aFile.nextLine().trim();
					// split the cityState into city and state
					token = cityState.split(",");
					city = token[0];
					state = token[1];
					// if a state is two words then and it has a _ to
					// seprate the words
					// replace the _ with a space
					if (state.contains("_")) {
						String[] tokenTwo = state.split("_");
						StringBuffer buff = new StringBuffer();
						buff.append(tokenTwo[0]);
						buff.append(" ");
						buff.append(tokenTwo[1]);
						state = buff.toString();
					}
					anAddress = new Address(null, city, state, null);
					phoneNumber = aFile.next();
					Gender = aFile.next().charAt(0);
					major = Major(aFile.next());
					personList.add(new Student(StudentID, FirstName, LastName,
							MidName, anAddress, phoneNumber, Gender, major,
							null, null, null, null));
					// System.out.println(personList);
					break;

				case 2: // department
					String[] tokens = aFile.nextLine().split(";");
					String DepartmentID = tokens[0];
					String DepartmentName = tokens[1];
					String location = tokens[2];
					String aPhoneNumber = tokens[3];
					String faxNumber = tokens[4];
					String teacherId = tokens[5];

					departmentList.add(new Department(DepartmentID,
							DepartmentName, location, aPhoneNumber, faxNumber,
							teacherId));
					break;
				case 3: // course name
					String[] moretoken = aFile.nextLine().split(";");
					String CourseID = moretoken[0];
					String CourseName = moretoken[1];
					String aDepartmentID = moretoken[2];
					int credits = Integer.parseInt(moretoken[3].trim());
					courseList.add(new Course(CourseID, CourseName,
							aDepartmentID, credits));
					// System.out.println(courseList);
					break;
				}// end switch

			}
			aFile.close();

		}
		/*
		 * } catch (FileNotFoundException e) {
		 * 
		 * //if the files are null they cant be found still set up school if(
		 * teachFileName.equals(null) && studentFileName.equlas(null) &&
		 * departmentFileName.equals(null) && courseFileName.equals(null)){
		 * 
		 * } else
		 * 
		 * e.printStackTrace(); }
		 */

	}

	public String getSchoolName() {
		return SchoolName;
	}

	public void setSchoolName(String schoolName) {
		SchoolName = schoolName;
	}

	public Address getTheAddress() {
		Address copy = new Address(theAddress.getCity(), theAddress.getState(),
				theAddress.getStreet(), theAddress.getZip());
		return copy;
	}

	public void setTheAddress(Address theAddress) {
		this.theAddress = theAddress;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public ArrayList<String> getPersonFirstNameLastNameId() {
		
		 ArrayList<String> copy = new ArrayList<String>(); 
		 for(Person ppl: this.personList){ 
			 copy.add(ppl.getFirstName() + " "+ ppl.getLastName() + " "+ppl.getID()); 
			 }
		return copy;
		 
		//return personList;
	}

	public void setPersonList(ArrayList<Person> personList) {
		this.personList = personList;
	}

	public ArrayList<Course> getCourseList() throws InvalidDataException {
		ArrayList<Course> copy = new ArrayList<Course>();
		for (Course aCourse : this.courseList) {
			copy.add(new Course(aCourse.getCourseID(),
					aCourse.getDescription(), aCourse.getDepartmentID(),
					aCourse.getCreditNumber()));
		}
		return copy;
	}

	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}

	public ArrayList<String> getDepartmentID() {

		ArrayList<String> copy = new ArrayList<String>();
		for (Department aDep : this.departmentList) {
			copy.add(aDep.getDepartmentID());
		}
		return copy;

		// return this.departmentList;
	}

	public void setDepartmentList(ArrayList<Department> departmentList) {
		this.departmentList = departmentList;
	}

	private Major Major(String next) {
		for (Major aMajor : Major.values()) {
			if (aMajor.toString().equals(next)) {
				return aMajor;
			}
		}
		return null; // if its not a major in the list return null
	}

	private Degree Degree(String next) {
		for (Degree aDegree : Degree.values()) {
			if (aDegree.toString().equalsIgnoreCase(next)) {
				return aDegree;
			}
		}
		return null;
	}

	// o addTeacher()
	public void addTeacher(Teacher newTeacher) {
		boolean inList = false; // keep track if the teacher is already in the
								// list
		for (Person people : this.personList) {
			if (people instanceof Teacher) { // if the person is a teacher
				if (people.equals(newTeacher)) {// if the person is the same as
												// the newTeacher/the teacher
												// you want to add
					inList = true; // then record that the teacher is in the
									// list and dont add it at the end
					break;
				}

			}
		}
		if (!inList) { // if the new Teacher is not in the list of people add
						// the new teacher to the list
			this.personList.add(newTeacher);
		}

	}

	// addStudent()
	public void addStudent(Student newStudent) {
		boolean inList = false; // keep track if newStudent is already in the
								// listof people
		for (Person people : this.personList) {// loop through list of people
			if (people instanceof Student) { // if the person is a student
				if (people.equals(newStudent)) { // if the person is the same as
													// the student
					inList = true;// record that the newStudent is in the list
					break;
				}
			}
		}
		if (!inList) {
			this.personList.add(newStudent);
		}
	}

	// addCourse()
	public void addCourse(Course newCourse) throws AlreadyExistsException {
		boolean inList = false; // keep track if newCourse is already in the
								// listof courses
		for (Course acourse : this.courseList) {// loop through list of people
			if (acourse.equals(newCourse)) { // if the person is the same as the
												// student
				inList = true;// record that the newStudent is in the list
				break;
			}

		}
		if (!inList) {
			this.courseList.add(newCourse);
		} else {
			// if in list already throw error to inform user no new course added
			throw new AlreadyExistsException();
		}
	}

	// addDepartment()
	public void addDepartment(Department newDepartment) {
		boolean inList = false; // keep tracck of newDepartment if it is in the
								// list of dep. already
		for (Department aDep : this.departmentList) { // loop through all dep in
														// the list
			if (aDep.equals(newDepartment)) { // check if the new one is the
												// same as anyone in the list
				inList = true;
				break;
			}
		}
		if (!inList) {
			this.departmentList.add(newDepartment);
		}
	}

	// removeTeacher()
	public void removeTeacher(int teacherID) throws NotFoundException {
		boolean found = false;
		// this.personList.remove(byeTeacher);
		for (int i = 0; i < this.personList.size(); i++) { // loop through all
															// ppl
			// in list
			Person p = this.personList.get(i);
			if (p instanceof Teacher) { // if the person is a
										// teacher check
				// if they equal the teacher
				if (((Teacher) p).getID().equals(teacherID)) { // if
																// they
																// are
					// the same as
					// byeTeacher
					this.personList.remove(i); // remove the teacher
					found = true;
					break;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	// removeStudent()
	public void removeStudent(int studentID) throws NotFoundException {
		boolean found = false;
		for (int i = 0; i < this.personList.size(); i++) { // loop through all
															// ppl in
			// list
			Person p = this.personList.get(i);
			if (p instanceof Student) { // if the person is a
										// student check
				// if they equal the student
				// int hold = p.getID();
				if (p.getID().equals(studentID)) { // if they are the
					// same as
					// byeStudent
					this.personList.remove(i); // remove the student
					found = true;
				}
			}

		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	// removeCourse()
	public void removeCourse(String courseID) throws NotFoundException {
		boolean found = false;
		for (int i = 0; i < this.courseList.size(); i++) { // loop through all
			// courses in
			// list
			Course c = this.courseList.get(i);
			if (c.getCourseID().equalsIgnoreCase(courseID)) { // if
																// they
				// are the
				// same as
				// byeTeacher
				this.courseList.remove(i); // remove the teacher
				found = true;
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	// modifyTeacherLastName(Integer teacherID, String newLastName)
	public void modifyTeacherLastName(Integer teacherID, String newLastName)
			throws NotFoundException {
		boolean found = false;
		for (Person p : this.personList) {
			if (p instanceof Teacher) {
				// Integer hold = ((Teacher) p).getID();
				if (p.getID().equals(teacherID)) {
					p.setLastName(newLastName);
					found = true;
					break;
				}
			}

		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	// modifyTeacherAddress(Integer teacherID, Address address)
	public void modifyTeacherAddress(Integer teacherID, Address address)
			throws NotFoundException {
		boolean found = false;
		for (Person p : this.personList) {
			if (p instanceof Teacher) {
				// Integer hold = p.getID();
				if (p.getID().equals(teacherID)) {
					p.setAddress(address);
					found = true;
					break;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	// modifyTeacherDegree(Integer teacherID, Degree degree, Major major)
	public void modifyTeacherDegree(Integer teacherID, Degree degree,
			Major major) throws NotFoundException {
		boolean found = false;
		for (Person p : this.personList) {
			if (p instanceof Teacher) {
				// int hold = p.getID();
				if (p.getID().equals(teacherID)) {
					((Teacher) p).setDegree(degree);
					((Teacher) p).setMajorID(major);
					found = true;
					break;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	// giveTeacherRaise(Integer teacherID, Double percent)
	public void giveTeacherRaise(Integer teacherID, Double percent)
			throws NotFoundException {
		boolean found = false;
		for (Person p : this.personList) {
			if (p instanceof Teacher) {
				// int hold = ((Teacher) p).getID();
				if (p.getID().equals(teacherID)) {
					((Teacher) p).applyRaise(percent);
					found = true;
					break;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	// giveTeacherRaise(Integer teacherID, Integer amount)
	public void giveTeacherRaise(Integer teacherID, Integer amount)
			throws NotFoundException {
		boolean found = false;
		for (Person p : this.personList) {
			if (p instanceof Teacher) {
				// int hold = p.getID();
				if (p.getID().equals(teacherID)) {
					((Teacher) p).setSalary(amount);
					found = true;
					break;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	// modifyStudentLastName(Integer studentID, String newLastName)
	public void modifyStudentLastName(Integer studentID, String newLastName)
			throws NotFoundException {
		boolean found = false;
		for (Person p : this.personList) {
			if (p instanceof Student) {
				// int hold = p.getID();
				if (p.getID().equals(studentID)) {
					p.setLastName(newLastName);
					found = true;
					break;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	// modifyStudentPhoneNumber(Integer studentID, String newPhoneNumber)
	public void modifyStudentPhoneNumber(Integer studentID,
			String newPhoneNumber) throws NotFoundException {
		boolean found = false;
		for (Person p : this.personList) {
			if (p instanceof Student) {
				// int hold = p.getID();
				if (p.getID().equals(studentID)) {
					p.setPhoneNumber(newPhoneNumber);
					found = true;
					break;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
	}

	// addCompletedCourse(Integer studentID, String courseID, Grade grade)
	public void addCompletedCourse(Integer studentID, String courseID,
			Grade grade) throws NotFoundException, InvalidDataException {
		boolean found = false;
		// search through ArrayList of Courses and find the courseID , then
		// based on
		// the data you have instantiate a CompletedCourse and add that course
		// to
		// record of the Student with id, studentID.
		for (Course allCourse : this.courseList) {
			if (allCourse.getCourseID().equalsIgnoreCase(courseID)) {
				for (Person p : this.personList) { // loop through list of ppl
					if (p.getID().equals(studentID)) { // if the person has the
														// id
						// requested\
						((Student) p).completeCourse(allCourse, grade);
						found = true;
						break;
					}
				}
				break; // this will stop the looping
			}
		}
		if (!found) {
			throw new NotFoundException();
		}

	}

	// getStudentGPA(Integer studentID)
	public Double getStudentGPA(Integer studentID) throws NotFoundException {
		Double gpa = null;
		boolean found = false;
		for (Person p : this.personList) {
			if (p instanceof Student) {
				// int hold = p.getID();
				if (p.getID().equals(studentID)) {
					gpa = ((Student) p).getGPA();
					found = true;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
		return gpa;
	}

	// getGradeofCourse(Integer studentID, String courseID)
	public String getGradeofCourse(Integer studentID, String courseID)
			throws NotFoundException, InvalidDataException {
		Grade grade = null;
		boolean found = false;
		for (Person p : this.personList) {
			if (p instanceof Student) {
				// int hold = p.getID();
				if (p.getID().equals(studentID)) {
					CompletedCourse cc = ((Student) p)
							.findCompletedCourse(courseID);
					grade = cc.getGrade();
					found = true;
				}
			}
		}
		if (!found) {
			throw new NotFoundException();
		}
		return (grade.toString());
	}

	// getCoursesbyDepartment(Integer studentID, String departmentID)
	public ArrayList<CompletedCourse> getCoursesbyDepartment(Integer studentID,
			String departmentID) throws NotFoundException {
		ArrayList<CompletedCourse> list = new ArrayList<CompletedCourse>();
		boolean found = false;
		for (Person p : this.personList) {
			if (p instanceof Student) { // if its a student
				if (p.getID().equals(studentID)) { // if its the student with
													// given id
					found = true;
					for (CompletedCourse cC : ((Student) p).getCourses()) { // go
																			// through
																			// all
																			// the
																			// courses
																			// that
																			// student
																			// has
						if (cC.getDepartmentID().equalsIgnoreCase(departmentID)) {// if
																					// course
																					// is
																					// from
																					// dep
																					// add
																					// to
																					// list
							list.add(cC);
						}// end if
					}// end for
				}// end if
			}// end if
		}// end for
		if (!found) {
			throw new NotFoundException();
		}
		return list;
	}

	// getCoursesbyGrade(Integer studentID, Grade g)
	public ArrayList<CompletedCourse> getCoursesbyGrade(Integer studentID,
			Grade g) throws NotFoundException {
		ArrayList<CompletedCourse> list = new ArrayList<CompletedCourse>();
		boolean found = false;
		for (Person p : this.personList) {
			if (p instanceof Student) { // if its a student
				if (p.getID().equals(studentID)) { // if its the student with
													// given id
					found = true;
					for (CompletedCourse cC : ((Student) p).getCourses()) { // go
																			// through
																			// all
																			// the
																			// courses
																			// that
																			// student
																			// has
						if (cC.getGrade() == g) {// if course is from dep add to
													// list
							list.add(cC);
						}// end if
					}// end for
				}// end if
			}// end if
		}// end for
			// if person id is not a student in the person list send error
		if (!found) {
			throw new NotFoundException();
		}
		return list;
	}

	// getTeachersSortedByName()
	public ArrayList<String> getTeachersSortedByName() {

		ArrayList<String> sortedTeachers = new ArrayList<String>();
		for (Person ppl : this.personList) {
			if (ppl instanceof Teacher) {
				sortedTeachers.add((ppl.getLastName() + " ," + ppl
						.getFirstName())); // add all the teachers last names to
											// array list
			}
		}

		// sort the list of last names
		Collections.sort(sortedTeachers);
		return sortedTeachers;

	}

	// getTeachers()
	public ArrayList<String> getTeachers() {
		ArrayList<String> Teachers = new ArrayList<String>();
		for (Person ppl : this.personList) {
			if (ppl instanceof Teacher) {
				// add all teachers names to arraylist
				Teachers.add((ppl.getID() + " " + ppl.getLastName()));
			}
		}
		// return list of Teachers sorted by teacherid
		Collections.sort(Teachers, new Comparator<String>() {
			@Override
			public int compare(String teacherOne, String teacherTwo) {
				return teacherOne.compareTo(teacherTwo);
			}
		});

		return Teachers;

	}

	// getStudents()

	public ArrayList<String> getStudents() {
		// return list of Students sorted by studentid
		ArrayList<String> students = new ArrayList<String>();
		for (Person ppl : this.personList) { // loop through all ppl
			if (ppl instanceof Student) { // if they are a student
				students.add((ppl.getID() + " - " + ppl.getLastName()));
			}
		}
		Collections.sort(students, new Comparator<String>() {
			@Override
			public int compare(String studentOne, String studentTwo) {
				return studentOne.compareTo(studentTwo);
			}
		});

		return students;
	}

	// getStudentsByName()
	public ArrayList<String> getStudentByName() {
		// return list of Students sorted by lastname, firstname
		ArrayList<String> student = new ArrayList<String>();

		for (Person ppl : this.personList) { // loop through all ppl
			if (ppl instanceof Student) { // if they are a student
				student.add((ppl.getLastName() + "," + ppl.getFirstName()));
			}
		}
		return student;
	}

	// addTaughtCourse (Integer teacherID, String courseID, Integer year,
	// Semester semester, Section section)
	public void addTaughtCourse(Integer teacherID, String courseID,
			Integer year, Semester semester, TimeSlot section) throws InvalidDataException {
		// search through ArrayList<Person> to find the Teacher with id,
		// teacherID.
		for (Person ppl : this.personList) {
			if (ppl instanceof Teacher) { // if the person is a teacher
				if (((Teacher) ppl).getID().equals(teacherID)) { // check if
																	// their id
					// is the same as
					// given
					// Then search for the Course in ArrayList<Course> that has
					// courseID.
					for (Course allC : this.courseList) {
						if (allC.getCourseID().equalsIgnoreCase(courseID)) {
							// invoke the Teacher.taughtCourse sending it the
							// Course with courseID,
							// year, semester, and section.
							((Teacher) ppl).taughtClasses(allC, year, semester,
									section);
							break;
						}
					}
				}
			}
		}
	}

	// howManyCoursesPerSemester(Integer teacherID, Integer Year, Semester
	// semester)
	public Integer howManyCoursesPerSemester(Integer teacherID, Integer Year,
			Semester semester) {
		// search for the Teacher with teacherID
		Integer amount = null;
		for (Person ppl : this.personList) {
			if (ppl instanceof Teacher) {
				if (((Teacher) ppl).getID().equals(teacherID)) {
					// invoke the Teacher.howManyCoursesPerSemester to find out
					// how many courses that
					// particular Teacher taught that semester.
					amount = ((Teacher) ppl).getCoursesTaught().size();
					break;
				}
			}
		}
		return amount;
	}

	// to String
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" School; ");
		buffer.append(this.SchoolName);
		buffer.append(" Address: ");
		buffer.append(this.theAddress);
		buffer.append(" Phone Number: ");
		buffer.append(this.PhoneNumber);
		buffer.append(" People: ");
		buffer.append(this.personList);
		buffer.append(" courses: ");
		try {
			buffer.append(this.getCourseList());
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buffer.append(" Departments: ");
		buffer.append(this.departmentList);
		return buffer.toString();

	}
}
