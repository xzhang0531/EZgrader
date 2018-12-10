package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;

import objects.Category;

import java.awt.Font;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ComponentsType {

	protected JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ComponentsType window = new ComponentsType();
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
	public ComponentsType(List<Category> categoryList, int courseid) {
		initialize(categoryList, courseid);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(List<Category> categoryList, int courseid) {
		frame = new JFrame();
		frame.setBounds(100, 100, 683, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(248, 178, 144, 36);
		for (Category category : categoryList) {
			comboBox.addItem(category.getCategoryName());
		}
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				String category = (String)comboBox.getSelectedItem();
				ChangeTypeWeight frame2 = new ChangeTypeWeight(categoryList, category, courseid);
				frame2.frame.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(263, 277, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblChooseTheComponent = new JLabel("Choose The Component You Want To Change");
		lblChooseTheComponent.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblChooseTheComponent.setBounds(161, 74, 417, 43);
		frame.getContentPane().add(lblChooseTheComponent);
	}
}
