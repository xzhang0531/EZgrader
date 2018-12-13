package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import objects.Course;

import javax.swing.JButton;

public class PrintStats {

	private JFrame frame;
	private JTextField sizeText;
	private JTextField averageText;
	private JTextField maximumText;
	private JTextField minimumText;
	private JTextField medianText;
	private JTextField courseNameText;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PrintStats window = new PrintStats();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public PrintStats(Course course) {
		initialize(course);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Course course) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPrintCourseStatistics = new JLabel("Course Stats for:");
		lblPrintCourseStatistics.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPrintCourseStatistics.setBounds(76, 20, 130, 26);
		frame.getContentPane().add(lblPrintCourseStatistics);
		
		JLabel lblNewLabel = new JLabel("Number of Students:");
		lblNewLabel.setBounds(70, 59, 136, 16);
		frame.getContentPane().add(lblNewLabel);
		
		sizeText = new JTextField(course.getFinalScoreList().size());
		sizeText.setEditable(false);
		sizeText.setBounds(218, 54, 130, 26);
		frame.getContentPane().add(sizeText);
		sizeText.setColumns(10);
		
		JLabel lblFinalScoreAverage = new JLabel("Final Score Average:");
		lblFinalScoreAverage.setBounds(70, 97, 136, 16);
		frame.getContentPane().add(lblFinalScoreAverage);
		
		averageText = new JTextField(String.valueOf(course.calculateAverage()));
		averageText.setEditable(false);
		averageText.setBounds(218, 92, 130, 26);
		frame.getContentPane().add(averageText);
		averageText.setColumns(10);
		
		JLabel lblMaximumScore = new JLabel("Maximum Score:");
		lblMaximumScore.setBounds(97, 142, 109, 16);
		frame.getContentPane().add(lblMaximumScore);
		
		maximumText = new JTextField(String.valueOf(course.getMax()));
		maximumText.setEditable(false);
		maximumText.setBounds(218, 137, 130, 26);
		frame.getContentPane().add(maximumText);
		maximumText.setColumns(10);
		
		JLabel lblMinimumScore = new JLabel("Minimum Score:");
		lblMinimumScore.setBounds(97, 187, 109, 16);
		frame.getContentPane().add(lblMinimumScore);
		
		minimumText = new JTextField(String.valueOf(course.getMin()));
		minimumText.setEditable(false);
		minimumText.setBounds(218, 182, 130, 26);
		frame.getContentPane().add(minimumText);
		minimumText.setColumns(10);
		
		JLabel lblFinalScoreMedian = new JLabel("Final Score Median:");
		lblFinalScoreMedian.setBounds(85, 225, 121, 16);
		frame.getContentPane().add(lblFinalScoreMedian);
		
		medianText = new JTextField(String.valueOf(course.calculateMedian()));
		medianText.setEditable(false);
		medianText.setBounds(218, 220, 130, 26);
		frame.getContentPane().add(medianText);
		medianText.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(6, 243, 75, 29);
		frame.getContentPane().add(btnBack);
		
		courseNameText = new JTextField(course.getCourseName().getName());
		courseNameText.setEditable(false);
		courseNameText.setBounds(218, 21, 130, 26);
		frame.getContentPane().add(courseNameText);
		courseNameText.setColumns(10);
	}
}
