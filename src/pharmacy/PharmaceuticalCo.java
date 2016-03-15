package pharmacy;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.RandomAccess;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import School.NotFoundException;

public class PharmaceuticalCo {
	
	private String companyCode;
	private String companyName;
	private String phoneNumber;

	public PharmaceuticalCo(String companyCode, String phone, String name) throws InvalidDataException {
		this.companyName = name;
		this.companyCode = companyCode;
		if (isValid(phone)) {
			this.phoneNumber = phone;
		} else {
			throw new InvalidDataException();
		}
	}

	public PharmaceuticalCo(Scanner filename) throws InvalidDataException {
		// read the next three strings from a text file and construct an
		// instance of PharmaceuticalCo
		this(filename.next(), filename.next(), filename.nextLine());
	}

	public PharmaceuticalCo(RandomAccessFile raFile, Long location)
			throws IOException {
		raFile.seek(location);
		// this(raFile.readUTF(), raFile.readUTF(), raFile.readUTF());
		this.companyCode = raFile.readUTF();
		this.phoneNumber = raFile.readUTF();
		this.companyName = raFile.readUTF();
	}

	// getters and setters
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		if (isValid(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		}
	}

	// validation
	private boolean isValid(String phoneNumber) {
		String expression = "\\d{10}";
		CharSequence inputStr = phoneNumber;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		if (!matcher.matches()) { // if the pattern does not matches the region
									// then the phone number is valid format
			return false;
		}
		return true; // if valid return false
	}

	// random access file
	public void writeToFile(RandomAccessFile raFile, Long location)
			throws IOException {
		// companyCode - fixed length field of 4 characters
		// company name – fixed length field of 25 characters
		// telephone number – fixed length field of 10 characters.
		raFile.seek(location);
		raFile.writeUTF(String.format("%-4s", this.companyCode));
		raFile.writeUTF(String.format("%-10s", this.phoneNumber));
		raFile.writeUTF(String.format("%-10s", this.companyName));

	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[companyCode= ");
		buffer.append(this.companyCode);
		buffer.append(" companyName= ");
		buffer.append(this.companyName);
		buffer.append(" phoneNumber= ");
		buffer.append(this.phoneNumber);
		buffer.append("]");
		return buffer.toString();
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PharmaceuticalCo other = (PharmaceuticalCo) obj;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}

	public int compareTo(PharmaceuticalCo other) {
		return other.getPhoneNumber().compareTo(this.getPhoneNumber());
	}
}

	


