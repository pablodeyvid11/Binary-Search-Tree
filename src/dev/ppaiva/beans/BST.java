package dev.ppaiva.beans;

public class BST<T extends Comparable<T>> {
	private Node<T> root;
	private String type;
	private Integer size;

	public BST() {

	}

	public BST(T root, String type) {
		this.root = new Node<T>(root);
		this.type = type;
		this.size = 0;
	}

	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSize() {
		return size;
	}

	private void setSize(Integer size) {
		this.size = size;
	}

	public void insert(Object value) {
		@SuppressWarnings("unchecked")
		T v = (T) value;
		insert(v);
	}

	public void insert(T value) {
		if (root == null) {
			this.root = new Node<T>(value);
			this.root.setLevel(1);
			return;
		}
		insertRecursive(value, root, this.root.getLevel());
		setSize(getSize() + 1);
	}

	private void insertRecursive(T value, Node<T> node, int level) {

		switch (node.getValue().compareTo(value)) {
		case 1: {
			if (node.getLeft() == null) {
				node.setLeft(new Node<T>(value));
				node.getLeft().setLevel(level + 1);
			} else
				insertRecursive(value, node.getLeft(), level + 1);
			break;
		}
		case -1: {
			if (node.getRight() == null) {
				node.setRight(new Node<T>(value));
				node.getRight().setLevel(level + 1);
			} else
				insertRecursive(value, node.getRight(), level + 1);
			break;
		}
		case 0: {
			throw new IllegalArgumentException("Element already exists.");
		}
		}

	}

	public String printTree() {
		StringBuilder sb = new StringBuilder();
		printTreeRecursive(root, 1, sb);
		return sb.toString();
	}

	private void printTreeRecursive(Node<T> node, int level, StringBuilder sb) {
		String separator = ".....".repeat(level);
		sb.append("" + separator + node.getValue() + "\n");
		if (node.getLeft() != null) {
			printTreeRecursive(node.getLeft(), level + 1, sb);
		}
		if (node.getRight() != null) {
			printTreeRecursive(node.getRight(), level + 1, sb);
		}
	}

	public String printTreeInOrder() {
		StringBuilder sb = new StringBuilder();
		printInOrderRecursive(root, sb);
		return sb.toString();
	}

	private void printInOrderRecursive(Node<T> node, StringBuilder sb) {
		if (node.getLeft() != null)
			printInOrderRecursive(node.getLeft(), sb);
		sb.append(node.getValue() + " ");
		if (node.getRight() != null)
			printInOrderRecursive(node.getRight(), sb);
	}

	public String printTreePreOrder() {
		StringBuilder sb = new StringBuilder();
		printPreOrderRecursive(root, sb);
		return sb.toString();
	}

	private void printPreOrderRecursive(Node<T> node, StringBuilder sb) {
		sb.append(node.getValue() + " ");
		if (node.getLeft() != null)
			printPreOrderRecursive(node.getLeft(), sb);
		if (node.getRight() != null)
			printPreOrderRecursive(node.getRight(), sb);
	}

	public String printTreePostOrder() {
		StringBuilder sb = new StringBuilder();
		printPostOrderRecursive(root, sb);
		return sb.toString();
	}

	private void printPostOrderRecursive(Node<T> node, StringBuilder sb) {
		if (node.getLeft() != null)
			printPostOrderRecursive(node.getLeft(), sb);
		if (node.getRight() != null)
			printPostOrderRecursive(node.getRight(), sb);
		sb.append(node.getValue() + " ");
	}

	public int indexOf(String value) {
		IndexResult result = indexOfRecursive(root, value, 1);
		return result.isResult() ? result.getIndex() : -1;
	}

	private IndexResult indexOfRecursive(Node<T> node, String value, int index) {
		if (node == null) {
			return new IndexResult(index, false);
		}

		IndexResult leftResult = indexOfRecursive(node.getLeft(), value, index);
		if (leftResult.isResult()) {
			return leftResult;
		}

		index = leftResult.getIndex();

		if (node.getValue().toString().equals(value)) {
			return new IndexResult(index, true);
		}

		index++;

		IndexResult rightResult = indexOfRecursive(node.getRight(), value, index);
		return rightResult;
	}

	public Node<T> findByValue(String value) {
		Node<T> aux = new Node<T>(null);

		findByValueRecursive(root, value, aux);

		return aux;
	}

	private void findByValueRecursive(Node<T> node, String value, Node<T> aux) {

		if (node.getValue().toString().equals(value)) {
			aux.setValue(node.getValue());
			aux.setLeft(node.getLeft());
			aux.setRight(node.getRight());

			return;
		}

		if (node.getLeft() != null)
			findByValueRecursive(node.getLeft(), value, aux);
		if (node.getRight() != null)
			findByValueRecursive(node.getRight(), value, aux);
	}
}
