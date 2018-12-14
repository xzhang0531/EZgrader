package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import db.Database;
import objects.Assignment;
import objects.Category;
import objects.Course;

public class ImportSavedSettings {

	protected JFrame frame;


	public ImportSavedSettings(Course course) {
		initialize(course);
	}


	private void initialize(Course course) {
		frame = new JFrame();
		frame.setBounds(100, 100, 683, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		Database db = new Database();

		db.connect();

		db.updateDB();
		List<Course> courseList = db.courseList;
		for (Course cur : courseList) {
			comboBox.addItem(cur.getCourseName().getCode() +" " +cur.getCourseName().getName());
		}

		comboBox.setBounds(83, 109, 219, 60);
		frame.getContentPane().add(comboBox);
		
		JLabel lblCurrentCourses = new JLabel("Select the Course Setting You Want");
		lblCurrentCourses.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblCurrentCourses.setBounds(86, 41, 444, 52);
		frame.getContentPane().add(lblCurrentCourses);
		
		JButton btnNewButton = new JButton("Import Setting");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseName = comboBox.getSelectedItem().toString();
				
				try {
					
					db.updateDB();
					List<Course> courseList = db.courseList;
					for (Course cur : courseList) {
						if (courseName.equals(cur.getCourseName().getCode() +" " +cur.getCourseName().getName())) {
							for (Category category : cur.getCategoryList()) {
								Category newcat = new Category(category.getCategoryName(), 0, category.getGWeight(), category.getUgWeight());
								for (Assignment assignment : category.getAssignmentList()) {
									Assignment newassign = new Assignment(assignment.getAssignmentName(), category.getCategoryName(), assignment.getGWeight(), assignment.getUgWeight(), assignment.getMaxScore(), 0);
									newcat.addAssignment(newassign);
								}
								course.addCategory(newcat);
							}
						}
					}
				} catch(Exception err) {
					err.printStackTrace();
				}
				frame.dispose();
				db.disconnect();
				AddStudent frame2 = new AddStudent(course);
				frame2.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(88, 263, 138, 39);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				db.disconnect();
				Welcome frame2 = new Welcome();
				frame2.frame.setVisible(true);
			}
		});
		btnBack.setBounds(335, 263, 138, 39);
		frame.getContentPane().add(btnBack);
	}

}
