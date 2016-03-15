package bill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Scanner;

import bill.LinkedList.LinkedListInternalIterator;
import School.NotFoundException;
import pharmacy.DuplicateDataException;

public class BillOrganizer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PriorityQueue<Bill> dateQueue;
	private PriorityQueue<Bill> billTypeQueue;
	private PriorityQueue<Bill> amountQueue;
	private SortedLinkedList<Bill> idList;

	public BillOrganizer() {
		this.dateQueue = new PriorityQueue<Bill>(new DateDueComparator());
		this.billTypeQueue = new PriorityQueue<Bill>(new BillTypeComparator());
		this.amountQueue = new PriorityQueue<Bill>(new AmountDueComparator());
		this.idList = new SortedLinkedList<Bill>();

	}

	public BillOrganizer(String fileName) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(
				fileName));
		this.idList = (SortedLinkedList<Bill>) input.readObject();
		input.close();
		// restore all the queues
		this.dateQueue = new PriorityQueue<Bill>(new DateDueComparator());
		this.billTypeQueue = new PriorityQueue<Bill>(new BillTypeComparator());
		this.amountQueue = new PriorityQueue<Bill>(new AmountDueComparator());
		idList.iter.reset();
		int counter = 1;
		while (idList.iter.hasNext()) {
			Bill restoreBill = idList.iter.next();
			restoreBill.setBillId(counter); //change bill id to be counter
			this.dateQueue.enqueue(restoreBill);
			this.amountQueue.enqueue(restoreBill);
			this.billTypeQueue.enqueue(restoreBill);
			counter++;
		}
		Bill.setLastId(counter);

	}

	// add for read from regular file - with scanner
	public BillOrganizer(Scanner input) throws EmptyScanner,
			DuplicateDataException {
		// instaniate everthing if its the first scanner object
		if (billTypeQueue == null) {
			this.dateQueue = new PriorityQueue<Bill>(new DateDueComparator());
			this.billTypeQueue = new PriorityQueue<Bill>(
					new BillTypeComparator());
			this.amountQueue = new PriorityQueue<Bill>(
					new AmountDueComparator());
			this.idList = new SortedLinkedList<Bill>();
		}
		if (input.hasNext()) {
			dateQueue.enqueue(new Bill(input));
			billTypeQueue.enqueue(new Bill(input));
			amountQueue.enqueue(new Bill(input));
			idList.insert(new Bill(input));

		} else {
			throw new EmptyScanner();
		}

	}

	public void insert(Bill bill) throws DuplicateDataException {
		this.dateQueue.enqueue(bill);
		this.amountQueue.enqueue(bill);
		this.billTypeQueue.enqueue(bill);
		this.idList.insert(bill);
	}

	public Bill payNextBill(BillCriteria criteria) throws ListEmptyException,
			NotFoundException {
		Bill payed = null;
		if (criteria.equals(BillCriteria.BILLTYPE)) {
			payed = this.billTypeQueue.dequeue();
			this.dateQueue.remove(payed);
			this.amountQueue.remove(payed);
			this.idList.remove(payed);
		} else if (criteria.equals(BillCriteria.BILLAMOUNT)) {
			payed = this.amountQueue.dequeue();
			this.dateQueue.remove(payed);
			this.billTypeQueue.remove(payed);
			this.idList.remove(payed);
		} else if (criteria.equals(BillCriteria.BILLDUEDATE)) {
			payed = this.dateQueue.dequeue();
			this.billTypeQueue.remove(payed);
			this.amountQueue.remove(payed);
			this.idList.remove(payed);

		}
		return payed;
	}

	public Bill payBill(int id) throws ListEmptyException, NotFoundException {
		Bill payed = null;
		payed = find(id);
		if (payed == null){
			throw new NotFoundException();
		}
		this.idList.remove(payed);
		this.dateQueue.remove(payed);
		this.billTypeQueue.remove(payed);
		this.amountQueue.remove(payed);

		return payed;
	}

	public double totalBill() {
		double total = 0;
		this.idList.iter.reset();
		LinkedListInternalIterator iter = this.idList.iter;
		// LinkedListInternalIterator iter = this.iteratorByDate();
		while (iter.hasNext()) {
			total += ((Bill) iter.next()).getAmountDue();
		}
		return total;
	}

	public String toString() {
		StringBuilder bills = new StringBuilder();
		while (this.idList.iter.hasNext()) {
			bills.append(this.idList.iter.next());
			bills.append("\n");

		}
		return bills.toString();
	}

	public String toString(LinkedListInternalIterator iter) {
		StringBuilder bills = new StringBuilder();
		while (iter.hasNext()) {
			bills.append(iter.next());
			bills.append("\n");
		}

		return bills.toString();

	}
	public Bill find(int id) {
		
		LinkedListInternalIterator iter = this.idList.iter;
		iter.reset();
		while (iter.hasNext()) {
			Bill temp = (Bill) iter.next();
			if (temp.getBillId() == id) {
				return temp;
			}
			
		}
		return null; // no node found

	}

	public LinkedListInternalIterator iteratorByDate() {
		return this.dateQueue.iterator();
	}

	public LinkedListInternalIterator iteratorByAmount() {
		return this.amountQueue.iterator();
	}

	public LinkedListInternalIterator iteratorByBillType() {
		return this.billTypeQueue.iterator();
	}

	public void closeOrginizer() throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				"BillOrginizer.ser"));
		out.writeObject(this.idList);
		out.close();

	}

}
