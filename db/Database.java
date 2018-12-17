package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import objects.Student;
import objects.StudentName;
import objects.UnderGraduate;
import objects.Assignment;
import objects.Category;
import objects.Course;
import objects.Graduate;
import objects.Score;

public class Database {
	
	public List<Student> studentList;
	public List<Course> courseList;
	Connection conn;
	
	public void connect() {
		String user = "root";
		String pwd = "sss5533";
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection conn=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/ezgrader?autoReconnect=true&useSSL=false",user,pwd);
			this.conn = conn;
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	public void disconnect() {
		try {
			this.conn.close();
		} catch (Exception e) {}
	}
	
	
	//use it when you want to wipe out all data from database.
	public void dropEntireDb() {
		Connection conn = this.conn;
		Statement stmt = null;
		try{
			conn.setAutoCommit(false);
			stmt=conn.createStatement();
			stmt.executeUpdate("delete from Enrollment");
			stmt.executeUpdate("delete from AssignmentScore");
			stmt.executeUpdate("delete from Assignment");
			stmt.executeUpdate("delete from Category");
			stmt.executeUpdate("delete from Course");
			stmt.executeUpdate("delete from Student");
			conn.commit();
		}catch(Exception e){ 
			System.out.println(e);
		}finally {
			try{
				if (stmt != null) stmt.close();
				conn.setAutoCommit(true);
			} catch(Exception e1){}
		}
	}

	//insert some fake data into SQL database.
	public void insertFakeData() {
		Connection conn = this.conn;
		Statement stmt = null;
		try{
			conn.setAutoCommit(false);
			stmt=conn.createStatement();
			//fake student data
			stmt.executeUpdate("INSERT INTO Student (buid, fname, lname, stu_type, major, college, gpa) "
					+ "VALUES ('U77094012', 'Kevin', 'Durant', 'G', 'computer scinece', 'GRS', 3.43)");
			stmt.executeUpdate("INSERT INTO Student (buid, fname, lname, stu_type, major, college, gpa) "
					+ "VALUES ('U12344456', 'Lebron', 'James', 'UG', 'Computer Engineering', 'ENG', 3.8)");
			//fake course data
			stmt.executeUpdate("INSERT INTO Course (coursecode, coursename, semester, year, collegecode, section) "
					+ "VALUES ('CS591', 'OOD', 'Fall', 2019, 'CAS', 'A1')");
			ResultSet rs = stmt.executeQuery("SELECT MAX(courseid) AS id FROM Course");
			int lastid = 0;
			if(rs.next()) {
				lastid = rs.getInt("id");
			}
			String courseid = Integer.toString(lastid);
			
			//fake enrollment data
			stmt.executeUpdate("INSERT INTO Enrollment (buid, courseid) "
					+ "VALUES ('U12344456', " + courseid + ")");
			stmt.executeUpdate("INSERT INTO Enrollment (buid, courseid) "
					+ "VALUES ('U77094012', " + courseid + ")");
			
			//fake category data
			stmt.executeUpdate("INSERT INTO Category (courseid, gweight, ugweight, categoryname, categoryseq) "
					+ "VALUES (" + courseid + ", 0.4, 0.4, 'Assignment', 0)");
			stmt.executeUpdate("INSERT INTO Category (courseid, gweight, ugweight, categoryname, categoryseq) "
					+ "VALUES (" + courseid + ", 0.6, 0.6, 'Exam', 1)");
			
			//fake assignment data
			
			stmt.executeUpdate("INSERT INTO Assignment (courseid, gweight, ugweight, assignmentname, assignmentseq, categoryname, maxraw, curve) "
					+ "VALUES (" + courseid + ", 0.2, 0.2, 'Assignment1', 0, 'Assignment', 100.0, 2.0)");
			stmt.executeUpdate("INSERT INTO Assignment (courseid, gweight, ugweight, assignmentname, assignmentseq, categoryname, maxraw, curve) "
					+ "VALUES (" + courseid + ", 0.2, 0.2, 'Assignment2', 1, 'Assignment', 100.0, 0)");
			stmt.executeUpdate("INSERT INTO Assignment (courseid, gweight, ugweight, assignmentname, assignmentseq, categoryname, maxraw, curve) "
					+ "VALUES (" + courseid + ", 0.6, 0.6, 'Final', 2, 'Exam', 100.0, 0)");
			//fake score data
			stmt.executeUpdate("INSERT INTO AssignmentScore (buid, courseid, assignmentname, pointslost, comment) "
					+ "VALUES ('U77094012', " + courseid + ", 'Assignment1', 21.0, 'sick')");
			stmt.executeUpdate("INSERT INTO AssignmentScore (buid, courseid, assignmentname, pointslost) "
					+ "VALUES ('U77094012', " + courseid + ", 'Assignment2', 17.0)");
			stmt.executeUpdate("INSERT INTO AssignmentScore (buid, courseid, assignmentname, pointslost) "
					+ "VALUES ('U77094012', " + courseid + ", 'Final', 9.0)");
			stmt.executeUpdate("INSERT INTO AssignmentScore (buid, courseid, assignmentname, pointslost) "
					+ "VALUES ('U12344456', " + courseid + ", 'Assignment1', 23.0)");
			stmt.executeUpdate("INSERT INTO AssignmentScore (buid, courseid, assignmentname, pointslost) "
					+ "VALUES ('U12344456', " + courseid + ", 'Assignment2', 16.0)");
			stmt.executeUpdate("INSERT INTO AssignmentScore (buid, courseid, assignmentname, pointslost) "
					+ "VALUES ('U12344456', " + courseid + ", 'Final', 8.0)");
			
			
			conn.commit();
			
		}catch(Exception e){ 
			System.out.println(e);
		}finally {
			try {
				if (stmt != null) stmt.close();
				conn.setAutoCommit(true);
			}catch(Exception e) {}
		}
	}
	

	//use it to load the newest data from SQL database to this.studentList and this.courseList
	public void updateDB() {
		Connection conn = this.conn;
		this.studentList = new ArrayList<Student>();
		this.courseList = new ArrayList<Course>();
		Statement stmt = null;
		Statement stmt2 = null;
		Statement stmt3 = null;
		Statement stmt4 = null;
		Statement stmt5 = null;
		Statement stmt6 = null;
		try{
			//Add students to list
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from Student"); 			
			while(rs.next()) {
				String buid = rs.getString(1);
				String fname = rs.getString(2);
				String lname = rs.getString(3);
				String mname = rs.getString(4);
				String major = rs.getString(5);
				String college = rs.getString(6);
				double gpa = rs.getDouble(7);
				String spec = rs.getString(8);
				String stu_type = rs.getString(9);
				String stu_year = rs.getString(10);
				String undergradmajor = rs.getString(11);
				StudentName name = new StudentName(fname, lname, mname);
				if(stu_type.equals("G")) {
					Student newStudent = new Graduate(name, buid, major, college, gpa, undergradmajor, spec);
					studentList.add(newStudent);
				}
				if(stu_type.equals("UG")) {
					Student newStudent = new UnderGraduate(name, buid, major, college, gpa, stu_year);
					studentList.add(newStudent);
				}				
			}
			//Add courses to list
			stmt2=conn.createStatement();
			ResultSet rs2=stmt2.executeQuery("select * from Course");
			while(rs2.next()) {
				int courseid = rs2.getInt(1);
				String coursecode = rs2.getString(2);
				String semester = rs2.getString(3);
				String coursename = rs2.getString(4);
				String collegecode = rs2.getString(5);
				String year = rs2.getString(6);
				String section = rs2.getString(7);
				Course newCourse = new Course(courseid, coursename, coursecode, section, semester, year, collegecode);
				courseList.add(newCourse);
			}
			//Add category to list
			stmt3=conn.createStatement();
			ResultSet rs3=stmt3.executeQuery("select * from Category");
			while(rs3.next()) {
				int courseid = rs3.getInt(1);
				double gweight = rs3.getDouble(2);
				double ugweight = rs3.getDouble(3);
				String categoryname = rs3.getString(4);
				int categoryseq = rs3.getInt(5);
				
				Category newCategory = new Category(categoryname, categoryseq, gweight, ugweight);
				for(Course course: courseList) {
					if(course.getCourseId() == courseid) course.addCategory(newCategory);
				}	
			}

			//Add assignments to list
			stmt4=conn.createStatement();
			ResultSet rs4=stmt4.executeQuery("select * from Assignment");
			while(rs4.next()) {
				int courseid = rs4.getInt(1);
				double gweight = rs4.getDouble(2);
				double ugweight = rs4.getDouble(3);
				String assignmentname = rs4.getString(4);
				int assignmentseq = rs4.getInt(5);
				String categoryname = rs4.getString(6);
				double maxraw = rs4.getDouble(7);
				double curve = rs4.getDouble(8);
				Assignment newAssign = new Assignment(assignmentname, categoryname, gweight, ugweight, maxraw, curve);
				newAssign.setAssignmentSeq(assignmentseq);
				for(Course course: courseList) {
					if(course.getCourseId() == courseid) {
						for(Category category:course.getCategoryList()) {
							if(category.getCategoryName().equals(categoryname)) {
								category.addAssignment(newAssign);
							}
						}
					}
				}	
			}

			//Add scores to list
			stmt5=conn.createStatement();
			ResultSet rs5=stmt5.executeQuery("select * from AssignmentScore");
			while(rs5.next()) {
				String buid = rs5.getString(1);
				int courseid = rs5.getInt(2);
				String assignmentname = rs5.getString(3);
				double pointslost = rs5.getDouble(4);
				String comment = rs5.getString(5);
				
				Score newScore = new Score(pointslost, comment);
				for(Course course: courseList) {
					if(course.getCourseId() == courseid) {
						for(Category category: course.getCategoryList()) {
							for(Assignment assignment:category.getAssignmentList()) {
								if(assignment.getAssignmentName().equals(assignmentname)) {
									newScore.setPercentage(newScore.calculatePercentage(assignment.getMaxScore(), assignment.getCurvedScore()));
									assignment.getScoreList().put(getStudentById(buid), newScore);
								}
							}
						}
					}
				}	
			}
			//Add enrollment to list
			stmt6=conn.createStatement();
			ResultSet rs6=stmt6.executeQuery("select * from Enrollment");
			while(rs6.next()) {
				String buid = rs6.getString(1);
				int courseid = rs6.getInt(2);
				double rawtotal = rs6.getDouble(3);
				for(Student student: this.studentList) {
					if(student.getBuid().equals(buid)) {
						student.addCourse(getCourseById(courseid));
					}
				}
				for(Course course: this.courseList) {
					if(course.getCourseId() == courseid) {
						course.addStudent(getStudentById(buid));
					}
				}
				
			} 

			
		}catch(Exception e){
			System.out.println(e);
		}finally {
			try {
				if (stmt != null) stmt.close();
				if (stmt2 != null) stmt2.close();
				if (stmt3 != null) stmt3.close();
				if (stmt4 != null) stmt4.close();
				if (stmt5 != null) stmt5.close();
				if (stmt6 != null) stmt6.close();
			}catch(Exception e) {}
			
		}
			  
	}
	
	public Student getStudentById(String buid) {
		for (Student student: studentList) {
			if(student.getBuid().equals(buid)) {
				return student;
			}
		}
		return null;
	}
	
	public Course getCourseById(int courseid) {
		for (Course course: courseList) {
			if(course.getCourseId() == courseid) {
				return course;
			}
		}
		return null;
	}
	
	//add a single student to database
	public boolean AddStudent(Student s) {
		PreparedStatement stmt = null;
		try {
			stmt=conn.prepareStatement("INSERT INTO Student (buid, fname, lname, mname, stu_type, major, college, gpa) "
					+ "VALUES (?,?,?,?,?,?,?,?)");
			stmt.setString(1, s.getBuid());
			stmt.setString(2, s.getName().getFirstName());
			stmt.setString(3, s.getName().getLastName());
			stmt.setString(4, s.getName().getMiddleName());
			stmt.setString(5, s.getType());
			stmt.setString(6, s.getMajor());
			stmt.setString(7, s.getCollege());
			stmt.setDouble(8, s.getGpa());
			stmt.executeUpdate();
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			try {
				if (stmt != null) stmt.close();
			}catch(Exception e) {}
		}
	}
	
	//add student with duplicate check
	public boolean AddStudentWithCheck(Student s){
		PreparedStatement stmt = null;
		try {
			stmt=conn.prepareStatement("SELECT * from Student where buid = ?");
			stmt.setString(1, s.getBuid());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return false;
			}else {
				AddStudent(s);
				return true;
			}

		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			try {
				if (stmt != null) stmt.close();
			}catch(Exception e) {}
		}
		
	}
	
