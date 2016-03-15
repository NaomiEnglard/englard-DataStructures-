package School;

import java.util.ArrayList;

import pharmacy.InvalidDataException;

public class Teacher extends Employee {

	private Integer ID;
	private String DepartmentID;
	private String SocialSecurityNum;
	private Degree degree;
	private Major majorID;
	private Double Salary;
	private ArrayList<TaughtCourse> coursesTaught;

	// with no midinitial and phone number
	public Teacher(Integer ID, String FirstName, String LastName,
			Address address, char Gender, String hireDate, String DOB,
			String jobType, String department, String socialsecurity,
			Degree degree, Major major, Double salary) {
		this(ID, FirstName, LastName, null, address, null, Gender, hireDate,
				DOB, jobType, department, socialsecurity, degree, major, salary);
	}// no middle initial

	public Teacher(Integer ID, String FirstName, String LastName,
			Address address, String phone, char Gender, String hireDate,
			String DOB, String jobType, String department,
			String socialsecurity, Degree degree, Major major, Double salary) {
		this(ID, FirstName, LastName, null, address, phone, Gender, hireDate,
				DOB, jobType, department, socialsecurity, degree, major, salary);
	}

	// with all info
	public Teacher(Integer ID, String FirstName, String LastName,
			String MidInitial, Address address, String PhoneNumber,
			char Gender, String hireDate, String DOB, String jobType,
			String department, String socialsecurity, Degree degree,
			Major major, Double salary) {

		super(ID, FirstName, LastName, MidInitial, address, PhoneNumber,
				Gender, hireDate, DOB, jobType);
		if (salary > 0 && salary < 120000) {
			this.DepartmentID = department;
			this.SocialSecurityNum = socialsecurity;
			this.degree = degree;
			this.majorID = major;
			this.Salary = salary;
			this.coursesTaught = new ArrayList<TaughtCourse>();
			this.ID = super.getID();
		}
	}

	// getters and setters
	public Integer getID() {
		return ID;

	}

	public String getDepartmentID() {
		return DepartmentID;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		for (Degree aDegree : Degree.values()) {
			if (aDegree.toString().equals(degree)) {
				this.degree = aDegree;
			}
		}
	}

	public Major getMajorID() {
		return majorID;
	}

	public void setMajorID(Major majorID) {
		// validate major id
		this.majorID = majorID;
	}

	public Double getSalary() {
		return Salary;
	}

	public void setSalary(Integer amount) {
		if (amount > 0 && amount < 120000)
			Salary = amount * 1.00; // times by 1 to convert the integer input
									// to a double output
	}

	public ArrayList<TaughtCourse> getCoursesTaught() {
		return coursesTaught;
	}

	public void setCoursesTaught(ArrayList<TaughtCourse> coursesTaught) {
		this.coursesTaught = coursesTaught;
	}

	public String getSocialSecurityNum() {
		return SocialSecurityNum;
	}

	// additional methods
	// raise the salary by a percent
	public void applyRaise(Double percent) {
		this.Salary = this.Salary * percent;
	}

	// add a course to list of taught courses
	public void taughtClasses(Course c, Integer Year, Semester semester,
			TimeSlot sectionID) throws InvalidDataException {
		TaughtCourse aCourse = new TaughtCourse(c, Year, semester, sectionID);
		// check if the course is in the list of taught courses
		boolean inList = false;
		for (TaughtCourse course : this.coursesTaught) {
			if (course.equals(aCourse)) {
				inList = true;
			}
		}
		if (!inList) // if the course is not in the list of taught courses add
						// it to the list
			this.coursesTaught.add(aCourse);

	}

	// find out the number of courses the teacher teaches in a given semsester
	public int numberOfCoursesInSemester(Integer year, Semester semesterID) {
		int number = 0; // course counter
		for (TaughtCourse course : this.coursesTaught) { // loop through all the
															// courses
			if (course.getSemesterID().equals(semesterID)) { // if one has the
																// semseter
																// given add one
																// to counter
				if (course.getYear() == year) { // if in the same year
					number++;
				}
			}
		}

		return number;
	}

	// return the number of diffrent courses taught. A Course differs if it has
	// a different CourseID
	public int numberTaugthcourses() {
		ArrayList<String> courseIdList = new ArrayList<String>();
		for (TaughtCourse course : this.coursesTaught) { // loop through all
															// course
			// create temo array of all diff courseID
			boolean inList = false;
			for (int i = 0; i < courseIdList.size(); i++) {
				// loop through courseId list to c if the course is already in
				// the list
				if (courseIdList.get(i).equals(course.getCourseID())) {
					inList = true; // the course is in the list
					break; // break from inner for loop because the course is in
							// the list,
					// you dont need to keep checking if its in the list
				}
			}
			// if the course is not in the list already add it to the list
			if (!inList) {
				courseIdList.add(course.getCourseID());
			}
		}

		return courseIdList.size();
	}

	// toString
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append(" ID: ");
		buffer.append(this.ID);
		buffer.append(" Department ID: ");
		buffer.append(this.DepartmentID);
		buffer.append(" Social Security: ");
		buffer.append(this.SocialSecurityNum);
		buffer.append(" Degree: ");
		buffer.append(this.degree);
		buffer.append(" Major ID: ");
		buffer.append(this.majorID);
		buffer.append(" Salary: ");
		buffer.append(this.Salary);
		buffer.append(" Taught Classes: ");
		buffer.append(this.coursesTaught);
		return buffer.toString();

	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

}
