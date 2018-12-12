package gui;

import java.awt.EventQueue;
import objects.Course;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateCourse {

	protected JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateCourse window = new CreateCourse();
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
	public CreateCourse() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 759, 484);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewCourseCreation = new JLabel("New Course Creation");
		lblNewCourseCreation.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewCourseCreation.setBounds(240, 27, 299, 49);
		frame.getContentPane().add(lblNewCourseCreation);
		
		JLabel lblCourseName = new JLabel("Course Name:");
		lblCourseName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblCourseName.setBounds(48, 116, 113, 29);
		frame.getContentPane().add(lblCourseName);

		
		textField = new JTextField();
		textField.setBounds(157, 118, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblCourseCode = new JLabel("Course Code:");
		lblCourseCode.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblCourseCode.setBounds(48, 204, 113, 29);
		frame.getContentPane().add(lblCourseCode);
		
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(157, 206, 130, 26);
		frame.getContentPane().add(textField_1);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(460, 116, 121, 29);
		frame.getContentPane().add(comboBox);

		comboBox.addItem("ENG");
		comboBox.addItem("CAS");
		comboBox.addItem("COM");
		comboBox.addItem("SAR");
		comboBox.addItem("QST");
		comboBox.addItem("CGS");
		comboBox.addItem("MET");
		comboBox.setSelectedIndex(-1);
		
		
		JLabel lblCollege = new JLabel("College :");
		lblCollege.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblCollege.setBounds(384, 116, 113, 29);
		frame.getContentPane().add(lblCollege);
		
		JLabel lblYear = new JLabel("Year :");
		lblYear.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblYear.setBounds(384, 204, 113, 29);
		frame.getContentPane().add(lblYear);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(460, 204, 121, 29);
		frame.getContentPane().add(comboBox_1);
		comboBox_1.addItem("2019");
		comboBox_1.addItem("2020");
		comboBox_1.addItem("2021");
		comboBox_1.addItem("2022");
		comboBox_1.addItem("2023");
		comboBox_1.addItem("2024");
		comboBox_1.addItem("2025");
		comboBox_1.setSelectedIndex(-1);

		
		JLabel lblSection = new JLabel("Section :");
		lblSection.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblSection.setBounds(62, 278, 113, 29);
		frame.getContentPane().add(lblSection);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.addItem("A1");
		comboBox_2.addItem("A2");
		comboBox_2.setSelectedIndex(-1);
		comboBox_2.setBounds(157, 280, 121, 29);
		frame.getContentPane().add(comboBox_2);
		
		
		

		

		
		JLabel lblSemester = new JLabel("Semester :");
		lblSemester.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblSemester.setBounds(384, 278, 113, 29);
		frame.getContentPane().add(lblSemester);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.addItem("Spring");
		comboBox_3.addItem("Fall");
		comboBox_3.setSelectedIndex(-1);
		comboBox_3.setBounds(460, 281, 121, 29);
		frame.getContentPane().add(comboBox_3);
		
		JButton btnImportSavedSettings = new JButton("Import Saved Settings");
		btnImportSavedSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String college = comboBox.getSelectedItem().toString();
				String year = comboBox_1.getSelectedItem().toString();
				String code = textField_1.getText();
				String section = comboBox_2.getSelectedItem().toString();
				String semester = comboBox_3.getSelectedItem().toString();
				String name = textField.getText();
				Course newCourse = new Course(name, code, section, semester, year, college);
				System.out.print(newCourse.getCourseName().getSection());
				frame.dispose();
				ImportSavedSettings frame2 = new ImportSavedSettings(newCourse);
				frame2.frame.setVisible(true);
			}
		});
		btnImportSavedSettings.setBounds(420, 354, 161, 49);
		frame.getContentPane().add(btnImportSavedSettings);
		
		JButton btnNewButton = new JButton("Use New Setting");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String college = comboBox.getSelectedItem().toString();
				String year = comboBox_1.getSelectedItem().toString();
				String code = textField_1.getText();
				String section = comboBox_2.getSelectedItem().toString();
				String semester = comboBox_3.getSelectedItem().toString();
				String name = textField.getText();
				Course newCourse = new Course(name, code, section, semester, year, college);
				System.out.print(newCourse.getCourseName().getSection());
				frame.dispose();
				EditComponents frame2 = new EditComponents(newCourse);
				frame2.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(157, 354, 143, 49);
		frame.getContentPane().add(btnNewButton);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
