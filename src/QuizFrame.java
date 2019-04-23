import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class creates quiz frame in which the user can test whatever words that
 * they inputed into the database.
 * 
 * @author Kimhuibeom
 *
 */

public class QuizFrame extends JFrame {
	private JLabel defLabel;
	private JLabel titleLabel;
	private JLabel correctLabel;
	private JLabel wrongLabel;
	private JTextField defField;
	private JTextField answerField;
	private JButton nextButton;
	private JButton prevButton;
	private int count = 1;

	public QuizFrame() {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		// TOP Panel
		JPanel topPanel = new JPanel();

		titleLabel = new JLabel("Vocaulary Quiz");
		titleLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
		topPanel.add(titleLabel);

		// CENTER Panel
		JPanel centerPanel = new JPanel(new GridLayout(2, 1));
		JPanel centerTopPanel = new JPanel(new BorderLayout());
		JPanel centerBotPanel = new JPanel(new FlowLayout());

		JLabel question = new JLabel("What is the word that matches the definition below");
		question.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		question.setHorizontalAlignment(JLabel.CENTER);

		defLabel = new JLabel(DataBase.getVocabulary(1).getDefinition());
		defLabel.setHorizontalAlignment(JLabel.CENTER);

		answerField = new JTextField();
		answerField.addActionListener(new QuizFieldActionListener());

		correctLabel = new JLabel("");
		correctLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		wrongLabel = new JLabel("");
		wrongLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 16));

		centerTopPanel.add(question, BorderLayout.NORTH);
		centerTopPanel.add(defLabel, BorderLayout.CENTER);
		centerTopPanel.add(answerField, BorderLayout.SOUTH);
		centerBotPanel.add(correctLabel);
		centerBotPanel.add(wrongLabel);

		centerPanel.add(centerTopPanel);
		centerPanel.add(centerBotPanel);

		// LEFT, RIGHT PANEL
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(150, 0));
		rightPanel.setPreferredSize(new Dimension(150, 0));

		// BOTTOM Panel
		JPanel botPanel = new JPanel(new BorderLayout());

		prevButton = new JButton("Previous");
		prevButton.addActionListener(new QuizActionListener());
		botPanel.add(prevButton, BorderLayout.WEST);

		nextButton = new JButton("Next");
		nextButton.addActionListener(new QuizActionListener());
		botPanel.add(nextButton, BorderLayout.EAST);

		// Add Component
		cp.add(topPanel, BorderLayout.NORTH);
		cp.add(centerPanel, BorderLayout.CENTER);
		cp.add(leftPanel, BorderLayout.WEST);
		cp.add(rightPanel, BorderLayout.EAST);
		cp.add(botPanel, BorderLayout.SOUTH);

		// Main Frame
		setSize(800, 300);
		setVisible(true);
		setResizable(false);
		setTitle("Quiz");
	}

	private class QuizActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();

			if (source == nextButton) {
				count++;
				defLabel.setText(DataBase.getVocabulary(count).getDefinition());
				correctLabel.setText("");
				wrongLabel.setText("");
			} else if (source == prevButton && count != 1) {
				count--;
				defLabel.setText(DataBase.getVocabulary(count).getDefinition());
				correctLabel.setText("");
				wrongLabel.setText("");
			}
		}
	}

	private class QuizFieldActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JTextField source = (JTextField) e.getSource();

			if (source == answerField) {
				if (answerField.getText().toLowerCase().equals(DataBase.getVocabulary(count).getWord().toLowerCase())) {
					correctLabel.setText("Congratulation! You are correct!");
					wrongLabel.setText("");
				} else {
					wrongLabel.setText("Sorry, but you got it wrong.");
					correctLabel.setText("");
				}
			}
		}
	}
}
