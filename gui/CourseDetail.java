package gui;


import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import db.Database;
import gui.lib.ColumnGroup;
import gui.lib.GroupableTableHeader;
import gui.lib.TableCellListener;
import objects.Assignment;
import objects.Category;
import objects.Course;
import objects.Student;

public class CourseDetail {
	public JFrame frame;
	private JTabbedPane jTabbedpane = new JTabbedPane();
	
	public void init(Database db) {
		try {
			db.updateDB();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		for (Course course: db.courseList) {
			//add tab
			JPanel currentCoursePanel = new JPanel();
			jTabbedpane.addTab(course.getCourseName().getCode(), currentCoursePanel);
			//create table component
			DefaultTableModel dm = new DefaultTableModel();
			List<String> header = new ArrayList<>();
			List<List<String>> data = new ArrayList<>();
			//sort component
			Collections.sort(course.getCategoryList());
			for(Category c: course.getCategoryList()) {
				Collections.sort(c.getAssignmentList());
			}
			//add table header
			header.add("Name");
			for(Category c: course.getCategoryList()) {
				for(Assignment a: c.getAssignmentList()) {
					header.add("PointsLost");
					header.add("Percentage");
				}
			}
			header.add("Cumulative");
			//add table data
			for(Student s: course.getStudentList()) {
				List<String> rowdata = new ArrayList<>();
				rowdata.add(s.getName().getFullName());
				for(Category c: course.getCategoryList()) {
					for(Assignment a: c.getAssignmentList()) {
						if(a.getScoreList().containsKey(s)) {
							rowdata.add(String.valueOf(a.getScoreList().get(s).getPointsLost()));
							rowdata.add(String.valueOf(a.getScoreList().get(s).getPercentage()));
						}else {
							rowdata.add(null);
							rowdata.add(null);
						}
					}
				}
				data.add(rowdata);
			}

			//create table
			Object[] tableHeader = new Object[header.size()];
			for (int i =0; i < header.size(); i++) tableHeader[i] = header.get(i);
			Object[][] tableData = new Object[data.size()][data.size() == 0?0:data.get(0).size()];
			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).size(); j++) {
					tableData[i][j] = data.get(i).get(j);
				}
			}
			dm.setDataVector(tableData, tableHeader);
			JTable table = new JTable(dm) {
			      protected JTableHeader createDefaultTableHeader() {
			          return new GroupableTableHeader(columnModel);
			      }
			};
			//set group
			TableColumnModel cm = table.getColumnModel();
			GroupableTableHeader t_header = (GroupableTableHeader)table.getTableHeader();
			int columeNum = 1;
			for(Category c: course.getCategoryList()) {
				ColumnGroup categoryGroup = new ColumnGroup(c.getCategoryName() + " (G:" + c.getGWeight()*100 + "%/UG:" + c.getUgWeight()*100 + "%)", 1);
				for(Assignment a: c.getAssignmentList()) {
					ColumnGroup assignmentGroup = new ColumnGroup(a.getAssignmentName(), 2);
					assignmentGroup.add(cm.getColumn(columeNum));
					columeNum += 1;
					assignmentGroup.add(cm.getColumn(columeNum));
					columeNum += 1;
					categoryGroup.add(assignmentGroup);
				}
				t_header.addColumnGroup(categoryGroup);
			}
			//change colume width and alignment
			cm.getColumn(0).setPreferredWidth(150);
			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
			for (int i = 1; i < table.getColumnCount(); i++) {
				cm.getColumn(i).setPreferredWidth(100);
				table.getColumnModel().getColumn(i).setCellRenderer( rightRenderer );
			}
			//add table to panel
			JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			currentCoursePanel.setLayout(null);
			sp.setBounds(50, 50, 1200, 600);
			currentCoursePanel.add(sp);
			//add buttons
			JButton btn_changeweight = new JButton("Change Weight");
	        btn_changeweight.setBounds(100, 700, 150, 30);
	        currentCoursePanel.add(btn_changeweight);
	        JButton btn_calFinal = new JButton("Calculate Final");
	        btn_calFinal.setBounds(300, 700, 150, 30);
	        currentCoursePanel.add(btn_calFinal);
	        JButton btn_printStat = new JButton("Print Statistics");
	        btn_printStat.setBounds(500, 700, 150, 30);
	        currentCoursePanel.add(btn_printStat);
			//table listener
	        Action action = new AbstractAction()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	                TableCellListener tcl = (TableCellListener)e.getSource();
	                int row = tcl.getRow();
	                int column = tcl.getColumn();
	                double oldval = Double.parseDouble((String) tcl.getOldValue());
	                try {
		                double newval = Double.parseDouble((String) tcl.getNewValue());
	                }catch(Exception err) {
	                	JOptionPane.showMessageDialog(frame, "Invalid value");
	                	return;
	                }
	                
	                
	            }
	        };
	        TableCellListener tcl = new TableCellListener(table, action);
			
		}
	}
	
	public static void main(String[] args) {
		Database db = new Database();
		db.connect("root", "sss5533");
		CourseDetail cd = new CourseDetail();
		cd.init(db);
		cd.frame = new JFrame();
		cd.frame.setContentPane(cd.jTabbedpane);
		cd.frame.setSize(1300, 900);
		cd.frame.setVisible(true);
		
	}
}
