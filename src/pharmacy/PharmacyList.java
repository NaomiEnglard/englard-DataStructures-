package pharmacy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import School.NotFoundException;

public class PharmacyList {
	private ArrayList<CompanyCodeIndex> companyCoderefrence;
	private ArrayList<CompanyNameIndex> companyNameRefrence;
	private RandomAccessFile theFile;

	public PharmacyList() throws FileNotFoundException {
		this.companyCoderefrence = new ArrayList<CompanyCodeIndex>();
		this.companyNameRefrence = new ArrayList<CompanyNameIndex>();

	}

	public void connectData(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		this.theFile = new RandomAccessFile(file, "rw");
	}

	public void addCompany(String companyCode, String phoneNumber,
			String companyName) throws DuplicateDataException, IOException, InvalidDataException {
		// make sure that the company code and name are not in the list already
		// if they are not add them
		// if they are throw exception

		for (CompanyCodeIndex cc : this.companyCoderefrence) {
			if (cc.getCompanyCode().equalsIgnoreCase(companyCode)) {
				// check if they have the same name - get location
				Long location = cc.getLocation();
				for (CompanyNameIndex cci : this.companyNameRefrence) {
					if (cci.getLocation() == location) { // if they have the
															// same location
						if (cci.getCompanyName().equalsIgnoreCase(companyName)) {
							throw new DuplicateDataException();

						}
					}
				}
			}
		}
		// instantiate instance of pharmeceuticalCo
		PharmaceuticalCo pC = new PharmaceuticalCo(companyCode, phoneNumber,
				companyName);
		// write out the instance to RAF
		Long location = theFile.length();
		pC.writeToFile(theFile, location);
		// Set up 2 index records CompanyCodeIndex and CompanyNameIndex ,
		// each referencing the same location in the RAF
		this.companyCoderefrence.add(new CompanyCodeIndex(companyCode, location));
		this.companyNameRefrence.add(new CompanyNameIndex(companyName, location));

	}

	public PharmaceuticalCo findCompanyCode(String companyCode)
			throws NotFoundException, IOException {
		boolean found = false;
		// Search through the ArrayList of CompanyCodeIndex references
		for (CompanyCodeIndex aComany : this.companyCoderefrence) {
			if (aComany.getCompanyCode().equalsIgnoreCase(companyCode)) {
				found = true;
				// test if active
				if (!aComany.getIsActive()) {
					throw new NotFoundException();
				}
				// If found, retrieve location of that record,
				Long location = aComany.getLocation();

				// instantiate a PharmaceuticalCo record from the
				// RandomAccessFile
				PharmaceuticalCo pc = new PharmaceuticalCo(this.theFile,
						location);

				// return the record
				return pc;
			}
		}
		// If not found, through NotFoundException
		if (!found) {
			throw new NotFoundException();
		}
		return null;
	}

	public PharmaceuticalCo findCompanyName(String companyName)
			throws IOException, NotFoundException {
		boolean found = false;
		// Search through the ArrayList of CompanyNameIndex references
		for (CompanyNameIndex cCI : this.companyNameRefrence) {
			if (cCI.getCompanyName().equalsIgnoreCase(companyName)) {
				found = true;
				// retrieve location of that record
				Long location = cCI.getLocation();
				// instantiate a PharmaceuticalCo record from the
				// RandomAccessFile
				PharmaceuticalCo pC = new PharmaceuticalCo(this.theFile,
						location);

				// return the record
				return pC;
			}
		}

		// if not found
		if (!found) {
			throw new NotFoundException();
		}
		return null;
	}

	public void modifyCompanyPhone(String companyCode, String newNumber)
			throws NotFoundException, IOException {
		boolean found = false;
		// Search through the ArrayList of CompanyCodeIndex references
		for (CompanyCodeIndex cCI : this.companyCoderefrence) {
			if (cCI.getCompanyCode().equalsIgnoreCase(companyCode)) {

				// If found, but no longer active , throw NotFoundException
				if (!cCI.getIsActive()) {
					throw new NotFoundException();
				}
				// If found,
				found = true;
				// retrieve location of that record,
				Long location = cCI.getLocation();
				// instantiate a PharmaceuticalCo record from the
				// RandomAccessFile,
				PharmaceuticalCo pC = new PharmaceuticalCo(this.theFile,
						location);
				// invoke the PharmaceuticalCo setPhoneNumber(newNumber)
				pC.setPhoneNumber(newNumber);

				// Invoke the PharmaceuticalCo.writeToFile() to correct location
				// within the file ,
				// replacing the current record of that company
				pC.writeToFile(theFile, location);
				break;
			}
		}
		if (!found) {
			// If not found, through NotFoundException
			throw new NotFoundException();
		}

	}

	public void removeCompany(String name) throws FileNotFoundException {
		boolean found = false;
		for (CompanyNameIndex cCI : this.companyNameRefrence) {
			String hold = cCI.getCompanyName().trim();
			
			if (hold.equalsIgnoreCase(name)) {
				//if its inactive then it was already removed 
				if(!cCI.isActive()){
					throw new FileNotFoundException();
				}
				cCI.setActive(false);
				found = true;
				cCI.getLocation();
				for (CompanyCodeIndex cC : this.companyCoderefrence) {
					if (cC.getLocation() == (cCI.getLocation())) {
						cC.setActive(false);
					}
				}
			}
		}
		if (!found) {
			throw new FileNotFoundException();
		}
	}

	
	public String infoOnFile() throws IOException, NotFoundException {
		StringBuffer buffer = new StringBuffer();
		Collections.sort(this.companyNameRefrence);
		PharmaceuticalCo printer;
		for (CompanyNameIndex cCI : this.companyNameRefrence) {
			 printer = null;
			printer = this.findCompanyName(cCI.getCompanyName());
			buffer.append(printer);
		}

		return buffer.toString();
	}

}
