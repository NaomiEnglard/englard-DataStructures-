package binaryTree;

import pharmacy.DuplicateDataException;

public class UseBinaryTree {

	public static void main(String[] args) {
		BinaryTree<String> myData = new BinaryTree<String>();
		try {
			myData.insert("k");
			myData.insert("l");
			myData.insert("c");
			myData.insert("d");
			myData.insert("j");
			myData.insert("e");
			myData.insert("b");
			myData.insert("f");
			myData.insert("a");
			myData.insert("i");
			myData.insert("m");
			myData.insert("n");
			myData.insert("g");
			myData.insert("h");
			myData.traversePreOrder();
			myData.balanceTree();
			myData.traversePreOrder();
		} catch (DuplicateDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(myData.hasData("potatoes"));
			System.out.println(myData.hasData("onions"));

			System.out.println(myData.hasData("squash"));

			System.out.println(myData.hasData("egg"));
			System.out.println(myData.hasData("stringbeans"));
			System.out.println("should be same");

			// System.out.println(myData.remove("egg"));
			System.out.println(myData.remove("potatoes"));

			System.out.println(myData.remove("onions"));
			System.out.println(myData.remove("squash"));
			System.out.println(myData.remove("egg"));
			System.out.println(myData.remove("stringbeans"));
			System.out.println(myData.remove("stringbeans"));

		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
