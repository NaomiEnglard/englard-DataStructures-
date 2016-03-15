package doubleLinkedList;

import java.rmi.NoSuchObjectException;

public class DoubleLinkListMain {
	public static void main(String args[]) throws NoSuchObjectException, EmptyListException {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		list.insert(1);
		list.insert(4);
		list.insert(8);
		list.insert(0);
		list.insert(7);
		list.remove(7);

		System.out.println("reverse list");
		RevereseIterator<Integer> reverse = list.getReverseIterator();
		while (reverse.hasNext()) {
			System.out.print(reverse.next() + " ");
		}

		System.out.println("\nlist");
		Iterator<Integer> iter = list.getIterator();
		while (iter.hasNext()) {
			System.out.print(iter.next() + " ");
			
		}

		// test with string
		try {
			DoubleLinkedList<String> stringList = new DoubleLinkedList<String>();
			// stringList.reomveFirst();
			// stringList.removeLast();
			// stringList.removeAll();
			// System.out.println(stringList.isEmpty());
			// stringList.remove("a");

			stringList.insert("b");
			// stringList.reomveFirst();
			// stringList.removeLast();
			// stringList.removeAll();
			// System.out.println(stringList.isEmpty());
			// stringList.remove("a");

			stringList.insert("e");
			stringList.insert("g");
			stringList.insert("a");
			// stringList.reomveFirst();
			// stringList.removeLast();
			// stringList.removeAll();
			//stringList.remove("a");
			stringList.insert("f");
			stringList.remove("e");
			stringList.remove("b");
			stringList.remove("g");
			stringList.remove("a");

			System.out.println("\nreverse list");
			RevereseIterator<String> reverseString = stringList
					.getReverseIterator();
			while (reverseString.hasNext()) {
				System.out.print(reverseString.next().toString() + " ");
			}

			System.out.println("\nlist");
			Iterator<String> iterString = stringList.getIterator();
			while (iterString.hasNext()) {
				System.out.print(iterString.next() + " ");
			}
		} catch (NoSuchObjectException | EmptyListException e) {
			System.out.println("nothing removed");
		}

	}
}