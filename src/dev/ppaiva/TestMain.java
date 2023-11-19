package dev.ppaiva;

import dev.ppaiva.beans.BST;

public class TestMain {
	public static void main(String[] args) {
		BST<Integer> tree = new BST<>();

		tree.insert(5);
		tree.insert(7);
		tree.insert(3);
		tree.insert(4);
		tree.insert(6);
		tree.insert(2);
		tree.insert(8);

//		System.out.println(tree.getNodeAtIndex(1).getValue());
//		System.out.println(tree.getNodeAtIndex(2).getValue());
//		System.out.println(tree.getNodeAtIndex(3).getValue());
//		System.out.println(tree.getNodeAtIndex(4).getValue());
//		System.out.println(tree.getNodeAtIndex(5).getValue());
//		System.out.println(tree.getNodeAtIndex(6).getValue());
//		System.out.println(tree.getNodeAtIndex(7).getValue());
//		System.out.println(tree.getNodeAtIndex(8).getValue());
//		System.out.println(tree.getNodeAtIndex(9).getValue());
	}
}
