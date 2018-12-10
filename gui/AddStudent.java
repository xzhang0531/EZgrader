package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import objects.Course;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddStudent {

	protected JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					addStudent window = new addStudent();
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
	public AddStudent(Course course) {
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
		
		JLabel lblAddStudents = new JLabel("Add Students");
		lblAddStudents.setBounds(146, 19, 90, 16);
		frame.getContentPane().add(lblAddStudents);
		

		
		JButton btnAddStudentsBy_1 = new JButton("File Upload");
		btnAddStudentsBy_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				LocateFile nextPage = new LocateFile(course);
				nextPage.frame.setVisible(true);
			}
		});
		btnAddStudentsBy_1.setBounds(122, 89, 145, 29);
		frame.getContentPane().add(btnAddStudentsBy_1);
	}

}
