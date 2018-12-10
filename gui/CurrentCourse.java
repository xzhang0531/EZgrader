package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;

import db.Database;
import objects.Course;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class CurrentCourse {

	protected JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CurrentCourse window = new CurrentCourse();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CurrentCourse() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 683, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		Database db = new Database();
		try {
			db.connect("root", "sss5533");

			db.updateDB();
			List<Course> course = db.courseList;
			for (Course cur : course) {
				comboBox.addItem(cur.getCourseName().getCode() +" " +cur.getCourseName().getName());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		comboBox.setBounds(83, 109, 219, 114);
		frame.getContentPane().add(comboBox);
		
		JLabel lblCurrentCourses = new JLabel("Current Courses");
		lblCurrentCourses.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblCurrentCourses.setBounds(86, 41, 229, 52);
		frame.getContentPane().add(lblCurrentCourses);
		
		JButton btnNewButton = new JButton("Enter Course");
		btnNewButton.setBounds(88, 263, 138, 39);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Welcome frame2 = new Welcome();
				frame2.frame.setVisible(true);
			}
		});
		btnBack.setBounds(335, 263, 138, 39);
		frame.getContentPane().add(btnBack);
	}
}
