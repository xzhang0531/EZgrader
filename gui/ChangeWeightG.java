package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.Database;
import objects.Category;
import objects.Course;

public class ChangeWeightG {

	protected JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChangeWeightG window = new ChangeWeightG();
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
	public ChangeWeightG(Course course) {
		initialize(course);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Course course) {
		frame = new JFrame();
		frame.setBounds(100, 100, 821, 662);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(55, 78, 658, 511);
		panel.setLayout(null);

		List<Category> categoryList = course.getCategoryList();
		int increment = 0;
		for (Category category : categoryList) {

			
			JLabel label_g = new JLabel();
			label_g.setText(category.getCategoryName() + " :" + category.getGWeight());
			JTextField jfield_g = new JTextField(20);
			jfield_g.setName(category.getCategoryName());
			
			label_g.setBounds(110, 100+increment, 100, 19);
			jfield_g.setBounds(220, 100+increment, 100, 19);
			

			

			panel.add(label_g);
			panel.add(jfield_g);
			
			increment += 50;
//			Weight.add(title);
		}
		JButton change = new JButton();
		change.setBounds(110, 100+increment, 100, 19);
		change.setText("change");
		change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = panel.getComponentCount();
				Map<String, Double> weightMap = new HashMap<>();
				for (Category category: categoryList) {
			    		for (int i = 0; i < count; i++) {
			    			Object obj = panel.getComponent(i);
				    		if (obj instanceof JTextField) {
				    			JTextField text = (JTextField) obj;
				    			if (text.getName().equals(category.getCategoryName())) {
				    				weightMap.put(category.getCategoryName(), Double.parseDouble(text.getText()));
				    			}
				    		}
			    		}
				}
				for(String key:weightMap.keySet()) {
					Database db = new Database();
					db.connect("root", "sss5533");
					try {
						db.updateCategoryWeight("g", course.getCourseId(), key, weightMap.get(key));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				CourseDetail course = new CourseDetail();
				frame.dispose();
				course.run();
			}});
		panel.add(change);
//		Database db = new Database();
//		db.connect("root", "czx123456");
//		try {
//			db.updateDB();
//			List<Course> courseList = db.courseList;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		frame.getContentPane().add(panel);
		
		JLabel lblGraduate = new JLabel();
		lblGraduate.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblGraduate.setText("Graduate");
		lblGraduate.setBounds(200, 48, 105, 16);
		panel.add(lblGraduate);
	}

}
