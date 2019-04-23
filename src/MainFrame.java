import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

/**
 * This class creates the main frame of the application. When the program is run the
 * first frame that pops up on screen is this frame. It handles word &
 * definition input.
 * 
 * @author Kimhuibeom
 *
 */

public class MainFrame extends JFrame implements FrameAccessor {
	JLabel title;
	JTextField wordField;
	JTextField defField;
	JButton inputButton;
	JButton displayButton;
	JButton quizButton;
	JButton resetButton;

	public MainFrame() {
		// MAIN FRAME
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		// TOP FRAME
		JPanel topPanel = new JPanel(new FlowLayout());

		title = new JLabel("Study Glossary");
		title.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
		topPanel.add(title);

		// RIGHT, LEFT Panel
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();

		leftPanel.setPreferredSize(new Dimension(100, 0));
		rightPanel.setPreferredSize(new Dimension(100, 0));

		// CENTER Panel
		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel centerCenterPanel = new JPanel(new GridLayout(2, 1));
		JPanel centerLeftPanel = new JPanel(new GridLayout(2, 1));
		JPanel centerRightPanel = new JPanel(new GridLayout());

		// Label
		JLabel wordLabel = new JLabel("Word: ");
		wordLabel.setHorizontalAlignment(JLabel.RIGHT);
		JLabel defLabel = new JLabel("Definition: ");
		wordLabel.setHorizontalAlignment(JLabel.RIGHT);

		// Textfield
		wordField = new JTextField();
		defField = new JTextField();

		centerLeftPanel.add(wordLabel);
		centerCenterPanel.add(wordField);
		centerLeftPanel.add(defLabel);
		centerCenterPanel.add(defField);

		// Button
		inputButton = new JButton("Input");
		centerRightPanel.add(inputButton);

		inputButton.addActionListener(new MainActionListener());

		centerPanel.add(centerLeftPanel, BorderLayout.WEST);
		centerPanel.add(centerCenterPanel, BorderLayout.CENTER);
		centerPanel.add(centerRightPanel, BorderLayout.EAST);

		// BOTTOM Panel
		JPanel botPanel = new JPanel(new FlowLayout());
		botPanel.setPreferredSize(new Dimension(0, 70));

		// Button
		displayButton = new JButton("Display");
		quizButton = new JButton("Quiz");
		resetButton = new JButton("Reset");

		displayButton.addActionListener(new MainActionListener());
		quizButton.addActionListener(new MainActionListener());
		resetButton.addActionListener(new MainActionListener());

		botPanel.add(displayButton);
		botPanel.add(quizButton);
		botPanel.add(resetButton);

		// Adding Componenets
		cp.add(topPanel, BorderLayout.NORTH);
		cp.add(leftPanel, BorderLayout.WEST);
		cp.add(rightPanel, BorderLayout.EAST);
		cp.add(centerPanel, BorderLayout.CENTER);
		cp.add(botPanel, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setTitle("Custom Dictionary");
		setSize(600, 200);
	}

	public static void main(String[] args) {
		MainFrame main = new MainFrame();
	}

	private class MainActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();

			if (source == inputButton) {
				try {
					DataBase.push(0, wordField.getText(), defField.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				wordField.setText("");
				defField.setText("");
			} else if (source == displayButton) {
				DisplayFrame displayFrame = new DisplayFrame();
			} else if (source == quizButton) {
				QuizFrame quizFrame = new QuizFrame();
			} else if (source == resetButton) {
				DataBase.delete(2);
			}
			// "Your word and definition is now saved! Select input again to enter a new
			// word")
		}
	}

	@Override
	public MainFrame getMainFrame() {
		return null;
	}

	@Override
	public DisplayFrame getDisplayFrame() {
		return null;
	}

	@Override
	public QuizFrame getQuizFrame() {
		return null;
	}
}