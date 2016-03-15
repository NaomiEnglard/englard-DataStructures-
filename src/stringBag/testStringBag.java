package stringBag;

public class testStringBag {
	public static void main(String[] args){
		StringBagADT test = new StringBagADT(4);
		System.out.println("is empty: " +test.isEmpty());
		System.out.println("is full: " +test.isFull());
		test.insert("one");
		System.out.println("is empty: " +test.isEmpty());
		test.insert("two");
		test.insert("three");
		test.insert("four");
		System.out.println("is empty: " +test.isEmpty());
		System.out.println("is full: " +test.isFull());
		System.out.println(test.toString());
		try {
			System.out.println("remove" +test.remove());
		} catch (EmptyBagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(test.toString());


		//throw array index out of bounds exception
			//test.insert("five");
		test.clear();
		System.out.println("cleared");
		
		System.out.println("is empty: " +test.isEmpty());
		System.out.println("is full: " +test.isFull());

		
		
	}
	
}
