package School;

import pharmacy.InvalidDataException;

public class TaughtCourse extends Course {
	private String TeacherID;
	private Integer Year;
	private Semester SemesterID;
	private TimeSlot SectionID;
	
	public TaughtCourse (Course course, Integer Year, Semester semesterID, TimeSlot sectionID) throws InvalidDataException{
		super(course.getCourseID(),course.getDescription(),course.getDepartmentID(),course.getCreditNumber());
		this.Year = Year;
		this.SemesterID = semesterID;
		this.SectionID = sectionID;
		//this.TeacherID = ;

	}

	public String getTeacherID() {
		return TeacherID;
	}

	public Integer getYear() {
		return Year;
	}

	public Semester getSemesterID() {
		return SemesterID;
	}

	public TimeSlot getSectionID() {
		return SectionID;
	}
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Taught Course: ");
		buffer.append(super.toString());
		buffer.append("Teacher ID: ");
		buffer.append(this.TeacherID);
		buffer.append("Year: ");
		buffer.append(this.Year);
		buffer.append("Semester ID: ");
		buffer.append(this.SemesterID);
		buffer.append("Section ID: ");
		buffer.append(this.SectionID);
		return buffer.toString();
	}
}
