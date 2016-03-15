package pharmacy;

import java.io.Serializable;

public class CompanyNameIndex implements Comparable<CompanyNameIndex>,
		Serializable {
	private String companyName;
	private Long location;
	private boolean isActive;

	// constructor
	public CompanyNameIndex(String companyName, Long location) {
		this.companyName = companyName;
		this.location = location;
		this.isActive = true;
	}

	// getters and setters

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getLocation() {
		return location;
	}

	public void setLocation(Long location) {
		this.location = location;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	// equals compateTo an ToSting

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyNameIndex other = (CompanyNameIndex) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}

	public int compareTo(CompanyNameIndex other) {

		return this.companyName.compareToIgnoreCase(other.companyName);
	}

	public String toString() {
		return "CompanyCodeIndex [companyName=" + companyName + ", location="
				+ location + ", isActive=" + isActive + "]";
	}

}
