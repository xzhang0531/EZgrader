package gui;



import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import db.Database;
import gui.lib.BuidColumn;
import gui.lib.ColumnGroup;
import gui.lib.CommentColumn;
import gui.lib.GroupableTableHeader;
import gui.lib.TableCellListener;
import objects.Assignment;
import objects.Category;
import objects.Course;
import objects.Student;

public class CourseDetail {
	public JFrame frame;
	private JTabbedPane jTabbedpane = new JTabbedPane();

	

	
	public void init(Database db, int cid) {
		try {
			db.updateDB();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		for (Course course: db.courseList) {

			//add tab
			JPanel currentCoursePanel = new JPanel();
			jTabbedpane.addTab(course.getCourseName().getCode(), currentCoursePanel);
			if(course.getCourseId() == cid) jTabbedpane.setSelectedComponent(currentCoursePanel);
			
			
			//logo
			
			ImageIcon buLogo = new ImageIcon("gui/img/BU.PNG");
			Image buLogoImage = buLogo.getImage();
			Image newBuLogoImage = buLogoImage.getScaledInstance(150, 80,  java.awt.Image.SCALE_SMOOTH);
			buLogo = new ImageIcon(newBuLogoImage);
			JLabel buLogoLabel = new JLabel(buLogo);
			buLogoLabel.setBounds(1100, 18, buLogo.getIconWidth(), buLogo.getIconHeight());
			currentCoursePanel.add(buLogoLabel);
			
			
			ImageIcon logo = new ImageIcon("gui/img/exgrader.png");
			Image logoImage = logo.getImage();
			Image newLogoImage = logoImage.getScaledInstance(616, 90,  java.awt.Image.SCALE_SMOOTH);
			logo = new ImageIcon(newLogoImage);		
			JLabel logoLabel = new JLabel(logo);
			logoLabel.setBounds(634, 12, logo.getIconWidth(), logo.getIconHeight());
			currentCoursePanel.add(logoLabel);
			

			
			//control panels
			JPanel Settings = new JPanel();
			Settings.setLayout(null);
			Settings.setBounds(49, 10, 272, 90);
			Border blackline = BorderFactory.createLineBorder(Color.GRAY);
			Settings.setBorder(BorderFactory.createTitledBorder(blackline, "Course Settings"));
			currentCoursePanel.add(Settings);
			
			
			
			JPanel Summarize = new JPanel();
			Summarize.setLayout(null);
			Summarize.setBounds(330, 10, 144, 90);
			Summarize.setBorder(BorderFactory.createTitledBorder(blackline, "Summarize Data"));
			currentCoursePanel.add(Summarize);
			
			JPanel Command = new JPanel();
			Command.setLayout(null);
			Command.setBounds(483, 10, 136, 90);
			Command.setBorder(BorderFactory.createTitledBorder(blackline, "Command"));
			currentCoursePanel.add(Command);
			
			//add buttons
			
			JButton btn_addStudent = new JButton();
			ImageIcon imageIcon = new ImageIcon("gui/img/plus.png");
			Image image = imageIcon.getImage();
			Image newimg = image.getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			btn_addStudent.setIcon(imageIcon);
			btn_addStudent.setBounds(80, 183, 20, 20);
			btn_addStudent.setContentAreaFilled(false);
			btn_addStudent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					AddSingleStudent a = new AddSingleStudent(db, course);
					a.frame.setVisible(true);
				}
			});
			currentCoursePanel.add(btn_addStudent);
			
			
			JButton btn_deleteStudent = new JButton();
			ImageIcon imageIcon2 = new ImageIcon("gui/img/minus.png");
			Image image2 = imageIcon2.getImage();
			Image newimg2 = image2.getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH);
			imageIcon2 = new ImageIcon(newimg2);
			btn_deleteStudent.setIcon(imageIcon2);
			btn_deleteStudent.setBounds(102, 183, 20, 20);
			btn_deleteStudent.setContentAreaFilled(false);
			btn_deleteStudent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					DeleteStudent d = new DeleteStudent(db, course);
					d.frame.setVisible(true);
				}
			});
			currentCoursePanel.add(btn_deleteStudent);
			
			
			
			JButton btn_curveScore = new JButton("Curve");
			btn_curveScore.setBounds(138, 20, 125, 25);
			btn_curveScore.setFont(new Font("Arial", Font.BOLD, 9));
			Settings.add(btn_curveScore);
			btn_curveScore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					CurveScore frame2 = new CurveScore(course);
					frame2.frame.setVisible(true);
					
				}
			});
			
			
			JButton btn_addComponent = new JButton("Add Component");
			btn_addComponent.setBounds(10, 20, 125, 25);
			btn_addComponent.setFont(new Font("Arial", Font.BOLD, 9));
			Settings.add(btn_addComponent);
			btn_addComponent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					AddComponent addcomponent = new AddComponent(course);
					addcomponent.frame.setVisible(true);
					
				}
			});
			
			JButton btn_deleteComponent = new JButton("Delete Component");
			btn_deleteComponent.setBounds(10, 50, 125, 25);
			btn_deleteComponent.setFont(new Font("Arial", Font.BOLD, 9));
			Settings.add(btn_deleteComponent);
			btn_deleteComponent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					DeleteComponent btn_deleteComponent = new DeleteComponent(db, course);
					btn_deleteComponent.frame.setVisible(true);
					
				}
			});
			
			
			JButton btn_logout = new JButton("Logout");
			btn_logout.setBounds(10, 50, 117, 25);
			btn_logout.setFont(new Font("Arial", Font.BOLD, 9));
			Command.add(btn_logout);
			btn_logout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					Login l = new Login();
					l.frame.setVisible(true);
				}
			});
			
			
			JButton btn_back = new JButton("Back");
			btn_back.setBounds(10, 20, 117, 25);
			btn_back.setFont(new Font("Arial", Font.BOLD, 9));
			Command.add(btn_back);
			btn_back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					Welcome w = new Welcome();
					w.frame.setVisible(true);
				}
			});
			
		
			JButton btn_changeweight = new JButton("Change Weight");
			btn_changeweight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					ChangeWeight frame2 = new ChangeWeight(course);
					frame2.frame.setVisible(true);
				}
			});
			btn_changeweight.setBounds(138, 50, 125, 25);
			btn_changeweight.setFont(new Font("Arial", Font.BOLD, 9));
			Settings.add(btn_changeweight);
			
			
			
			
			JButton btn_printStat = new JButton("Print Statistics");
			btn_printStat.setBounds(10, 50, 125, 25);
			btn_printStat.setFont(new Font("Arial", Font.BOLD, 9));
			btn_printStat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					db.updateDB();
					Course newestCourse = null;
					for(Course c: db.courseList) {
						if(c.getCourseId() == course.getCourseId()) {
							newestCourse = c;
							newestCourse.calculateFinalScore();
						}
					}
					JDialog d = new JDialog(frame, "Statistic"); 
					JPanel p = new JPanel();
					p.setLayout(null);
					
					
					JLabel lblPrintCourseStatistics = new JLabel("Course Stats for:");
					JLabel lblNoStuLabel = new JLabel("Number of Students:");
					JLabel lblFinalScoreAverage = new JLabel("Final Score Average:");
					JLabel lblFinalScoreMedian = new JLabel("Final Score Median:");
					JLabel lblMaximumScore = new JLabel("Maximum Score:");
					JLabel lblMinimumScore = new JLabel("Minimum Score:");
					
					JTextField courseNameText = new JTextField(newestCourse.getCourseName().getName());
					JTextField sizeText = new JTextField(String.valueOf(newestCourse.getFinalScoreList().size()));
					JTextField averageText = new JTextField(String.format("%.2f", newestCourse.calculateAverage()));
					JTextField medianText = new JTextField(String.format("%.2f", newestCourse.calculateMedian()));
					JTextField maximumText = new JTextField(String.format("%.2f", newestCourse.getMax()));
					JTextField minimumText = new JTextField(String.format("%.2f", newestCourse.getMin()));

					lblPrintCourseStatistics.setBounds(98, 20, 150, 26);
					lblNoStuLabel.setBounds           (72, 65, 150, 16);
					lblFinalScoreAverage.setBounds    (73, 105, 150, 16);
					lblFinalScoreMedian.setBounds     (79, 145, 150, 16);
					lblMaximumScore.setBounds         (103, 185, 150, 16);
					lblMinimumScore.setBounds         (107, 225, 150, 16);
					courseNameText.setBounds          (228, 20, 130, 26);
					sizeText.setBounds                (228, 60, 130, 26);
					averageText.setBounds             (228, 100, 130, 26);
					medianText.setBounds              (228, 140, 130, 26);
					maximumText.setBounds             (228, 180, 130, 26);
					minimumText.setBounds             (228, 220, 130, 26);
										
					p.add(lblPrintCourseStatistics);						
					p.add(lblNoStuLabel);
					p.add(lblFinalScoreAverage);
					p.add(lblFinalScoreMedian);
					p.add(lblMaximumScore);
					p.add(lblMinimumScore);
					p.add(courseNameText);
					p.add(sizeText);
					p.add(averageText);
					p.add(medianText);
					p.add(maximumText);
					p.add(minimumText);
					
					courseNameText.setEditable(false);
					sizeText.setEditable(false);
					averageText.setEditable(false);
					medianText.setEditable(false);
					maximumText.setEditable(false);
					minimumText.setEditable(false);
					

					JButton btnBack = new JButton("Back");
					btnBack.setBounds(170, 275, 75, 29);
					btnBack.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							d.setVisible(false);
						}
					});
					p.add(btnBack);
					
					d.add(p);
					d.setLocation(400, 200);
					d.setSize(450, 400); 
					d.setVisible(true);
				}
			});
			Summarize.add(btn_printStat);
			//add table to panel
			JTable table = createTable(course, db);
			table.getTableHeader().setResizingAllowed(false);
			JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			sp.setBounds(50, 130, 1200, 600);
			currentCoursePanel.setLayout(null);
			currentCoursePanel.add(sp);
			//calc final
			JButton btn_calFinal = new JButton("Calculate Final");
			btn_calFinal.setBounds(10, 20, 125, 25);
			btn_calFinal.setFont(new Font("Arial", Font.BOLD, 9));
			btn_calFinal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					db.updateDB();
					Course newestCourse = null;
					for(Course c: db.courseList) {
						if(c.getCourseId() == course.getCourseId()) {
							newestCourse = c;
							newestCourse.calculateFinalScore();
						}
					}
					for(int i = 0; i < table.getRowCount(); i++) {
						String buid = (String) table.getModel().getValueAt(i, 0);
						for (Student student: newestCourse.getStudentList()) {
							if(student.getBuid().equals(buid)) {
								double finalScore = newestCourse.getFinalScoreList().get(student);
								String score = String.format("%.2f", finalScore);
								table.getModel().setValueAt(score, i, table.getColumnCount()-1);
							}
						}
					}
				}
			});
			Summarize.add(btn_calFinal);
			
		}
	}

	public JTable createTable(Course course, Database db) {
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
		header.add("BUID");
		header.add("Name");
		header.add("Type");
		for(Category c: course.getCategoryList()) {
			for(Assignment a: c.getAssignmentList()) {
				header.add("PointsLost");
				header.add("Percentage");
				header.add("C");
			}
		}
		header.add("Cumulative");
		//add table data
		Collections.sort(course.getStudentList());
		for(Student s: course.getStudentList()) {
			List<String> rowdata = new ArrayList<>();
			rowdata.add(s.getBuid());
			rowdata.add(s.getName().getFullName());
			rowdata.add(s.getType());
			for(Category c: course.getCategoryList()) {
				for(Assignment a: c.getAssignmentList()) {
					if(a.getScoreList().containsKey(s)) {
						rowdata.add(String.valueOf(a.getScoreList().get(s).getPointsLost()));
						rowdata.add(String.format("%.2f", a.getScoreList().get(s).getPercentage()));
						rowdata.add(String.valueOf(a.getScoreList().get(s).getComment()));
					}else {
						rowdata.add(null);
						rowdata.add(null);
						rowdata.add(null);
					}
				}
			}
			data.add(rowdata);
		}

		//create table
		Object[] tableHeader = new Object[header.size()];
		for (int i = 0; i < header.size(); i++) tableHeader[i] = header.get(i);
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
		int columeNum = 3;
		for(Category c: course.getCategoryList()) {
			ColumnGroup categoryGroup = new ColumnGroup(c.getCategoryName() + " (G:" + String.format("%.1f", c.getGWeight()*100) + "%/UG:" + String.format("%.1f", c.getUgWeight()*100) + "%)", 1);
			for(Assignment a: c.getAssignmentList()) {
				ColumnGroup assignmentGroup = new ColumnGroup(a.getAssignmentName()+ " (G:" + String.format("%.1f", a.getGWeight()*100) + "%/UG:" + String.format("%.1f", a.getUgWeight()*100) + "%)", 2);
				assignmentGroup.add(cm.getColumn(columeNum));
				columeNum += 1;
				assignmentGroup.add(cm.getColumn(columeNum));
				columeNum += 1;
				assignmentGroup.add(cm.getColumn(columeNum));
				columeNum += 1;
				categoryGroup.add(assignmentGroup);
			}
			t_header.addColumnGroup(categoryGroup);
		}
		//change colume width and alignment
		cm.getColumn(0).setPreferredWidth(100);
		cm.getColumn(1).setPreferredWidth(150);
		cm.getColumn(2).setPreferredWidth(50);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		cm.getColumn(2).setCellRenderer(centerRenderer);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
		for (int i = 3; i < table.getColumnCount(); i++) {
			if(i%3 == 2) {
				cm.getColumn(i).setPreferredWidth(16);
				table.getColumnModel().getColumn(i).setCellRenderer( rightRenderer );
			}else {
				cm.getColumn(i).setPreferredWidth(105);
				table.getColumnModel().getColumn(i).setCellRenderer( rightRenderer );
			}
			
		}
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//table listener
		Action action = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				TableCellListener tcl = (TableCellListener)e.getSource();
				int row = tcl.getRow();
				int column = tcl.getColumn();

				String oldVal = (String) tcl.getOldValue();
				String newVal;
				if(column == 0 || column == 1 || column == 2 || column % 3 == 1 || column == table.getColumnCount() - 1) {
					JOptionPane.showMessageDialog(frame, "This cell cannot be changed!");
					dm.setValueAt(oldVal, row, column);
					return;
				}
				
				if(column % 3 == 0) {
					try {
						newVal = (String) tcl.getNewValue();
						Double.parseDouble(newVal);
					}catch(Exception err) {
						JOptionPane.showMessageDialog(frame, "Invalid value");
						dm.setValueAt(oldVal, row, column);
						return;
					}
					String buid = (String) dm.getValueAt(row, 0);
					int courseid = course.getCourseId();
					String assignmentname = "";
					int assignIdx = (column - 3)/3;
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
					db.updateScore(buid, courseid, assignmentname, Double.parseDouble(newVal));
					db.updateDB();
					Course newestCourse = db.getCourseById(courseid);
					double newPercentage = newestCourse.getAssignment(assignmentname).getScoreList().get(db.getStudentById(buid)).getPercentage();
					dm.setValueAt(String.format("%.2f", newPercentage), row, column + 1);
				}
			}
		};
		TableCellListener tcl = new TableCellListener(table, action);
		//comment column
		
		for(int i = 5; i < table.getColumnCount(); i+=3) {
			CommentColumn buttonsColumn = new CommentColumn(table, i, frame, course);
		}
		//buid column
		BuidColumn buidColumn = new BuidColumn(table, 0, frame);
		
		return table;
	}
	
	public void run(int cid) {
		Database db = new Database();
		db.connect();
		CourseDetail cd = new CourseDetail();
		cd.init(db, cid);
		cd.frame = new JFrame();
		cd.frame.setContentPane(cd.jTabbedpane);
		cd.frame.setSize(1300, 900);
		cd.frame.setVisible(true);
	}
	
	
	

	public static void main(String[] args) {
		CourseDetail cd = new CourseDetail();
		cd.run(11);

	}
}
