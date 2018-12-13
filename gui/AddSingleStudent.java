package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.Database;
import objects.Course;
import objects.Graduate;
import objects.Student;
import objects.StudentName;
import objects.UnderGraduate;

public class AddSingleStudent {
	JFrame frame;
	JPanel panel;
	
	public AddSingleStudent(Database db, Course course) {
		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(null);
		
		
		JLabel buidLabel = new JLabel("BUID:");
		JLabel firstnameLabel = new JLabel("First Name:");
		JLabel lastnameLabel = new JLabel("Last Name:");
		JLabel middlenameLabel = new JLabel("Middle Name:");
		JLabel majorLabel = new JLabel("Major:");
		JLabel collegeLabel = new JLabel("College:");
		JLabel gpaLabel = new JLabel("GPA:");
		JLabel stu_typeLabel = new JLabel("Student Type:");
		
		JTextField buidField = new JTextField();
		JTextField firstnameField = new JTextField();
		JTextField lastnameField = new JTextField();
		JTextField middlenameField = new JTextField();
		JTextField majorField = new JTextField();
		JTextField collegeField = new JTextField();
		JTextField gpaField = new JTextField();
		JComboBox stu_typeBox = new JComboBox();
		stu_typeBox.addItem("Graduate");
		stu_typeBox.addItem("Undergraduate");
		

		
		
		buidLabel.setBounds      (180, 50, 150, 20);
		buidField.setBounds      (280, 50, 150, 20);
		firstnameLabel.setBounds (180, 70, 150, 20);
		firstnameField.setBounds (280, 70, 150, 20);
		lastnameLabel.setBounds  (180, 90, 150, 20);
		lastnameField.setBounds  (280, 90, 150, 20);
		middlenameLabel.setBounds(180, 110, 150, 20);
		middlenameField.setBounds(280, 110, 150, 20);
		majorLabel.setBounds     (180, 130, 150, 20);
		majorField.setBounds     (280, 130, 150, 20);
		collegeLabel.setBounds   (180, 150, 150, 20);
		collegeField.setBounds   (280, 150, 150, 20);
		gpaLabel.setBounds       (180, 170, 150, 20);
		gpaField.setBounds       (280, 170, 150, 20);
		stu_typeLabel.setBounds  (180, 190, 150, 20);
		stu_typeBox.setBounds    (280, 190, 150, 20);
		

		JButton btn_add = new JButton("Add");
		btn_add.setBounds(210, 240, 90, 20);
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student;
				String buid = buidField.getText();
				String firstname = firstnameField.getText();
				String lastname = lastnameField.getText();
				String middlename = middlenameField.getText();
				String major = majorField.getText();
				String college = collegeField.getText();
				double gpa = Double.parseDouble(gpaField.getText());
				
				StudentName name = new StudentName(firstname, lastname, middlename);
				if(stu_typeBox.getSelectedItem().toString().equals("Graduate")) {
					student = new Graduate(name, buid, major, college, gpa);
				}else {
					student = new UnderGraduate(name, buid, major, college, gpa);
				}
				
				try {
					db.AddSingleStudent(student);
					db.AddEnrollment(student.getBuid(), course.getCourseId());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				frame.dispose();
				CourseDetail c = new CourseDetail();
				c.run();
			}
		});
		panel.add(btn_add);
		
		
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
		
		
		
		
		panel.add(buidLabel);
		panel.add(firstnameLabel);
		panel.add(lastnameLabel);
		panel.add(middlenameLabel);
		panel.add(majorLabel);
		panel.add(collegeLabel);
		panel.add(gpaLabel);
		panel.add(stu_typeLabel);
		panel.add(buidField);
		panel.add(firstnameField);
		panel.add(lastnameField);
		panel.add(middlenameField);
		panel.add(majorField);
		panel.add(collegeField);
		panel.add(gpaField);
		panel.add(stu_typeBox);
		
		frame.setContentPane(panel);
		frame.setSize(600, 400);
	}
	
	
}
