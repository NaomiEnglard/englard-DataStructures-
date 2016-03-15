package School;

import java.util.GregorianCalendar;

import pharmacy.InvalidDataException;


public class CompletedCourse extends Course{
	private Integer studentId;
	private Grade grade; 
	private GregorianCalendar dateCompleted;
	
	//constructor
	public CompletedCourse(Course aCourse, Grade courseGrade, Integer id) throws InvalidDataException{
		super(aCourse.getCourseID(),aCourse.getDescription(),aCourse.getDepartmentID(),aCourse.getCreditNumber());
		//this.studentId=studentId;
		this.grade = courseGrade;
		this.studentId =id;
		GregorianCalendar today = new GregorianCalendar();
		this.dateCompleted = today; 
		
	}

	public Integer getStudentId() {
		return studentId;
	}

	public Grade getGrade() {
		return grade;
	}
	
}
	
	
