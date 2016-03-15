package School;

import java.util.ArrayList;

import pharmacy.InvalidDataException;

public class Student extends Person {
	private Major major;
	private String enrolledDate;
	private String dateOfBirth;
	private Double GPA;
	private int creditsEarned;
	private String socialSecurityNum;
	private ArrayList<CompletedCourse> courses;

	// consturcor for a student withou midInitial or phone number
	public Student(Integer ID, String FirstName, String LastName,
			Address address, char Gender, Major major, String enrollDate,
			String DOB, String social, ArrayList<CompletedCourse> coursesDone) {
		this(ID, FirstName, LastName, null, address, null, Gender, major,
				enrollDate, DOB, social, coursesDone);
	}

	// constructor if no major
	public Student(Integer ID, String FirstName, String LastName,
			String MidInitail, Address address, String phoneNumber,
			char Gender, String enrollDate, String DOB, String social,
			ArrayList<CompletedCourse> coursesDone) {
		this(ID, FirstName, LastName, null, address, null, Gender, Major.UDCD,
				enrollDate, DOB, social, coursesDone);
	}// constructor for no major and no midName or phoneNumber

	public Student(Integer ID, String FirstName, String LastName,
			Address address, char Gender, String enrollDate, String DOB,
			String social, ArrayList<CompletedCourse> coursesDone) {
		this(ID, FirstName, LastName, null, address, null, Gender, Major.UDCD,
				enrollDate, DOB, social, coursesDone);
	}

	// consturtor with all info
	public Student(Integer ID, String FirstName, String LastName,
			String MidInitail, Address address, String phoneNumber,
			char Gender, Major major, String enrollDate, String DOB,
			String social, ArrayList<CompletedCourse> coursesDone) {
		super(ID, FirstName, LastName, MidInitail, address, phoneNumber, Gender);
		// validate enrollDate aand DOB
		// if both dates are valid or both are null
		if ((DOB == null && enrollDate == null)
				|| (isValidDate(DOB) && isValidDate(enrollDate))) {
			// if(isValidMajor(major)){ //already validated
			this.major = major;
			this.enrolledDate = enrollDate;
			this.dateOfBirth = DOB;
			this.GPA = null;
			this.creditsEarned = 0;
			this.socialSecurityNum = social;
			// if courseDone is null instantiate an empty arraylist
			if (coursesDone == null) {
				this.courses = new ArrayList<CompletedCourse>();
			} else {
				this.courses = coursesDone;
			}
		}
	}

	private boolean isValidDate(String date) {
		String[] token = date.split("/");
		int month = Integer.parseInt(token[0]);
		int day = Integer.parseInt(token[1]);
		int year = Integer.parseInt(token[2]);
		if (month > 0 && month <= 12 && day > 0 && day <= 31 && year > 0) {
			return true;
		}
		return false;
	}

	private Major isValidMajor(String major) {
		for (Major aMajor : Major.values()) {
			if (aMajor.toString().equalsIgnoreCase(major)) {
				return aMajor;
			}
		}
		return null;
	}

	// getters and setters
	public String getMajor() {
		return this.major.toString();
	}

	public void setMajor(String major) {
		this.major = isValidMajor(major);
	}

	public Double getGPA() {
		return GPA;
	}

	public void setGPA(Double gPA) {
		if (gPA < 4.0 && gPA > 0) // validation
			GPA = gPA;
	}

	public int getCreditsEarned() {
		return creditsEarned;
	}

	public void setCreditsEarned(int creditsEarned) {
		if (creditsEarned > 0)
			this.creditsEarned = creditsEarned;
	}

	public Integer getID() {
		return super.getID();
	}

	// to String
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("Student: Major: ");
		buffer.append(this.major);
		buffer.append(" Enrolled Date: ");
		buffer.append(this.enrolledDate);
		buffer.append(" Date Of Birth: ");
		buffer.append(this.dateOfBirth);
		buffer.append(" GPA: ");
		buffer.append(this.GPA);
		buffer.append(" Credits Earned: ");
		buffer.append(this.creditsEarned);
		buffer.append("Social Security Number: ");
		buffer.append(this.socialSecurityNum);
		buffer.append(" Courses Completed ");
		buffer.append(this.courses);
		return buffer.toString();

	}

	// add course to course complted list
	public void completeCourse(Course course, Grade agrade) throws InvalidDataException {
		this.courses.add(new CompletedCourse(course, agrade, this.getID()));
		// recalulate credits earned
		this.creditsEarned += this.courses.get(this.courses.size() - 1)
				.getCreditNumber();
		if (GPA == null) { // if gpa is not set then make it 0
			GPA = 0.0;
		}
		this.GPA = (GPA + agrade.getGradeValue()) / this.courses.size();

	}

	public ArrayList<CompletedCourse> getCourses() {
		ArrayList<CompletedCourse> copy = new ArrayList<CompletedCourse>();
		for(CompletedCourse cC : this.courses){
			copy.add(cC);
		}
		return copy;
	}

	// find in list of completed courses a spacific course or throw exception if
	// course is not in the list
	public CompletedCourse findCompletedCourse(String courseID)
			throws NotFoundException, InvalidDataException {
		CompletedCourse copyCourse = null;
		boolean found = false;
		if (this.courses == null) { // if no courses in list
			throw new NotFoundException();
		}
		for (Course c :this.courses) {
			if (c instanceof CompletedCourse) {
				if (c.getCourseID().equalsIgnoreCase(courseID)) {
					found = true;
					copyCourse = new CompletedCourse(c, ((CompletedCourse) c).getGrade(),this.getID());
				}
			}
			if (!found) {
				throw new NotFoundException();
			}
		}
		return copyCourse;
	}

	public Grade getGradeofCourse(String courseID) {
		if (this.courses == null) { // if there is nothing in the list return an
									// null
			return null; // cannot return later since for loop will throw null
							// pionter
		}

		for (CompletedCourse aCourse : this.courses) {
			if (aCourse.getCourseID().equals(courseID)) {
				return aCourse.getGrade();
			}
		}
		return null;
	}

	public ArrayList<CompletedCourse> getCoursesbyDepartment(String departmentID) throws InvalidDataException {
		ArrayList<CompletedCourse> copyList = new ArrayList<CompletedCourse>();
		if (this.courses == null) { // if there is nothing in the list return an
									// empty copy
			return copyList; // cannot return later since for loop will throw
								// null pionter
		}

		for (CompletedCourse aCourse : this.courses) {
			if (aCourse.getDepartmentID().equals(departmentID)) {
				copyList.add(new CompletedCourse(aCourse, aCourse.getGrade(), this.getID()));
			}
		}
		return copyList;
	}

	public ArrayList<CompletedCourse> getCoursesbyGrade(Grade g) throws InvalidDataException {
		ArrayList<CompletedCourse> copy = new ArrayList<CompletedCourse>();
		if (this.courses == null) { // if there is nothing in the list return an
									// empty copy
			return copy; // cannot return later since for loop will throw null
							// pionter
		}
		for (CompletedCourse aCourse : this.courses) {
			if (aCourse.getGrade().equals(g)) {
				copy.add(new CompletedCourse(aCourse, aCourse.getGrade(), this.getID()));
			}
		}
		return copy;
	}
}
