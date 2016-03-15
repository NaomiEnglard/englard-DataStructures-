package bill;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Bill implements Serializable, Comparable<Bill> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int lastId;
	private Integer billId;
	private String vendorName;
	private double amountDue;
	private Calendar dueDate;
	private BillType type;

	// set up based on data
	public Bill(String vendorName, double amountDue, Calendar dueDate, BillType type) {

		this.billId = lastId++;
		this.vendorName = vendorName;
		this.amountDue = amountDue;
		this.dueDate = dueDate;
		this.type = type;
	}

	public static int getLastId() {
		return lastId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Bill(Scanner input) {
		
		this.billId = lastId++;
		this.vendorName = input.next();
		this.amountDue = input.nextDouble();
		 dueDate = Calendar.getInstance();
		  
		this.dueDate.setTime(Date.valueOf(input.next()));
		this.type = BillType.valueOf(input.next());
		 
		 

		input.close();
	}

	public static void setLastId(int lastId) {
		Bill.lastId = lastId;
	}

	public Integer getBillId() {
		return billId;
	}

	// default comparator is by id
	public int compareTo(Bill other) {
		return this.billId.compareTo(other.billId);
	}

	public String getVendorName() {
		return vendorName;
	}

	public double getAmountDue() {
		return amountDue;
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public BillType getType() {
		return type;
	}

	@Override
	//only good for gui
	public String toString() {
		StringBuilder build = new StringBuilder();
		build.append("Bill ID: ");
		build.append(billId);
		build.append("<br>Vender Name: ");
		build.append(vendorName);
		build.append("<br>Amount Due: ");
		build.append(amountDue);
		build.append("<br>Date: ");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		build.append(format1.format(dueDate.getTime()));
		build.append("<br>Bill Type:");
		build.append(type);
		build.append("<br><br>");
		
		return build.toString();
	}

}
