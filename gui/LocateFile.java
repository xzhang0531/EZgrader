package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import db.Database;
import objects.Category;
import objects.Course;
import objects.Student;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class LocateFile {

	protected JFrame frame;
	private JTextField txtFileLocationHere;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LocateFile window = new LocateFile();
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
	public LocateFile(Course course) {
		initialize(course);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Course course) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 422, 266);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(59, 83, 61, 16);
		panel.add(lblLocation);
		
		txtFileLocationHere = new JTextField();
		txtFileLocationHere.setText("");
		txtFileLocationHere.setBounds(116, 78, 130, 26);
		panel.add(txtFileLocationHere);
		txtFileLocationHere.setColumns(10);
		
		JButton btnUploadStudentsTo = new JButton("Upload Students to Course");
		btnUploadStudentsTo.setBounds(118, 134, 200, 29);
		panel.add(btnUploadStudentsTo);
		btnUploadStudentsTo.setVisible(false);
		
		JButton btnBrowseComputer = new JButton("Browse Computer");
		btnBrowseComputer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser studentFile = new JFileChooser();
				studentFile.showOpenDialog(frame);
				java.io.File chosenFile = studentFile.getSelectedFile();
				txtFileLocationHere.setText(chosenFile.getPath());
				btnUploadStudentsTo.setVisible(true);
				frame.repaint();
				frame.revalidate();
			}
		});
		btnBrowseComputer.setBounds(250, 78, 142, 29);
		panel.add(btnBrowseComputer);
		

		btnUploadStudentsTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File file = new File();
				List<Student> studentsAdded = file.awesomeFileReader(txtFileLocationHere.getText());
//				System.out.println(studentsAdded.size());
				for(Student students : studentsAdded) {
					course.addStudent(students);
					System.out.println(course.getStudentList().size());
					
				}
				Database db =new Database();
				db.connect("root", "sss5533");
				try {
					db.dropEntireDb();
					db.updateDB();
					db.AddEverythingByCourse(course);
					frame.dispose();
					CourseDetail cd = new CourseDetail();
					cd.run();
					
				}catch(Exception err) {
					err.printStackTrace();;
				}		
			}
		});
		btnUploadStudentsTo.setBounds(118, 134, 200, 29);
		panel.add(btnUploadStudentsTo);
	}
}
