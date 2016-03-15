package bill;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

import School.NotFoundException;
import pharmacy.DuplicateDataException;

public class SortedLinkedList<T extends Serializable & Comparable<T>> extends
		LinkedList<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SortedLinkedList() {
		super(); // call super constructor
	}

	public void insert(T data) throws DuplicateDataException {
		LLNode<T> currentNode;
		LLNode<T> prevNode;
		if (super.head == null) { // the first Node in the list points to the
									// student index record
			super.head = new LLNode<T>(data);
		} else {
			LLNode<T> addNode = new LLNode<T>(data); // allocate a new Node
			// find the right spot for the Node inside the list
			currentNode = prevNode = super.head;

			while (currentNode != null
					&& data.compareTo(currentNode.getData()) > 0) {
				prevNode = currentNode;
				currentNode = currentNode.getNext(); // move along to next Node
			}
			// check to make sure its not a duplicate
			if (prevNode.getData().compareTo(data) == 0) {
				throw new DuplicateDataException();
			}
			// found the right place , attach the links
			if (currentNode == head) {
				// the new node will become the new head and point to the
				// current head
				addNode.setNext(head); // head and super.head r the same
				head = addNode;
			} else { // goes in between other node or at the end of the list
				prevNode.setNext(addNode);
				addNode.setNext(currentNode);
			}

		}
	}

	public void insert(T data, Comparator<T> comparator)  {

		LLNode<T> currentNode;
		LLNode<T> prevNode;
		if (super.head == null) { // the first Node in the list points to the
									// student index record
			super.head = new LLNode<T>(data);
		} else {
			LLNode<T> addNode = new LLNode<T>(data); // allocate a new Node
			// find the right spot for the Node inside the list
			currentNode = prevNode = super.head;

			while (currentNode != null
					&& comparator.compare(data, currentNode.getData()) > 0) {
				prevNode = currentNode;
				currentNode = currentNode.getNext(); // move along to next Node
			}
			
			// found the right place , attach the links
			if (currentNode == head) {
				// the new node will become the new head and point to the
				// current head
				addNode.setNext(head); // head and super.head r the same
				head = addNode;
			} else { // goes in between other node or at the end of the list
				prevNode.setNext(addNode);
				addNode.setNext(currentNode);
			}

		}
	}

	public LLNode<T> find(T data) {

		LLNode<T> currentNode = super.head;
		while (currentNode != null) {
			if (currentNode.getData().compareTo(data) == 0) {
				return currentNode;
			}
			currentNode = currentNode.getNext(); // move along to next Node
		}
		return null; // no node found

	}
	
	

}
