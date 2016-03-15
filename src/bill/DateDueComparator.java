package bill;

import java.util.Comparator;

public class DateDueComparator implements Comparator<Bill>{

	
	public int compare(Bill one, Bill two) {
		return one.getDueDate().compareTo(two.getDueDate());
	}

}
