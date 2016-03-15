package bill;

import java.io.Serializable;

public class LLNode<T extends Serializable & Comparable<T>> implements
		Serializable {

	private static final long serialVersionUID = 1L;
	protected T data;
	protected LLNode<T> next;

	// empty Node
	public LLNode() {
		data = null;
		next = null;
	}

	// Node will contain a reference to an object
	public LLNode(T value) {
		this.data = value;
		next = null;

	}

	public LLNode(T value, LLNode<T> next) {
		this.data = value;
		this.next = next;
	}

	// link this Node to another Node
	public void setNext(LLNode<T> next) {
		this.next = next;
	}

	// set data in the Node
	public void setData(T value) {
		this.data = value;
	}

	// get the value the Node contains
	// returns a reference to the data
	public T getData() {
		return data;
	}

	// get the Node that this Node is linked to
	public LLNode<T> getNext() {
		return next;
	}

	public int compareTo(LLNode<T> aNode) {
		return this.data.compareTo(aNode.getData());
	}

}