	//add a course to database, without category
	public boolean AddCourse(Course c) {
		PreparedStatement stmt = null;
		try {
			stmt=conn.prepareStatement("INSERT INTO Course (coursecode, semester, coursename, collegecode, year, section) "
					+ "VALUES (?,?,?,?,?,?)");
			stmt.setString(1, c.getCourseName().getCode());
			stmt.setString(2, c.getCourseName().getSemester());
			stmt.setString(3, c.getCourseName().getName());
			stmt.setString(4, c.getCourseName().getCollege());
			stmt.setString(5, c.getCourseName().getYear());
			stmt.setString(6, c.getCourseName().getSection());
			stmt.executeUpdate();
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			try {
				if (stmt != null)stmt.close();
			} catch (Exception e) {}
		}
	}
	
	//add a category to database, must provide the courseid to indicate which course it belongs to.
	public boolean AddCategory(Category c, int courseid){
		PreparedStatement stmt = null;
		try {
			stmt=conn.prepareStatement("INSERT INTO Category (courseid, gweight, ugweight, categoryname, categoryseq) "
					+ "VALUES (?,?,?,?,?)");
			stmt.setInt(1, courseid);
			stmt.setDouble(2, c.getGWeight());
			stmt.setDouble(3, c.getUgWeight());
			stmt.setString(4, c.getCategoryName());
			stmt.setInt(5, c.getCategorySeq());
			stmt.executeUpdate();
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			try {
				if (stmt != null) stmt.close();
			}catch(Exception e) {}
		}
	}
	
