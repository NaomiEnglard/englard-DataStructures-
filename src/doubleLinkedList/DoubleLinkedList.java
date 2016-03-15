package doubleLinkedList;

import java.io.Serializable;
import java.rmi.NoSuchObjectException;

public class DoubleLinkedList<T extends Serializable & Comparable<T>> {
	private DoubleLinkNode<T> head;
	private DoubleLinkNode<T> tail;

	public DoubleLinkedList(T data) {
		head = new DoubleLinkNode<T>(data);
		tail = head;

	}

	// empty linked list
	public DoubleLinkedList() {
		this(null);
	}

	/*
	 * add to end of list
	 */
	public void insert(T data) {
		DoubleLinkNode<T> addNode = new DoubleLinkNode<T>(data);

		if (head.getData() == null) { // first node
			head = addNode;
			tail = head;
		} else if (head.getNext() == null) { // second node
			// if the new data should come before the head
			if (addNode.compareTo(head) < 0) {
				addNode.setNext(head);
				head.setPrev(addNode);
				tail = head;
				head = addNode;

			} else {
				addNode.setPrev(head);
				head.setNext(addNode);
				tail = addNode;
			}
		} else { // all other nodes
			// if the new data belongs at the end
			if (addNode.compareTo(tail) > 0) {
				addNode.setPrev(tail);
				tail.setNext(addNode);
				tail = addNode;
			}// if new data belongs at head
			else if (addNode.compareTo(head) < 0) {
				addNode.setNext(head);
				head.setPrev(addNode);
				head = addNode;

			} else {
				// fint location it should be
				DoubleLinkNode<T> temp = head;
				while (temp != null) {
					// find the spot that it should go b4
					if (addNode.compareTo(temp) < 0) {
						addNode.setNext(temp);
						addNode.setPrev(temp.getPrev());
						temp.getPrev().setNext(addNode);
						temp.setPrev(addNode);
						break;
					} else {
						// check the next node
						temp = temp.getNext();
					}

				}
			}
		}
	}

	public T removeFirst() throws EmptyListException {
		if (head.getData() == null) {
			throw new EmptyListException();
		}
		T data = head.getData();
		if (head.getNext() != null) {
			head = head.getNext();
			head.setPrev(null);
		} else {
			// if there is no next set the head to null
			head = new DoubleLinkNode<T>();
		}
		return data;
	}

	public T removeLast() throws EmptyListException {
		if (head.getData() == null) {
			throw new EmptyListException();
		}

		T data = tail.getData();
		if (tail.getPrev() != null) {
			tail = tail.getPrev();
			tail.setNext(null);
		} else {
			// if you remove the head set head to empty
			head = new DoubleLinkNode<T>();
		}
		return data;
	}

	/*
	 * remove node with specific data
	 */
	public void remove(T data) throws EmptyListException, NoSuchObjectException {
		boolean removed = false;
		//if empty list
		if(head.getData() == null){
			throw new EmptyListException();
		}
		//if its the head
		if(head.getData().equals(data)){
			removeFirst();
			removed = true;
		}
		//if its the tail
		else if(tail.getData().equals(data)){
			removeLast();
			removed = true;
		}else{
			DoubleLinkNode<T> currentNode = head.getNext();
			while (currentNode != null) {
			if (currentNode.getData().equals(data)) {
				currentNode.getPrev().setNext(currentNode.getNext());
				currentNode.getNext().setPrev(currentNode.getPrev());
				removed = true;
				return;
			} else {
				currentNode = currentNode.getNext();
			}

		}
		}
		if(!removed){
			throw new NoSuchObjectException(null);
		}
	}

	public void removeAll() {
		head = new DoubleLinkNode<T>();
	}

	public boolean isEmpty() {
		return head.getData() == null;
	}

	// toString
	public String toString() {
		StringBuilder build = new StringBuilder();
		DoubleLinkNode<T> currentNode = head;
		while (currentNode != null) {
			build.append(currentNode);
			build.append(" ");
			currentNode = currentNode.getNext();
		}
		return build.toString();
	}

	// return external iterator
	public RevereseIterator<T> getReverseIterator() {
		return new RevereseIterator<T>(tail);
	}

	// return external iterator
	public Iterator<T> getIterator() {
		return new Iterator<T>(head);
	}

}
