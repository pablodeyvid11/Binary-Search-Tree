package dev.ppaiva.beans;

public class Node<T extends Comparable<T>> {

	private T value;
	private Node<T> left;
	private Node<T> right;
	private Integer contLeftNodes;
	private Integer contRightNodes;
	private Integer level;

	public Node(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	public Integer getContLeftNodes() {
		return contLeftNodes;
	}

	public void setContLeftNodes(Integer contLeftNodes) {
		this.contLeftNodes = contLeftNodes;
	}

	public Integer getContRightNodes() {
		return contRightNodes;
	}

	public void setContRightNodes(Integer contRightNodes) {
		this.contRightNodes = contRightNodes;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}