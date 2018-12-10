package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Welcome {

	protected JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
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
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 652, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Create New Course");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CreateCourse frame2 = new CreateCourse();
				frame2.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(414, 116, 170, 49);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCurrentCourse = new JButton("Current Course");
		btnCurrentCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CurrentCourse frame2 = new CurrentCourse();
				frame2.frame.setVisible(true);
			}
		});
		btnCurrentCourse.setBounds(414, 193, 170, 49);
		frame.getContentPane().add(btnCurrentCourse);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login frame2 = new Login();
				frame2.frame.setVisible(true);
			}
		});
		btnLogout.setBounds(414, 277, 170, 49);
		frame.getContentPane().add(btnLogout);
		
		JLabel lblWelcomeProfessor = new JLabel("Welcome Professor!");
		lblWelcomeProfessor.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblWelcomeProfessor.setBounds(72, 50, 253, 49);
		frame.getContentPane().add(lblWelcomeProfessor);
		
		ImageIcon image = new ImageIcon("./image/Thank.png");
		JLabel lblNewLabel = new JLabel(image);
		lblNewLabel.setBounds(65, 105, image.getIconWidth(), image.getIconHeight());
		frame.getContentPane().add(lblNewLabel);
	}

}
