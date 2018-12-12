package gui.lib;

import java.awt.Color;
import java.awt.Component;
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

public class ButtonColumn extends AbstractCellEditor implements
	TableCellRenderer, TableCellEditor, ActionListener {
	JTable table;
	String text;
	JFrame frame;
	Course course;
	
	public ButtonColumn(JTable table, int column, JFrame frame, Course course) {
		super();
		this.table = table;
		this.frame = frame;
		this.course = course;
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
			renderButton.setBackground(table.getSelectionBackground());
		} else {
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		}

		if(value == null || value.equals("") || value.equals("null")) {
			
		} else {
			ImageIcon imageIcon = new ImageIcon("/home/xzhang/EZgrader/gui/img/info.png");
			Image image = imageIcon.getImage();
			Image newimg = image.getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			renderButton.setIcon(imageIcon);
		}
		

		renderButton.setBackground(Color.white);
		renderButton.setBorder(BorderFactory.createEmptyBorder());;
		return renderButton;
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		JButton editButton = new JButton();
		editButton.setFocusPainted(false);
		editButton.addActionListener(this);
		text = (value == null) ? "" : value.toString();
		editButton.setText(text);
		return editButton;
	}

	public Object getCellEditorValue() {
		return text;
	}

	public void actionPerformed(ActionEvent e) {
		fireEditingStopped();
		JDialog d = new JDialog(frame, "Comment"); 
		JPanel p = new JPanel();
		p.setLayout(null);
		
		String comment = (String) table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn());
		if(comment != null) comment = comment.equals("null")?"":comment;
		
		//textarea
		JTextArea textArea = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(30, 30, 420, 300);
		textArea.setEditable(true);
		textArea.setText(comment);
		p.add(scrollPane);
		//buttons
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(100, 380, 100, 30);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buid = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
				String assignmentname = "";
				
				int assignIdx = (table.getSelectedColumn() - 5)/3;
				int currIdx = 0;
				for(Category c: course.getCategoryList()) {
					for(Assignment a: c.getAssignmentList()) {
						if(currIdx == assignIdx) {
							assignmentname = a.getAssignmentName();
							currIdx += 1;
						}else {
							currIdx += 1;
						}
					}
				}
				Database db = new Database();
				db.connect("root", "sss5533");
				try {
					db.updateComment(buid, course.getCourseId(), assignmentname, textArea.getText());
					db.updateDB();
					Course newestCourse = db.getCourseById(course.getCourseId());
					String newComment = newestCourse.getAssignment(assignmentname).getScoreList().get(db.getStudentById(buid)).getComment();
					table.getModel().setValueAt(newComment, table.getSelectedRow(), table.getSelectedColumn());
					d.setVisible(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		p.add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(270, 380, 100, 30);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.setVisible(false);
			}
		});
		p.add(btnCancel);
		
		
		d.add(p);
		d.setLocation(400, 200);
		d.setSize(500, 500); 
		d.setVisible(true); 
	}
}
