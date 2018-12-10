package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import objects.Course;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangeWeight {

	protected JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChangeWeight window = new ChangeWeight();
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
	public ChangeWeight(Course course) {
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
		
		JButton btnNewButton = new JButton("Component");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CurrentWeight frame2 = new CurrentWeight(course);
				frame2.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(146, 211, 160, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("Sub-Component");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ComponentsType frame2= new ComponentsType(course.getCategoryList(), course.getCourseId());
				frame2.frame.setVisible(true);
				
			}
		});
		button.setBounds(376, 211, 160, 29);
		frame.getContentPane().add(button);
	}
}
