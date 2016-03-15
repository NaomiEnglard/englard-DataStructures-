package School;


public class Employee extends Person{

	private Integer ID;
	private String HireDate;
	private String DateOfBirth;
	private EmployeeType jobtype; //make enum type once validate it will work
	
	
	//Implement constructors. Note: MidInitial, PhoneNumber, Major  are optional but may be available when setting up a new Employee.
	public Employee(Integer ID, String FirstName, String LastName, Address address, char Gender, String hireDate, String DOB, String jobType){
		this(ID,FirstName,LastName,null,address,null,Gender,hireDate,DOB,jobType);
	}
	public Employee(Integer ID, String FirstName, String LastName, String MidInitial,
			Address address, String PhoneNumber, char Gender, String hireDate, String DOB, String jobType){
	
	super(ID,FirstName,LastName,MidInitial, address,PhoneNumber,Gender);
	this.ID = super.getID();
	//validate 
	// HireDate (mm/dd/yyyy)must be > DateOfBirth and must be reasonable (Employee must be at least 18 years of age), 
	if(validDates(hireDate,DOB)){
	
	
//EmployeeTypeID must be a valid EmployeeT
	boolean	isFound =false;
	for(EmployeeType aType : EmployeeType.values()){
		if(aType.toString().equals(jobType)){
			this.jobtype = aType;
			isFound= true;
			break;
		}
		
	}
	if(!isFound){
		//if not foudn
		throw new InvalidDataException();
		
	}
	//this.ID
	this.HireDate= hireDate;
	this.DateOfBirth =DOB;
	}
	}
	//	Implement setters and getters
	//	EmployeeType may be modified – assuming Employee switch positions

	private boolean validDates(String hireDate, String DOB) {
		String[] token = hireDate.split("/");
		String[] otherToken =DOB.split("/");
		int yearHire = Integer.parseInt(token[2]);
		int yearBorn =Integer.parseInt(otherToken[2]);
		if( yearHire < yearBorn || (yearHire-18)< yearBorn){
			//error the year of hire can not be before year of birth, and you must be 18 before you are hired
         return false;
         }
		return true;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getHireDate() {
		return HireDate;
	}
	public void setHireDate(String hireDate) throws InvalidDateException {
		if(validDates(hireDate, this.DateOfBirth)){
		HireDate = hireDate;
		}
		else{
			throw new InvalidDateException();
		}
	}
	public String getDateOfBirth() {
		
		return DateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		if(validDates(this.HireDate,dateOfBirth)){
		DateOfBirth = dateOfBirth;
		}
	}
	public String getJobtype() {
		return jobtype.toString();
	}
	public void setJobtype(EmployeeType jobtype) {
		this.jobtype = jobtype;
	}
	
	//	Implement toString () method
	public String toString(){
		StringBuffer buff = new StringBuffer();
		buff.append(super.toString());
		buff.append("Job type: ");
		buff.append(this.jobtype);
		buff.append(" Hire Date: ");
		buff.append(this.HireDate);
		//buff.append(" ID: ");
		//buff.append(this.ID);
		buff.append(" Date OF Birth: ");
		buff.append(this.DateOfBirth);
		return buff.toString();
	}

}
