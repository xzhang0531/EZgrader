package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import objects.Assignment;
import objects.Category;
import objects.Course;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTextField;

import db.Database;

public class AddComponent {

	protected JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AddComponent(Course course) {
		initialize(course);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Course course) {
		frame = new JFrame();
		frame.setBounds(100, 100, 683, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		for (Category category : course.getCategoryList()) {
			comboBox.addItem(category.getCategoryName());
		}
		comboBox.setBounds(148, 183, 167, 34);
		frame.getContentPane().add(comboBox);
		
		JLabel lblAddComponent = new JLabel("Selete The Component You Want To Use");
		lblAddComponent.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblAddComponent.setBounds(143, 63, 404, 53);
		frame.getContentPane().add(lblAddComponent);
		textField = new JTextField();
		textField.setBounds(442, 186, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Category category : course.getCategoryList()) {
					if (comboBox.getSelectedItem().toString().equals(category.getCategoryName())) {
						int size = category.getAssignmentList().size();
						size = size +1;
						String name = category.getCategoryName() +" " + size;
						double weight = 1.0 / (size);
						Assignment assignment = new Assignment(name, category.getCategoryName(), weight, weight, Double.parseDouble(textField.getText()), 0);
						Database db = new Database();
						db.connect("root", "sss5533");
						try {
							db.AddAssignment(assignment, course.getCourseId(), category.getCategoryName());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						for(Assignment a: category.getAssignmentList()) {
							try {
								db.updateAssignmentWeight("g", course.getCourseId(), a.getAssignmentName(), weight);
								db.updateAssignmentWeight("ug", course.getCourseId(), a.getAssignmentName(), weight);
							} catch (SQLException e1) {
								e1.printStackTrace();
							} finally {
								frame.dispose();
								CourseDetail cd = new CourseDetail();
								cd.run();
							}
							
						}
						
						
						
						
					}
				}
				

				
			}});
		btnNewButton.setBounds(156, 304, 117, 29);
		frame.getContentPane().add(btnNewButton);
		

		
		JLabel lblNewLabel = new JLabel("Max Score");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setBounds(451, 149, 107, 29);
		frame.getContentPane().add(lblNewLabel);
	}
}
