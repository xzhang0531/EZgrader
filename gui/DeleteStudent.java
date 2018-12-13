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

public class DeleteStudent {
	JFrame frame;
	JPanel panel;
	
	public DeleteStudent(Database db, Course course) {
		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(null);
		
		JComboBox stuDropDown = new JComboBox();
		stuDropDown.setBounds(98, 89, 261, 27);
		panel.add(stuDropDown);
		for(Student s: course.getStudentList()) {
			stuDropDown.addItem(s.getBuid() + "  " + s.getName().getFullName());
		}
		
		JButton btn_delete = new JButton("delete");
		btn_delete.setBounds(210, 240, 90, 20);
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buid = stuDropDown.getSelectedItem().toString().split("  ")[0];
						
				try {
					db.deleteEnrollment(buid, course.getCourseId());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				frame.dispose();
				CourseDetail c = new CourseDetail();
				c.run();
			}
		});
		panel.add(btn_delete);
		
		
		JButton btn_cancel = new JButton("Cancel");
		btn_cancel.setBounds(310, 240, 90, 20);
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CourseDetail c = new CourseDetail();
				c.run();
			}
		});
		panel.add(btn_cancel);
		
		
		
		
		
		
		
		
		
		
		frame.setContentPane(panel);
		frame.setSize(600, 400);
	}
}
