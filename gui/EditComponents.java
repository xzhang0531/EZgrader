package gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import objects.Assignment;
import objects.Category;
import objects.Course;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;

public class EditComponents {

	JFrame frame;


	public EditComponents(Course course) {
		initialize(course);
	}


	private void initialize(Course course) {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(54, 81, 270, 314);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(332, 81, 270, 350);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 73, 246, 185);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(602, 81, 258, 314);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		panel_3.setVisible(false);
		
		JButton btnEditCourseComponents = new JButton("Edit Course Components");
		btnEditCourseComponents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				btnEditCourseComponents.setVisible(false);
			}
		});
		btnEditCourseComponents.setBounds(44, 43, 220, 29);
		frame.getContentPane().add(btnEditCourseComponents);
		
		JRadioButton rdbtnHomework = new JRadioButton("Homework");
		rdbtnHomework.setBounds(6, 70, 100, 23);
		panel.add(rdbtnHomework);
		
		JRadioButton rdbtnQuizzes = new JRadioButton("Quizzes");
		rdbtnQuizzes.setBounds(6, 105, 141, 23);
		panel.add(rdbtnQuizzes);
		
		JRadioButton rdbtnProjects = new JRadioButton("Projects");
		rdbtnProjects.setBounds(6, 140, 141, 23);
		panel.add(rdbtnProjects);
		
		JRadioButton rdbtnExams = new JRadioButton("Exams");
		rdbtnExams.setBounds(6, 175, 141, 23);
		panel.add(rdbtnExams);
		
		JRadioButton rdbtnFinal = new JRadioButton("Final");
		rdbtnFinal.setBounds(6, 210, 141, 23);
		panel.add(rdbtnFinal);
		
		JRadioButton rdbtnOther = new JRadioButton("Other");
		rdbtnOther.setBounds(6, 245, 141, 23);
		panel.add(rdbtnOther);
		
		JLabel lblAddComponents = new JLabel("Add Components");
		lblAddComponents.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblAddComponents.setBounds(6, 19, 205, 39);
		panel.add(lblAddComponents);
		List<String> Weight = new LinkedList<>();
		JButton btnSetWeights = new JButton("Set Weights");
		btnSetWeights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> checkedOff = new ArrayList<String>();
				if(rdbtnHomework.isSelected()) {
					checkedOff.add(rdbtnHomework.getText());
				}
				if(rdbtnQuizzes.isSelected()) {
					checkedOff.add(rdbtnQuizzes.getText());
				}
				if(rdbtnProjects.isSelected()) {
					checkedOff.add(rdbtnProjects.getText());
				}
				if(rdbtnExams.isSelected()) {
					checkedOff.add(rdbtnExams.getText());
				}
				if(rdbtnFinal.isSelected()) {
					checkedOff.add(rdbtnFinal.getText());
				}
				if(rdbtnOther.isSelected()) {
					checkedOff.add(rdbtnOther.getText());
				}
				
				
				int increment = 25;

				for(String title : checkedOff) {
					JLabel label_sw = new JLabel();
					label_sw.setText(title);
					JTextField jfield_sw = new JTextField(20);
					jfield_sw.setName(title);

					
					label_sw.setBounds(10, 0+increment, 100, 19);
					jfield_sw.setBounds(110, 0+increment, 100, 19);
					

					
					panel_2.add(label_sw);
					panel_2.add(jfield_sw);				
					increment += 25;
					Weight.add(title);
				}
				
				panel.setVisible(false);
				panel_1.setVisible(true);
				
			}
		});
		
		btnSetWeights.setBounds(0, 279, 130, 29);
		panel.add(btnSetWeights);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(135, 279, 130, 29);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Welcome w = new Welcome();
				w.frame.setVisible(true);
			}
		});
		panel.add(btnCancel);
	
		
		JLabel lblSetWeightsFor = new JLabel("Set Weights");
		lblSetWeightsFor.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblSetWeightsFor.setBounds(6, 19, 246, 34);
		panel_1.add(lblSetWeightsFor);
		
		JButton button = new JButton("Cancel");
		button.setBounds(0, 299, 180, 29);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Welcome w = new Welcome();
				w.frame.setVisible(true);
			}
		});
		panel_1.add(button);
		

		
		JLabel lblPleaseUseDecimal = new JLabel("Please use decimal units (i.e. /1.00)");
		lblPleaseUseDecimal.setBounds(0, 51, 269, 16);
		panel_1.add(lblPleaseUseDecimal);
		
		List<String> Weights = new LinkedList<>();
		List<String[]> weightPair = new LinkedList<>();
		JButton btnFinalizeChanges = new JButton("Finalize Changes");
		btnFinalizeChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = panel_2.getComponentCount();
				double totalWeight = 0;
			    for(String title : Weight) {
			    		for (int i = 0; i < count; i++) {
			    			Object obj = panel_2.getComponent(i);
				    		if (obj instanceof JTextField) {
				    			JTextField text = (JTextField) obj;
				    			if (text.getName().equals(title)) {
				    				weightPair.add(new String[] {title, text.getText()});
				    				try {
				    					totalWeight += Double.parseDouble(text.getText());
				    				}catch(Exception e1) {
				    					JOptionPane.showMessageDialog(frame, "Invalid values");
				    					weightPair.removeAll(weightPair);
				    			    	return;
				    				}
				    				
				    			}
				    		}
			    		}
			    }
			    
			    if(1 - totalWeight > 1e-6 || totalWeight - 1 > 1e-6) {
			    	JOptionPane.showMessageDialog(frame, "Weights must add up to one!");
			    	weightPair.removeAll(weightPair);
			    	return;
			    }
				
				List<String> checkedOff = new ArrayList<String>();
				if(rdbtnHomework.isSelected()) {
					checkedOff.add(rdbtnHomework.getText());
				}
				if(rdbtnQuizzes.isSelected()) {
					checkedOff.add(rdbtnQuizzes.getText());
				}
				if(rdbtnProjects.isSelected()) {
					checkedOff.add(rdbtnProjects.getText());
				}
				if(rdbtnExams.isSelected()) {
					checkedOff.add(rdbtnExams.getText());
				}
				if(rdbtnFinal.isSelected()) {
					checkedOff.add(rdbtnFinal.getText());
				}
				if(rdbtnOther.isSelected()) {
					checkedOff.add(rdbtnOther.getText());
				}
				int increment = 25;

				
				for(String title : checkedOff) {
					JLabel label_ms = new JLabel();
					label_ms.setText(title +" 1");
					JTextField jfield_ms = new JTextField(20);
					jfield_ms.setName(title +" 1");
					

					
					label_ms.setBounds(6, 60+increment, 100, 19);
					jfield_ms.setBounds(100, 60+increment, 100, 19);
					

					
					panel_3.add(label_ms);
					panel_3.add(jfield_ms);
					
					increment += 25;
					Weights.add(title +" 1");


				}
				
			
				panel_1.setVisible(false);
				panel_3.setVisible(true);
				

			}
		});
		btnFinalizeChanges.setBounds(0, 263, 180, 29);
		panel_1.add(btnFinalizeChanges);
		
		
		JLabel lblMaxScore = new JLabel("Max Score");
		lblMaxScore.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblMaxScore.setBounds(6, 19, 246, 34);
		panel_3.add(lblMaxScore);
		
		JLabel lblInitializeTheFirst = new JLabel("Initialize the first assignments ");
		lblInitializeTheFirst.setBounds(6, 51, 246, 22);
		panel_3.add(lblInitializeTheFirst);
		
		JButton btnFinalizeChanges_1 = new JButton("Add Students");
		List<String[]> scorePair = new LinkedList<>();
		List<Category> components = new LinkedList<>();
		btnFinalizeChanges_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int count = panel_3.getComponentCount();
			    for(String title : Weights) {
			    		for (int i = 0; i < count; i++) {
			    			Object obj = panel_3.getComponent(i);
				    		if (obj instanceof JTextField) {
				    			JTextField text = (JTextField) obj;
				    			if (text.getName().equals(title)) {
				    				try {
				    					Double.parseDouble(text.getText());
				    					scorePair.add(new String[] {title, text.getText()});
				    				}catch(Exception e1) {
				    					scorePair.removeAll(scorePair);
				    					JOptionPane.showMessageDialog(frame, "Invalid values");
				    					return;
				    					
				    				}
				    				
				    			}
				    		}
			    		}
			    }

			    int index = 0;
				for (String[] cur: weightPair) {
					Category category = new Category(cur[0], index, Double.parseDouble(cur[1]), Double.parseDouble(cur[1]));
					Assignment assignment = new Assignment(scorePair.get(index)[0], cur[0], 1, 1, Double.parseDouble(scorePair.get(index)[1]), 0);
					category.addAssignment(assignment);
					components.add(category);
					index++;
				}
				for (Category category : components) {
					course.addCategory(category);
				}

				frame.dispose();
				AddStudent frame2 = new AddStudent(course);
				frame2.frame.setVisible(true);
			}
		});
		
		btnFinalizeChanges_1.setBounds(6, 279, 165, 29);
		panel_3.add(btnFinalizeChanges_1);
		System.out.println(course.getCourseName().getSection());
		
	}
}
