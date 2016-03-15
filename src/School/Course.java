package School;

import pharmacy.InvalidDataException;

public class Course {
	private String courseID;
	private String description;
	private String departmentID;
	private int creditNumber;
	
	public Course(String courseID,String description,String departmentID, int credits) throws InvalidDataException{
		//validate 
		if(credits>0 && credits<=4){
		this.description = description;
		this.courseID = courseID;
		this.departmentID = departmentID;
		this.creditNumber = credits;
		}else
			throw new InvalidDataException();
	}
	//getters
	public String getCourseID() {
		return courseID;
	}

	public String getDescription() {
		return description;
	}

	public String getDepartmentID() {
		return departmentID;
	}

	public int getCreditNumber() {
		return creditNumber;
	}
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append( "\n COURSE  Descrition: ");
		buffer.append(this.description);
		buffer.append(" ID: ");
		buffer.append(this.courseID);
		buffer.append(" Department ID: ");
		buffer.append(this.departmentID);
		buffer.append(" credits: ");
		buffer.append(this.creditNumber);
		return buffer.toString();
	}
	public int compareTo(CompletedCourse aCourse){
		return aCourse.getCourseID().compareTo(this.courseID);
	}
	//equals method based on id
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseID == null) {
			if (other.courseID != null)
				return false;
		} else if (!courseID.equalsIgnoreCase(other.courseID))
			return false;
		return true;
	}
	
}
