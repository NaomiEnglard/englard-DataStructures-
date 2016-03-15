package School;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pharmacy.InvalidDataException;

public class Department {
	private String departmentID;
	private String departmentName;
	private String location;
	private String phoneNumber;
	private String faxNumber;
	private String departmentChairperson;
	
	//location, phone number, fax number and department chairperson are not required
	public Department(String DepId, String DepName) throws InvalidDataException {
		this(DepId, DepName, null, null, null, null);
	}
	public Department(String DepId, String DepName, String location,
			String phone, String fax, String DepChiarperson)throws InvalidDataException {
		// validate phone and fax
		if (isValidNumber(phone) && isValidNumber(fax)) {
			this.departmentID = DepId;
			this.departmentName = DepName;
			this.location = location;
			this.phoneNumber = phone;
			this.faxNumber = fax;
			this.departmentChairperson = DepChiarperson;
		}
		else
			throw new InvalidDataException();
	}

	public String getDepartmentID() {
		return departmentID;
	}
	private boolean isValidNumber(String number) {
		//phone numbers should be in xxx-xxx-xxxx form and only numeric values or throw error
		if(number ==null){
			return true;
		}
		String expression =  "\\d{10}$";  
       	CharSequence inputStr = number;  
		Pattern pattern = Pattern.compile(expression);  
		Matcher matcher = pattern.matcher(inputStr);  
		if(!matcher.matches()){  //if the pattern matches the region then the phone number is valid format
		  return false;
		}
		return true; //if valid return true
	}

	// seters
	public void setLocation(String location) {
		this.location = location;
	}

	public void setPhoneNumber(String phone) {
		if (isValidNumber(phone)) { //validate
			this.phoneNumber = phone;
		}
	}

	public void setFaxNumber(String fax) {
		if (isValidNumber(fax)) { //validate
			this.faxNumber = fax;
		}
	}

	public void setChairPerson(String chiarPerson) {
		this.departmentChairperson = chiarPerson;
	}

	// getters
	public String getLocation() {
		return this.location;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public String getFaxNumber() {
		return this.faxNumber;
	}

	public String getChairPerson() {
		return this.departmentChairperson;
	}
	//to String
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append(" Department ID: ");
		buffer.append(this.departmentID );
		buffer.append(" , Name: ");
		buffer.append(this.departmentName);
		buffer.append(" , location: ");
		buffer.append(this.location);
		buffer.append(" , Phone Number: ");
		buffer.append(this.phoneNumber);
		buffer.append(" , Fax Number: ");
		buffer.append(this.faxNumber );
		buffer.append(" , Department ChairPerson: ");
		buffer.append(this.departmentChairperson);
		return buffer.toString();
	}
	//Comapre To based on id
	public int compareTo(Department other){
		return other.departmentID.compareTo(this.departmentID);
	}
	//equals based on id
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (departmentID == null) {
			if (other.departmentID != null)
				return false;
		} else if (!departmentID.equals(other.departmentID))
			return false;
		return true;
	}
}
