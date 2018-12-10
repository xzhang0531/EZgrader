package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.Database;
import objects.Assignment;
import objects.Category;

public class ChangeTypeWeightU {

	protected JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChangeTyperWeightU window = new ChangeTyperWeightU();
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
	public ChangeTypeWeightU(List<Category> categoryList, String category, int courseid) {
		initialize(categoryList, category, courseid);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(List<Category> categoryList, String category, int courseid) {
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
		List<Assignment> assignmentList = new LinkedList<>();

		for (Category cur : categoryList) {
			if (cur.getCategoryName().equals(category)) {
				assignmentList = cur.getAssignmentList();

			}
		}
		int increment = 0;
		for (Assignment assignment : assignmentList) {
			JLabel label_sw = new JLabel();
			label_sw.setText(assignment.getAssignmentName() + " :" + assignment.getUgWeight());
			JTextField jfield_sw = new JTextField(20);
			jfield_sw.setName(assignment.getAssignmentName());

			
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
				List<Assignment> assignmentList2 = new LinkedList<>();

				for (Category cur : categoryList) {
					if (cur.getCategoryName().equals(category)) {
						assignmentList2 = cur.getAssignmentList();

					}
				}
				int count = panel.getComponentCount();
				Map<String, Double> weightMap = new HashMap<>();
				for (Assignment assignment : assignmentList2) {
			    		for (int i = 0; i < count; i++) {
			    			Object obj = panel.getComponent(i);
				    		if (obj instanceof JTextField) {
				    			JTextField text = (JTextField) obj;
				    			if (text.getName().equals(assignment.getAssignmentName())) {
				    				weightMap.put(assignment.getAssignmentName(), Double.parseDouble(text.getText()));
				    			}
				    		}
			    		}
				}
				for(String key:weightMap.keySet()) {
					Database db = new Database();
					db.connect("root", "sss5533");
					try {
						db.updateAssignmentWeight("ug", courseid, key, weightMap.get(key));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
//				for (String s : weightMap.keySet()) {
//					System.out.println(s);
//				}
//				for (Double b : weightMap.values()) {
//					System.out.println(b);
//				}
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
