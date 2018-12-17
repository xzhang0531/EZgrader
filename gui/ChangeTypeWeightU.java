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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.Database;
import objects.Assignment;
import objects.Category;

public class ChangeTypeWeightU {

	protected JFrame frame;


	public ChangeTypeWeightU(List<Category> categoryList, String category, int courseid) {
		initialize(categoryList, category, courseid);
	}


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
		label_type.setBounds(240, 31, 187, 50);
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

			
			label_sw.setBounds(200, 100+increment, 130, 19);
			jfield_sw.setBounds(340, 100+increment, 130, 19);
			

			
			panel.add(label_sw);
			panel.add(jfield_sw);	

			
			increment += 50;
//			Weight.add(title);
		}
		JButton change = new JButton();
		change.setBounds(200, 100+increment, 130, 29);
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
				    				try {
				    					Double.parseDouble(text.getText());
				    				}catch(Exception e1) {
				    					JOptionPane.showMessageDialog(frame, "Invalid values");
				    					weightMap.clear();
				    					return;
				    				}
				    				weightMap.put(assignment.getAssignmentName(), Double.parseDouble(text.getText()));
				    			}
				    		}
			    		}
				}
				
				double total = 0;
				for(Double value:weightMap.values()) {
					total += value;
				}
				
				if (1 - total > 1e-6 || total - 1 > 1e-6) {
					JOptionPane.showMessageDialog(frame, "Values must be added up to one!");
					weightMap.clear();
					return;
				}
				
				Database db = new Database();
				db.connect();
				for(String key:weightMap.keySet()) {
					
					db.updateAssignmentWeight("ug", courseid, key, weightMap.get(key));
					
				}
				db.disconnect();
				CourseDetail course = new CourseDetail();
				frame.dispose();
				course.run(courseid);
			}});
		panel.add(change);
		
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(340, 100+increment, 130, 29);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CourseDetail c = new CourseDetail();
				c.run(courseid);
			}});
		panel.add(btnCancel);

		frame.getContentPane().add(panel);
	}

}
