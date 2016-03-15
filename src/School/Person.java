package School;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private Integer ID;
	private String FirstName;
	private String LastName; 
	private String MidInitial;
	private Address address;
	private String PhoneNumber;
	private char Gender;
	
	//mid initila and phone number are not required
	public Person(Integer ID, String FirstName, String LastName, Address address, char Gender){
		this(ID,FirstName,LastName,null,address, null, Gender);
	}
	
	public Person(Integer ID, String FirstName, String LastName, String MidInitial, Address address, String PhoneNumber, char Gender){
		if(isValid(PhoneNumber)){
		this.ID=ID;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.MidInitial= MidInitial;
		this.address=address;
		this.PhoneNumber =PhoneNumber;
		this.Gender = Gender;
		}
	}
	private boolean isValid(String number) {
		if(number == null){
			return true;
		}
		String expression =  "\\d{10}";
       	CharSequence inputStr = number;  
		Pattern pattern = Pattern.compile(expression);  
		Matcher matcher = pattern.matcher(inputStr);  
		if(!matcher.matches()){  //if the pattern does not matches the region then the phone number is valid format
		  return false;
		}
		return true; //if valid return false
	}
	public Integer getID() {
		return ID;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		if(isValid(phoneNumber))
			PhoneNumber = phoneNumber;
	}
	public String getFirstName(){
		return this.FirstName;
	}
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nID: "); //get type of person
		buffer.append(this.ID);
		buffer.append(" Name: ");
		buffer.append(this.FirstName );
		if(this.MidInitial !=null){
			buffer.append (" " + this.MidInitial);
		}
		buffer.append(" " +this.LastName);
		buffer.append(" ADDRESS ");
		buffer.append(this.address);
		buffer.append(" Phone Number: ");
		buffer.append(this.PhoneNumber);
		buffer.append(" Gender: ");
		buffer.append(this.Gender);
		buffer.append(" ");
		
		return buffer.toString();
	}
	//compare based on id
	public int compateTo(Person other){
		return other.ID.compareTo(this.ID);
	}
	//equals method based on id
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.getID()))
			return false;
		return true;
	}
	
}
