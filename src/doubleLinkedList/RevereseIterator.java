package doubleLinkedList;

import java.io.Serializable;

public class RevereseIterator<T extends Serializable & Comparable<T>> {
	private DoubleLinkNode<T> tail;
	private DoubleLinkNode<T> backwordUpTo;

	public RevereseIterator(DoubleLinkNode<T> tail) {
		this.tail = this.backwordUpTo = tail;
	}

	public boolean hasNext() {
		// while there is still a node with data there is more to iterate
		// through
		return backwordUpTo != null;

	}

	public T next() {
		T data = null;
		if (hasNext()){
		data = backwordUpTo.getData();
		backwordUpTo = backwordUpTo.getPrev();
		}
		return data;
	}

	public void reset() {
		this.tail = this.backwordUpTo.getPrev();
	}

	public void set(T data) {
		this.backwordUpTo.data = data;
	}
}
