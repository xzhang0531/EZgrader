package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import db.Database;
import objects.Assignment;
import objects.Category;
import objects.Course;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CurveScore {

	public JFrame frame;
	private JTextField textField;


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
		
		
		JButton btnAddCurveTo = new JButton("Add Curve");
		btnAddCurveTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//take in score and add to score in database
				String newCurve = textField.getText();
				double curve;
				try {
					curve = Double.parseDouble(newCurve);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(frame, "Invalid value!");
					return;
				}
				Database db = new Database();
				db.connect();
				for(Category category: course.getCategoryList()) {
					for(Assignment assignment : category.getAssignmentList()) {
						if(assignment.getAssignmentName().equals(assignmentDropDown.getSelectedItem())){
							db.updateCurve(course.getCourseId(), assignment.getAssignmentName(), curve);
						}
					}
				}
				
				db.disconnect();
				//then
				frame.dispose();
				CourseDetail frame2 = new CourseDetail();
				frame2.run(course.getCourseId());
			}
		});
		btnAddCurveTo.setBounds(80, 195, 117, 29);
		frame.getContentPane().add(btnAddCurveTo);
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(240, 195, 117, 29);
		frame.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CourseDetail cd = new CourseDetail();
				cd.run(course.getCourseId());
				
			}
		});
		
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(271, 89, 54, 27);
		frame.getContentPane().add(textField);
	}
}