	//Change category weight, must specify which type of student
	public boolean updateCategoryWeight(String type, int courseid, String categoryname, double newWeight) {
		Statement stmt = null;
		try {
			stmt=conn.createStatement();
			stmt.executeUpdate("UPDATE Category SET "+ type +"weight = " + newWeight + " WHERE courseid = " + courseid + " AND categoryname = '" + categoryname + "'");
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			try {
				if (stmt != null) stmt.close();
			}catch(Exception e) {}
		}
		
	}
	
	//add an assignment to database, must provide the courseid and categoryname to indicate which course it belongs to and which category it belongs to.
	public boolean AddAssignment(Assignment a, int courseid, String categoryname) {
		PreparedStatement stmt = null;
		try {
			stmt=conn.prepareStatement("INSERT INTO Assignment (courseid, gweight, ugweight, assignmentname, assignmentseq, categoryname, maxraw, curve) "
					+ "VALUES (?,?,?,?,?,?,?,?)");
			stmt.setInt(1, courseid);
			stmt.setDouble(2, a.getGWeight());
			stmt.setDouble(3, a.getUgWeight());
			stmt.setString(4, a.getAssignmentName());
			stmt.setInt(5, a.getAssignmentSeq());
			stmt.setString(6, categoryname);
			stmt.setDouble(7, a.getMaxScore());
			stmt.setDouble(8, a.getCurvedScore());
			stmt.executeUpdate();
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			try {
				if (stmt != null) stmt.close();
			}catch(Exception e) {}
		}
	}
	
	
	//Delete an assignment from database
	public boolean deleteAssignment(String assignmentname, int courseid) {
		PreparedStatement stmt = null;
		try {
			stmt=conn.prepareStatement("DELETE FROM Assignment WHERE courseid = ? AND assignmentname = ?");
			stmt.setInt(1, courseid);
			stmt.setString(2, assignmentname);
			stmt.executeUpdate();
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			try {
				if (stmt != null) stmt.close();
			}catch(Exception e) {}
		}
	}
	
