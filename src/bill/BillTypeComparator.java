package bill;

import java.util.Comparator;

public class BillTypeComparator implements Comparator<Bill>{
@Override
	public int compare(Bill one, Bill two) {
		return one.getType().compareTo(two.getType());
	}

}
