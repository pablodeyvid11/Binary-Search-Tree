package dev.ppaiva.beans;

public class IndexNode<T extends Comparable<T>> {
	private int index;
	private Node<T> node;

	public IndexNode() {
	}

	public IndexNode(int index, Node<T> node) {
		this.index = index;
		this.node = node;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Node<T> getNode() {
		return node;
	}

	public void setNode(Node<T> node) {
		this.node = node;
	}

}
