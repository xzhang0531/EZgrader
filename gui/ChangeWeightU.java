package gui;

import java.awt.EventQueue;
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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeWeightU {

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
	public ChangeWeightU(Course course) {
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
		panel.setBounds(55, 78, 658, 463);
		panel.setLayout(null);
		JLabel label_type = new JLabel();
		label_type.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		label_type.setBounds(200, 31, 187, 50);
		label_type.setText("UnderGraduate");
		panel.add(label_type);
		List<Category> categoryList = course.getCategoryList();
		int increment = 0;
		for (Category category : categoryList) {
			JLabel label_sw = new JLabel();
			label_sw.setText(category.getCategoryName() + " :" + category.getUgWeight());
			JTextField jfield_sw = new JTextField(20);
			jfield_sw.setName(category.getCategoryName());

			
			label_sw.setBounds(110, 100+increment, 100, 19);
			jfield_sw.setBounds(220, 100+increment, 100, 19);
			

			
			panel.add(label_sw);
			panel.add(jfield_sw);	

			
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
						db.updateCategoryWeight("ug", course.getCourseId(), key, weightMap.get(key));
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
		

	}
}
