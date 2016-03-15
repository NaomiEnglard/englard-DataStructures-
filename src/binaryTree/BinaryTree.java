package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;

import pharmacy.DuplicateDataException;

//import pharmacy.DuplicateDataException;

public class BinaryTree<T extends Comparable<T>> {

	private BinaryTreeNode<T> root;
	private boolean found;

	public BinaryTree() {
		root = new BinaryTreeNode<T>();
		found = false;
	}

	public void insert(T data) throws DuplicateDataException {
		if (root.getInfo() == null) {
			this.root = new BinaryTreeNode<T>(data);
		} else {
			// add to correct spot
			BinaryTreeNode<T> dataNode = new BinaryTreeNode<T>(data);
			addRecursively(dataNode, this.root);
		}
		return;
	}

	private void addRecursively(BinaryTreeNode<T> dataNode,
			BinaryTreeNode<T> currRoot) throws DuplicateDataException {

		if (dataNode.compareTo(currRoot) < 0) {
			if (currRoot.getLeft() == null) {
				currRoot.setLeft(dataNode);
				return;
			} else {
				addRecursively(dataNode, currRoot.getLeft());
			}

		} else if (dataNode.compareTo(currRoot) > 0) {
			if (currRoot.getRight() == null) {
				currRoot.setRight(dataNode);
			} else {
				addRecursively(dataNode, currRoot.getRight());
			}
		} else {
			throw new DuplicateDataException();
		}
		return;

	}

	public boolean remove(T data) throws EmptyTreeException {
		BinaryTreeNode<T> tempNode = new BinaryTreeNode<T>(data);
		if (root == null) {
			throw new EmptyTreeException();
		} else {
			// remove the node
			root = removeNode(tempNode, root);

		}
		return found;
	}

	private BinaryTreeNode<T> removeNode(BinaryTreeNode<T> tempNode,
			BinaryTreeNode<T> currRoot) {
		if (currRoot == null) {
			found = false;
		} else if (tempNode.compareTo(currRoot) < 0)
			currRoot.setLeft(removeNode(tempNode, currRoot.getLeft()));
		else if (tempNode.compareTo(currRoot) > 0)
			currRoot.setRight(removeNode(tempNode, currRoot.getRight()));
		else {
			currRoot = removeData(currRoot);

			found = true;

		}

		return currRoot;
	}

	private BinaryTreeNode<T> removeData(BinaryTreeNode<T> currRoot) {
		// if there is only a right child
		if (currRoot.getLeft() == null)
			return currRoot.getRight();
		// if only a left child
		else if (currRoot.getRight() == null)
			return currRoot.getLeft();
		else // 2 children
		{
			T data = findPredecessor(currRoot.getLeft());
			currRoot.setInfo(data);
			currRoot.setLeft(removeNode(new BinaryTreeNode<T>(data),
					currRoot.getLeft()));
			return currRoot;

		}
	}

	private T findPredecessor(BinaryTreeNode<T> replacement) {
		while (replacement.getRight() != null) {
			replacement = replacement.getRight();
		}
		return replacement.getInfo();
	}

	public boolean hasData(T data) {
		return (find(new BinaryTreeNode<T>(data), root) == null ? false : true);
	}

	private BinaryTreeNode<T> find(BinaryTreeNode<T> removeNode,
			BinaryTreeNode<T> currRoot) {
		if (currRoot == null) {
			return null; // not found
		} else if (removeNode.compareTo(currRoot) < 0) {
			currRoot = find(removeNode, currRoot.getLeft());
		} else if (removeNode.compareTo(currRoot) > 0) {
			currRoot = find(removeNode, currRoot.getRight());
		} else {
			// if it equals the node you are looking for send back the node
			return currRoot;
		}
		return currRoot;

	}

	public void traversePreOrder() {
		System.out.println(root.getInfo());
		traverseP(root.getLeft());
		traverseP(root.getRight());

	}

	private void traverseP(BinaryTreeNode<T> root) {
		if (root == null)
			return; // anchor case
		System.out.println(root.getInfo());
		traverseP(root.getLeft());
		traverseP(root.getRight());

	}

	private void traverseInOrder(BinaryTreeNode<T> currRoot, LinkedList<T> list) {
		if (currRoot == null)
			return; // anchor case
		traverseInOrder(currRoot.getLeft(), list);
		list.add(currRoot.getInfo());
		traverseInOrder(currRoot.getRight(), list);

	}

	public void balanceTree() {
		// find a good root = middle value
		// the best way to balance a tree is keep dividing a list in half and
		// taking the middle
		// value from each half and adding it to the tree
		LinkedList<T> nodes = new LinkedList<T>();
		// add all values in order to linkedList
		this.traverseInOrder(root, nodes);

		// choose the root which is the middle element
		ArrayList<Integer> order = indexOrder(nodes.size());
		// System.out.println(order);
		// System.out.println(nodes);

		this.root = new BinaryTreeNode<T>(nodes.get(order.get(0)));
		for (int i = 1; i < order.size() - 1; i++) {
			try {
				this.insert(nodes.get(order.get(i)));
			} catch (DuplicateDataException e) {
				// this will never occur since data is being retrieved from a
				// preexisiting tree
			}
		}
		/*
		 * for (T element : nodes) { try {
		 * 
		 * this.insert(element); } catch (DuplicateDataException e) { // this
		 * will never happen since you start off with only valid // data } }
		 */
		System.out.println(root.getInfo());
	}

	private ArrayList<Integer> indexOrder(int size) {
		ArrayList<Integer> values = new ArrayList<Integer>();
		nextValue(0, size, values);

		return values;

	}

	private void nextValue(int beg, int end, ArrayList<Integer> values) {
		if (beg >= end) {
			values.add(beg);
			return;
		}
		int split;
		if (end % 2 == 0) {
			split = ((beg + end) / 2);
		} else {
			split = (((beg + end) / 2) + 1);
		}
		values.add(split); // add the middle
		nextValue(beg, split - 1, values); // right middle
		nextValue(split + 1, end, values); // left middle

	}
}
