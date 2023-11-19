package dev.ppaiva;

import javax.swing.SwingUtilities;

import dev.ppaiva.view.GUIView;

public class BSTApplication {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new GUIView("Binary Search Tree", 1000, 500));
	}
}
