package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;

import objects.Course;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangeWeight {

	protected JFrame frame;

	public ChangeWeight(Course course) {
		initialize(course);
	}


	private void initialize(Course course) {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		ImageIcon weightLogo1 = new ImageIcon("gui/img/weight1.jpg");
		Image weightLogoImage = weightLogo1.getImage();
		Image newWeightLogoImage = weightLogoImage.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
		weightLogo1 = new ImageIcon(newWeightLogoImage);
		JLabel buLogoLabel = new JLabel(weightLogo1);
		buLogoLabel.setBounds(120, 100, weightLogo1.getIconWidth(), weightLogo1.getIconHeight());
		frame.getContentPane().add(buLogoLabel);
		
		ImageIcon weightLogo2 = new ImageIcon("gui/img/weight2.jpg");
		Image weightLogoImage2 = weightLogo2.getImage();
		Image newWeightLogoImage2 = weightLogoImage2.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
		weightLogo2 = new ImageIcon(newWeightLogoImage2);
		JLabel buLogoLabel2 = new JLabel(weightLogo2);
		buLogoLabel2.setBounds(360, 100, weightLogo2.getIconWidth(), weightLogo2.getIconHeight());
		frame.getContentPane().add(buLogoLabel2);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(600, 350, 100, 29);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CourseDetail c = new CourseDetail();
				c.run(course.getCourseId());
			}
		});
		frame.getContentPane().add(btnCancel);
		
		
		
		JButton btnNewButton = new JButton("Type of Assignment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CurrentWeight frame2 = new CurrentWeight(course);
				frame2.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(126, 350, 180, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("Specific Assignment");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ComponentsType frame2= new ComponentsType(course.getCategoryList(), course.getCourseId());
				frame2.frame.setVisible(true);
				
			}
		});
		button.setBounds(366, 350, 180, 29);
		frame.getContentPane().add(button);
	}
}
