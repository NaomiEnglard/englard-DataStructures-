package bill;

import java.util.Comparator;

public class AmountDueComparator implements Comparator<Bill> {

	
	public int compare(Bill one, Bill two) {
		double oneDue= one.getAmountDue();
		double twoDue = two.getAmountDue();
		if(oneDue >twoDue){
			return 1;
		}else if(oneDue <twoDue){
			return -1;
		}else{
			//if equal
			return 0;
		}
	}

}