	//update the weight of assignment, must specify with type of student
	public boolean updateAssignmentWeight(String type, int courseid, String assignmentname, double newWeight){
		Statement stmt = null;
		try {
			stmt=conn.createStatement();
			stmt.executeUpdate("UPDATE Assignment SET "+ type +"weight = " + newWeight + " WHERE courseid = " + courseid + " AND assignmentname = '" + assignmentname + "'");
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			try {
				if (stmt != null) stmt.close();
			}catch(Exception e) {}
		}
		
	}
	
	
	//Add a curve onto an assignment
	public boolean updateCurve(int courseid, String assignmentname, double curve) {
		PreparedStatement stmt = null;
		try {
			stmt=conn.prepareStatement("UPDATE Assignment SET curve = ? WHERE courseid = ? AND assignmentname = ?");
			stmt.setDouble(1, curve);
			stmt.setInt(2, courseid);
			stmt.setString(3, assignmentname);
			stmt.executeUpdate();
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			try {
				if (stmt != null) stmt.close();
			}catch(Exception e) {}
		}
		
	}
	
	
	//add a score to database, must provide the buid to specify which student, and courseid to specify which course, and assignmentname to specify which assignment.
	public boolean AddScore(Score s, String buid, int courseid, String assignmentname) {
		PreparedStatement stmt = null;
		try {
			stmt=conn.prepareStatement("INSERT INTO AssignmentScore (buid, courseid, assignmentname, pointslost, comment) "
					+ "VALUES (?,?,?,?,?)");
			stmt.setString(1, buid);
			stmt.setInt(2, courseid);
			stmt.setString(3, assignmentname);
			stmt.setDouble(4, s.getPointsLost());
			stmt.setString(5, s.getComment());
			stmt.executeUpdate();
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			try {
				if (stmt != null) stmt.close();
			}catch(Exception e) {}
		}
	}
	
