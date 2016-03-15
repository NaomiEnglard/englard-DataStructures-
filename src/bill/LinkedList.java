package bill;

import java.io.Serializable;

import School.NotFoundException;

public class LinkedList<T extends Serializable & Comparable<T>> implements
		Serializable {
	private static final long serialVersionUID = 1l;
	protected LLNode<T> head;
	public LinkedListInternalIterator iter; // internal iterator, only one per

	// empty linked list
	public LinkedList() {
		head = null;
		iter = new LinkedListInternalIterator();
	}

	// insert data at the beginning
	// doesn't check for duplicates

	public void insert(T data) throws Exception {
		if (head == null) {
			// this data will be entered into first LLNode
			head = new LLNode<T>(data);
		} else {
			// insert new LLNode and head of list
			// have the new LLNode point to the previous head of list
			LLNode<T> aLLNode = new LLNode<T>(data);
			aLLNode.setNext(head);
			head = aLLNode;
		}
	}

	// remove LLNode that contains the data specified
	public void remove(T data) throws ListEmptyException, NotFoundException {
		LLNode<T> currLLNode;
		LLNode<T> prevLLNode;
		if (head == null)
			throw new ListEmptyException();
		else {
			// find the LLNode with the data in it
			// adjust the links so the LLNode is removed from the chain of
			// LLNodes
			currLLNode = head;
			prevLLNode = head;
			while (currLLNode != null) {
				if (currLLNode.getData().compareTo(data) == 0) {
					// found the data we are looking for
					if (currLLNode == head) {
						// reset head and you're done!
						head = head.getNext();
						return;

					} else {
						prevLLNode.setNext(currLLNode.getNext());
						return;

					}
				} else {
					// didn't find it yet, continue on to next LLNode
					prevLLNode = currLLNode;
					currLLNode = currLLNode.getNext();
				}

			}
			// exhausted list, didn't find a match
			throw new NotFoundException();
		}

	}

	public void removeAll() {
		head = null;
	}

	public LLNode<T> getFirst() {
		// return first LLNode in the list
		return head;
	}

	public boolean isEmpty() {
		// is list empty
		return (head == null);
	}

	public String toString() {
		LLNode<T> currLLNode = head.getNext();
		String info = null;
		if (head == null)
			return " ";
		else
			info = head.getData().toString();
		while (currLLNode != null) {
			info = info + " " + currLLNode.getData().toString();
			currLLNode = currLLNode.getNext();
		}
		return info;
	}

	// this class gives provides the internal iterator with its functionality

	class LinkedListInternalIterator implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private LLNode<T> currentLLNode;

		public LinkedListInternalIterator() {
			currentLLNode = head;

		}

		public boolean hasNext() {
			// is there more data to peruse
			if (currentLLNode == null) {
				return false;
			}
			if (currentLLNode.getData() != null) {
				return true;
			} else {
				return false;
			}
		}

		public T next() {
			LLNode<T> temp = currentLLNode;
			currentLLNode = currentLLNode.getNext();// move further along in the
			// list
			// return the currentLLNode before you moved further along
			return temp.getData();
		}

		public void reset() {
			// resets from the beginning of the list , starts all over again
			currentLLNode = head;
		}
	}
}
