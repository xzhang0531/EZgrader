package gui.lib;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import db.Database;
import gui.CourseDetail;
import objects.Assignment;
import objects.Category;
import objects.Course;
import objects.Score;
import objects.Student;

public class BuidColumn extends AbstractCellEditor implements
	TableCellRenderer, TableCellEditor, ActionListener {
	JTable table;
	String text;
	JFrame frame;
	Database db;
	
	public BuidColumn(JTable table, int column, JFrame frame) {
		super();
		this.table = table;
		this.frame = frame;
		this.db = new Database();
		try {
			db.connect("root", "sss5533");
			db.updateDB();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer(this);
		columnModel.getColumn(column).setCellEditor(this);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JButton renderButton = new JButton();
		if (hasFocus) {
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		} else if (isSelected) {
			renderButton.setForeground(table.getSelectionForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		} else {
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		}
		
		renderButton.setText((value == null) ? "" : value.toString());

		renderButton.setBackground(new Color(255, 255, 220));
		renderButton.setBorder(BorderFactory.createEmptyBorder());;
		return renderButton;
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		JButton editButton = new JButton();
		editButton.setFocusPainted(false);
		editButton.addActionListener(this);
		text = (value == null) ? "" : value.toString();
		editButton.setText((value == null) ? "" : value.toString());
		return editButton;
	}

	public Object getCellEditorValue() {
		return text;
	}

	public void actionPerformed(ActionEvent e) {
		fireEditingStopped();
		JDialog d = new JDialog(frame, "Student Information"); 
		JPanel p = new JPanel();
		p.setLayout(null);
		
		
		Student student = null;
		for(Student s : db.studentList) {
			if(s.getBuid().equals((String)table.getModel().getValueAt(table.getSelectedRow(), 0))) {
				student = s;
			}
		}
		
		JLabel lblNewLabel = new JLabel("Student Information");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(59, 6, 191, 16);
		p.add(lblNewLabel);
		
		JLabel lblStudentName = new JLabel("Student Name:");
		lblStudentName.setBounds(39, 38, 96, 16);
		p.add(lblStudentName);
		
		JLabel lblBuid = new JLabel("BUID:");
		lblBuid.setBounds(91, 75, 38, 16);
		p.add(lblBuid);
		
		JLabel lblNewLabel_1 = new JLabel("Student Type:");
		lblNewLabel_1.setBounds(44, 105, 96, 16);
		p.add(lblNewLabel_1);
		
		JLabel lblMajor = new JLabel("Major:");
		lblMajor.setBounds(88, 165, 44, 16);
		p.add(lblMajor);
		
		JLabel lblCollege = new JLabel("College:");
		lblCollege.setBounds(77, 135, 51, 16);
		p.add(lblCollege);
		
		JLabel lblGpa = new JLabel("GPA:");
		lblGpa.setBounds(94, 195, 33, 16);
		p.add(lblGpa);
		
		
		
		JTextField studentName = new JTextField(student.getName().getFullName());
		studentName.setEditable(false);
		studentName.setBounds(147, 34, 130, 26);
		p.add(studentName);
		studentName.setColumns(10);
		
		JTextField buid = new JTextField(student.getBuid());
		buid.setEditable(false);
		buid.setBounds(147, 70, 130, 26);
		p.add(buid);
		buid.setColumns(10);
		
		JTextField studentType = new JTextField(student.getType());
		studentType.setEditable(false);
		studentType.setBounds(147, 100, 130, 26);
		p.add(studentType);
		studentType.setColumns(10);
		
		JTextField college = new JTextField(student.getCollege());
		college.setEditable(false);
		college.setBounds(147, 130, 130, 26);
		p.add(college);
		college.setColumns(10);
		
		JTextField major = new JTextField(student.getMajor());
		major.setEditable(false);
		major.setBounds(147, 160, 130, 26);
		p.add(major);
		major.setColumns(10);
		
		JTextField gpa = new JTextField(student.getGpa().toString());
		gpa.setEditable(false);
		gpa.setBounds(147, 190, 130, 26);
		p.add(gpa);
		gpa.setColumns(10);
		
		
		
		JButton btnCancel = new JButton("close");
		btnCancel.setBounds(105, 240, 100, 30);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.setVisible(false);
			}
		});
		p.add(btnCancel);
		
		
		d.add(p);
		d.setLocation(400, 200);
		d.setSize(330, 330); 
		d.setVisible(true); 
	}
}