	//update a score, insert if not exist
	public boolean updateScore(String buid, int courseid, String assignmentname, double pointslost){
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		try {
			stmt=conn.prepareStatement("SELECT * from AssignmentScore where buid = ? AND courseid = ? AND assignmentname = ?");
			stmt.setString(1, buid);
			stmt.setInt(2, courseid);
			stmt.setString(3, assignmentname);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				stmt2=conn.prepareStatement("UPDATE AssignmentScore SET pointslost = ? WHERE buid = ? AND courseid = ? AND assignmentname = ?");
				stmt2.setDouble(1, pointslost);
				stmt2.setString(2, buid);
				stmt2.setInt(3, courseid);
				stmt2.setString(4, assignmentname);
				stmt2.executeUpdate();
				return true;
			}else {
				stmt2=conn.prepareStatement("INSERT INTO AssignmentScore (buid, courseid, assignmentname, pointslost) "
						+ "VALUES (?,?,?,?)");
				stmt2.setString(1, buid);
				stmt2.setInt(2, courseid);
				stmt2.setString(3, assignmentname);
				stmt2.setDouble(4, pointslost);
				stmt2.executeUpdate();
				return true;
			}

		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			try {
				if (stmt != null) stmt.close();
				if (stmt2 != null) stmt2.close();
			}catch(Exception e) {}
		}
		
	}
	
	//update a comment of a score
	public boolean updateComment(String buid, int courseid, String assignmentname, String comment) {
		PreparedStatement stmt = null;
		try {
			stmt=conn.prepareStatement("UPDATE AssignmentScore SET comment = ? WHERE buid = ? AND courseid = ? AND assignmentname = ?");
			stmt.setString(1, comment);
			stmt.setString(2, buid);
			stmt.setInt(3, courseid);
			stmt.setString(4, assignmentname);
			stmt.executeUpdate();
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			try {
				if (stmt != null) stmt.close();
			}catch(Exception e) {}
		}
		
	}
	

	//add an enrollment, use it when register a student to a course.
	public boolean AddEnrollment(String buid, int courseid){
		PreparedStatement stmt = null;
		try {
			stmt=conn.prepareStatement("INSERT INTO Enrollment (buid, courseid) VALUES (?,?)");
			stmt.setString(1, buid);
			stmt.setInt(2, courseid);
			stmt.executeUpdate();
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			try {
				if (stmt != null) stmt.close();
			}catch(Exception e) {}
		}
	}
	
	//delete an enrollment, use it when delete registration of a student from a course.
	public boolean deleteEnrollment(String buid, int courseid){
		PreparedStatement stmt = null;
		try {
			stmt=conn.prepareStatement("DELETE FROM Enrollment where buid = ? and courseid = ?");
			stmt.setString(1, buid);
			stmt.setInt(2, courseid);
			stmt.executeUpdate();
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			try {
				if (stmt != null) stmt.close();
			}catch(Exception e) {}
		}
	}
	
	//Add the course structure with a course object
	public boolean AddEverythingByCourse(Course c) {
		Statement stmt = null;
		try {
			conn.setAutoCommit(false);
			//add course
			AddCourse(c);
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(courseid) AS id FROM Course");
			int lastid = 0;
			if(rs.next()) {
				lastid = rs.getInt("id");
			}
			c.setCourseId(lastid);
			//add student
			for(Student s: c.getStudentList()) {
				AddStudentWithCheck(s);
			}
			//add category
			for(Category ca: c.getCategoryList()) {
				AddCategory(ca, c.getCourseId());
			}
			//add assignment
			for(Category ca: c.getCategoryList()) {
				for(Assignment a: ca.getAssignmentList()) {
					AddAssignment(a, c.getCourseId(), ca.getCategoryName());
				}
			}
			//add enrollment
			for(Student s: c.getStudentList()) {
				AddEnrollment(s.getBuid(), c.getCourseId());
			}

			conn.commit();
			return true;
		}catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(e);
			return false;
		}finally {
			try {
				if (stmt != null) stmt.close();
				conn.setAutoCommit(true);
			}catch(Exception e) {}
			
		}
	}
	
	//get the largest current courseid
	public int getLargestCid() {
		Statement stmt = null;
		try {
			stmt = stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(courseid) AS id FROM Course");
			while(rs.next()) {
				return rs.getInt("id");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null) stmt.close();
			}catch(Exception e) {}
		}
		return 0;
	}
}
