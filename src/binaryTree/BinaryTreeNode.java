package binaryTree;

public class BinaryTreeNode<T extends Comparable<T>> implements
		Comparable<BinaryTreeNode<T>> {

	private T info;
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;

	public BinaryTreeNode() {
		this(null);
	}

	public BinaryTreeNode(T data) {
		this.info = data;
		this.left = null;
		this.right = null;
	}

	public void setInfo(T data) {
		this.info = data;
	}

	public T getInfo() {
		return this.info;
	}

	public void setLeft(BinaryTreeNode<T> lData) {
		left = lData;

	}

	public void setRight(BinaryTreeNode<T> rData) {
		right = rData;
	}

	public BinaryTreeNode<T> getLeft() {
		return left;
	}

	public BinaryTreeNode<T> getRight() {
		return right;
	}

	public int compareTo(BinaryTreeNode<T> other) {
		return this.info.compareTo(other.info);
	}

}
