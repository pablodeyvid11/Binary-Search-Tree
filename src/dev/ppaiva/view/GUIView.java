package dev.ppaiva.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import dev.ppaiva.services.BSTService;

public class GUIView {

	private String frameName;
	private Integer width;
	private Integer height;

	private JFrame frame;
	private JTextPane textPane;
	private JTextField textField;
	private JScrollPane scrollPane;
	private DefaultStyledDocument styledDocument;

	private BSTService service;

	public GUIView(String frameName, Integer width, Integer height) {
		this.frameName = frameName;
		this.width = width;
		this.height = height;
		this.service = new BSTService();

		buildView();
		fixTextFieldPrefix();
		makeVisible();
	}

	private void buildView() {
		frame = new JFrame(frameName);
		frame.setSize(this.width, this.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.setExtendedState(JFrame.NORMAL);
		File iconFile = new File(System.getProperty("user.dir"), "icon.png");
        ImageIcon icon = new ImageIcon(iconFile.getPath());
        frame.setIconImage(icon.getImage());
        
		Dimension dimensaoTela = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dimensaoTela.width - this.width) / 2;
		int y = (dimensaoTela.height - this.height) / 2;
		frame.setLocation(x, y);

		Font terminalFont = new Font("Monospaced", Font.PLAIN, 16);

		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(Color.BLACK);
		textPane.setForeground(Color.WHITE);
		textPane.setFont(terminalFont);

		styledDocument = new DefaultStyledDocument();

		textPane.setDocument(styledDocument);

		scrollPane = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		textField = new JTextField("> ");
		textField.setBackground(Color.BLACK);
		textField.setForeground(Color.WHITE);
		textField.setCaretColor(Color.WHITE);
		textField.setFont(terminalFont);

		frame.add(scrollPane, BorderLayout.CENTER);
		frame.add(textField, BorderLayout.SOUTH);

		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = textField.getText().replaceFirst("> ", "");
				appendToTextPane("bst@local:~$ ", Color.BLUE);
				appendToTextPane(input + "\n", Color.WHITE);

				try {
					String text = service.executeCommand(input);
					if (text.equals("clear"))
						textPane.setText("");
					else if (text.equals("exit"))
						System.exit(0);
					else
						appendToTextPane(text + "\n", Color.WHITE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				DocumentFilter filter = ((javax.swing.text.AbstractDocument) textField.getDocument())
						.getDocumentFilter();
				((javax.swing.text.AbstractDocument) textField.getDocument()).setDocumentFilter(null);
				textField.setText("> ");
				((javax.swing.text.AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
			}
		});
	}

	private void appendToTextPane(String text, Color color) {
		SimpleAttributeSet attrs = new SimpleAttributeSet();
		StyleConstants.setForeground(attrs, color);
		try {
			styledDocument.insertString(styledDocument.getLength(), text, attrs);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private void fixTextFieldPrefix() {
		Document document = textField.getDocument();
		DocumentFilter filter = new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String text, AttributeSet attrs)
					throws BadLocationException {
				if (offset >= 2) {
					super.insertString(fb, offset, text, attrs);
				}
			}

			@Override
			public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
				if (offset >= 2) {
					super.remove(fb, offset, length);
				}
			}

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				if (offset >= 2) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		};
		((javax.swing.text.AbstractDocument) document).setDocumentFilter(filter);
	}

	public void makeVisible() {
		frame.setVisible(true);
		textField.requestFocus();
	}

	public void makeInvisible() {
		frame.setVisible(false);
	}
}
