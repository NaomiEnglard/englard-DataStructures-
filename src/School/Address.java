package School;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Address {
	private String street;
	private String city;
	private String zip;
	private StateAbreviations state;

	public Address(String street, String city, String state, String zip) {
		boolean isState = false;
		// validate state
		StateAbreviations theState = null;
		for (StateAbreviations anAbreviation : StateAbreviations.values()) {
			//if it is a valid abrevation or a valid state name then convert to abrevitation

			if (anAbreviation.toString().equalsIgnoreCase(state) || anAbreviation.getStateName().equalsIgnoreCase(state)){
				theState = anAbreviation;
				isState = true;
				break; // once its true stop looping
			}
		}
		// verify zip
		if (isValidZip(zip)&& isState){
			this.street = street;
			this.city = city;
			this.state = theState;
			this.zip = zip;
		} else
			throw new InvalidDataException();
	}

	private boolean isValidZip(String zip) {
		if (zip == null) {
			return true;
		}
		// 5 0r 9 Numeric digits
		String expression = "(\\d{5})(\\d{4})?$";
		CharSequence inputStr = zip;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		if (!matcher.matches()) { // if the pattern does not matches the region then the
									// zip is valid format
			return false;
		}
		return true; // if valid return
	}

	// setters
	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setState(StateAbreviations state) {
		this.state = state;
	}

	// getters
	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getZip() {
		return zip;
	}

	public String getState() {
		return state.toString();
	}

	// toString
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" Street: ");
		buffer.append(this.street);
		buffer.append(" City: ");
		buffer.append(this.city);
		buffer.append(" State: ");
		buffer.append(this.state);
		buffer.append(" Zipcode: ");
		buffer.append(this.zip);
		return buffer.toString();
	}

}
