package pharmacy;

import java.io.Serializable;

public class CompanyCodeIndex implements Comparable<CompanyCodeIndex>, Serializable {

	private String companyCode;
	private Long location;
	private boolean isActive;

	// constructor
	public CompanyCodeIndex(String companyCode, Long location) {
		this.companyCode = companyCode;
		this.location = location;
		this.isActive = true;
	}

	// getters and setters
	public String getCompanyCode() {
		return this.companyCode;
	}

	public Long getLocation() {
		return location;
	}

	public void setLocation(Long location) {
		this.location = location;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	// equals
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyCodeIndex other = (CompanyCodeIndex) obj;
		if (companyCode == null) {
			if (other.companyCode != null)
				return false;
		} else if (!companyCode.equals(other.companyCode))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}

	// tostring
	public String toString() {
		return "CompanyCode [companyCode=" + companyCode + ", location="
				+ location + ", isActive=" + isActive + "]";
	}

	// compareTo
	public int compareTo(CompanyCodeIndex other) {
		if (this.companyCode.equals(other.companyCode)) {
			return this.location.compareTo(other.location);
		} else {
			return this.companyCode.compareTo(other.companyCode);
		}
	}
}
