package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import db.Database;
import objects.Assignment;
import objects.Category;
import objects.Course;
import objects.Graduate;
import objects.Student;
import objects.StudentName;
import objects.UnderGraduate;

public class DeleteComponent {
	JFrame frame;
	JPanel panel;
	
	public DeleteComponent(Database db, Course course) {
		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(null);
		
		JComboBox componentDropDown = new JComboBox();
		componentDropDown.setBounds(98, 89, 261, 27);
		panel.add(componentDropDown);
		for(Category c: course.getCategoryList()) {
			for(Assignment a: c.getAssignmentList()) {
				componentDropDown.addItem(a.getAssignmentName());
			}
		}
		
		JButton btn_delete = new JButton("delete");
		btn_delete.setBounds(210, 240, 90, 20);
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String componentname = componentDropDown.getSelectedItem().toString();
				db.deleteAssignment(componentname, course.getCourseId());
				String categoryname = null;
				for(Category c : course.getCategoryList()) {
					for(Assignment a :c.getAssignmentList()) {
						if(a.getAssignmentName().equals(componentname)) {
							categoryname = c.getCategoryName();
						}
					}
				}
				
				for(Category c : course.getCategoryList()) {
					if (c.getCategoryName().equals(categoryname)) {
						for(Assignment a: c.getAssignmentList()) {
							int size = c.getAssignmentList().size() - 1;
							double weight = 1.0 / (size);
							db.updateAssignmentWeight("g", course.getCourseId(), a.getAssignmentName(), weight);
							db.updateAssignmentWeight("ug", course.getCourseId(), a.getAssignmentName(), weight);
						}
					}
				}
				
				

				
				frame.dispose();
				CourseDetail c = new CourseDetail();
				c.run(course.getCourseId());
			}
		});
		panel.add(btn_delete);
		
		
		JButton btn_cancel = new JButton("Cancel");
		btn_cancel.setBounds(310, 240, 90, 20);
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CourseDetail c = new CourseDetail();
				c.run(course.getCourseId());
			}
		});
		panel.add(btn_cancel);
		
		
		frame.setContentPane(panel);
		frame.setSize(600, 400);
	}
}
