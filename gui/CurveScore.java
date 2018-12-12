package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import db.Database;
import objects.Assignment;
import objects.Category;
import objects.Course;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CurveScore {

	public JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CurveScore window = new CurveScore();
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
	public CurveScore(Course course) {
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
		
		JLabel lblCurveScoreOf = new JLabel("Curving Scores");
		lblCurveScoreOf.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblCurveScoreOf.setBounds(117, 6, 180, 30);
		frame.getContentPane().add(lblCurveScoreOf);
		
		JComboBox assignmentDropDown = new JComboBox();
		assignmentDropDown.setBounds(98, 89, 161, 27);
		frame.getContentPane().add(assignmentDropDown);
		for(Category category: course.getCategoryList()) {
			for(Assignment assignment : category.getAssignmentList()) {
				assignmentDropDown.addItem(assignment.getAssignmentName());
			}
		}
		
		
		JButton btnAddCurveTo = new JButton("Add Curve to Scores");
		btnAddCurveTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//take in score and add to score in database
				String newCurve = textField.getText();
				double curve = Double.parseDouble(newCurve);
				for(Category category: course.getCategoryList()) {
					for(Assignment assignment : category.getAssignmentList()) {
						if(assignment.getAssignmentName().equals(assignmentDropDown.getSelectedItem())){
							Database db = new Database();
							db.connect("root", "sss5533");
							try {
								db.updateCurve(course.getCourseId(), assignment.getAssignmentName(), curve);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
				
				
				//then
				frame.dispose();
				CourseDetail frame2 = new CourseDetail();
				frame2.run();
			}
		});
		btnAddCurveTo.setBounds(208, 195, 153, 29);
		frame.getContentPane().add(btnAddCurveTo);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(271, 88, 54, 26);
		frame.getContentPane().add(textField);
	}
}
