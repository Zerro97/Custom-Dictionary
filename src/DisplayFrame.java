import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

/**
 * This class creates display frame in which it display the words that the user
 * has inputted.
 * 
 * @author Kimhuibeom
 *
 */

public class DisplayFrame extends JFrame {
	JTextArea wordArea;

	public DisplayFrame() {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		// TOP PANEL
		JPanel topPanel = new JPanel(new FlowLayout());

		JLabel title = new JLabel("Word List");
		title.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
		topPanel.add(title);

		// CENTER Panel
		JPanel centerPanel = new JPanel(new GridLayout());

		wordArea = new JTextArea();
		wordArea.setEditable(false);

		try {
			wordArea.append(DataBase.getWordList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		centerPanel.add(wordArea);

		// RIGHT, LEFT, BOTTOM Panel
		JPanel rightPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel botPanel = new JPanel();

		rightPanel.setPreferredSize(new Dimension(10, 10));
		leftPanel.setPreferredSize(new Dimension(10, 10));
		botPanel.setPreferredSize(new Dimension(10, 10));

		// Add Components
		cp.add(topPanel, BorderLayout.NORTH);
		cp.add(centerPanel, BorderLayout.CENTER);
		cp.add(rightPanel, BorderLayout.EAST);
		cp.add(leftPanel, BorderLayout.WEST);
		cp.add(botPanel, BorderLayout.SOUTH);

		// Main Frame
		setSize(300, 600);
		setVisible(true);
		setResizable(false);
		setTitle("Word List");
	}
}
