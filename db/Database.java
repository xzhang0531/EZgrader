package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import objects.Student;
import objects.StudentName;
import objects.UnderGraduate;
import objects.Assignment;
import objects.Course;
import objects.CourseName;
import objects.Graduate;
import objects.Score;

public class Database {
	
	public List<Student> studentList;
	public List<Course> courseList;
	Connection conn;
	
	public void connect(String user, String pwd) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection conn=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/ezgrader",user,pwd);
			this.conn = conn;
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//use it when you want to wipe out all data from database.
	public void dropEntireDb() throws SQLException{
		Connection conn = this.conn;
		Statement stmt = null;
		try{
			conn.setAutoCommit(false);
			stmt=conn.createStatement();
			stmt.executeUpdate("delete from Enrollment");
			stmt.executeUpdate("delete from AssignmentScore");
			stmt.executeUpdate("delete from Assignment");
			stmt.executeUpdate("delete from Course");
			stmt.executeUpdate("delete from Student");
			conn.commit();
		}catch(Exception e){ 
			System.out.println(e);
		}finally {
			if (stmt != null) stmt.close();
			conn.setAutoCommit(true);
		}
	}

	//insert some fake data into SQL database.
	public void insertFakeData() throws SQLException {
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
			//fake assignment data
			ResultSet rs = stmt.executeQuery("SELECT MAX(courseid) AS id FROM Course");
			int lastid = 0;
			if(rs.next()) {
				lastid = rs.getInt("id");
			}
			String courseid = Integer.toString(lastid);
			stmt.executeUpdate("INSERT INTO Assignment (courseid, weight, componentname, category, maxraw, curve) "
					+ "VALUES (" + courseid + ", 0.2, 'Assignment1', 'Assignment', 100.0, 2.0)");
			stmt.executeUpdate("INSERT INTO Assignment (courseid, weight, componentname, category, maxraw, curve) "
					+ "VALUES (" + courseid + ", 0.2, 'Assignment2', 'Assignment', 100.0, 0)");
			stmt.executeUpdate("INSERT INTO Assignment (courseid, weight, componentname, category, maxraw, curve) "
					+ "VALUES (" + courseid + ", 0.6, 'Final', 'Exam', 100.0, 0)");
			//fake score data
			stmt.executeUpdate("INSERT INTO AssignmentScore (buid, courseid, componentname, pointslost) "
					+ "VALUES ('U77094012', " + courseid + ", 'Assignment1', 21.0)");
			stmt.executeUpdate("INSERT INTO AssignmentScore (buid, courseid, componentname, pointslost) "
					+ "VALUES ('U77094012', " + courseid + ", 'Assignment2', 17.0)");
			stmt.executeUpdate("INSERT INTO AssignmentScore (buid, courseid, componentname, pointslost) "
					+ "VALUES ('U77094012', " + courseid + ", 'Final', 9.0)");
			stmt.executeUpdate("INSERT INTO AssignmentScore (buid, courseid, componentname, pointslost) "
					+ "VALUES ('U12344456', " + courseid + ", 'Assignment1', 23.0)");
			stmt.executeUpdate("INSERT INTO AssignmentScore (buid, courseid, componentname, pointslost) "
					+ "VALUES ('U12344456', " + courseid + ", 'Assignment2', 16.0)");
			stmt.executeUpdate("INSERT INTO AssignmentScore (buid, courseid, componentname, pointslost) "
					+ "VALUES ('U12344456', " + courseid + ", 'Final', 8.0)");
			//fake enrollment data
			stmt.executeUpdate("INSERT INTO Enrollment (buid, courseid) "
					+ "VALUES ('U12344456', " + courseid + ")");
			stmt.executeUpdate("INSERT INTO Enrollment (buid, courseid) "
					+ "VALUES ('U77094012', " + courseid + ")");
			
			conn.commit();
			
		}catch(Exception e){ 
			System.out.println(e);
		}finally {
			if (stmt != null) stmt.close();
			conn.setAutoCommit(true);
		}
	}
	

	//use it to load the newest data from SQL database to this.studentList and this.courseList
	public void updateDB() throws SQLException {
		Connection conn = this.conn;
		this.studentList = new ArrayList<Student>();
		this.courseList = new ArrayList<Course>();
		Statement stmt = null;
		Statement stmt2 = null;
		Statement stmt3 = null;
		Statement stmt4 = null;
		Statement stmt5 = null;
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
				CourseName cname = new CourseName(coursename, coursecode, section, semester, year, collegecode);
				Course newCourse = new Course(courseid, cname);
				courseList.add(newCourse);
			}
			//Add assignments to list
			stmt3=conn.createStatement();
			ResultSet rs3=stmt3.executeQuery("select * from Assignment");
			while(rs3.next()) {
				int courseid = rs3.getInt(1);
				double weight = rs3.getDouble(2);
				String componentname = rs3.getString(3);
				String category = rs3.getString(4);
				double maxraw = rs3.getDouble(5);
				double curve = rs3.getDouble(6);
				Assignment newAssign = new Assignment(componentname, category, weight, maxraw, curve);
				for(Course course: courseList) {
					if(course.courseid == courseid) course.addAssignment(newAssign);
				}	
			}
			//Add scores to list
			stmt4=conn.createStatement();
			ResultSet rs4=stmt4.executeQuery("select * from AssignmentScore");
			while(rs4.next()) {
				String buid = rs4.getString(1);
				int courseid = rs4.getInt(2);
				String componentname = rs4.getString(3);
				double pointslost = rs4.getDouble(4);
				String comment = rs4.getString(5);
				
				Score newScore = new Score(pointslost, comment);
				for(Course course: courseList) {
					if(course.courseid == courseid) {
						for(Assignment assignment:course.assignmentList) {
							if(assignment.getName().equals(componentname)) {
								assignment.getScoreList().put(getStudentById(buid), newScore);
							}
						}
					}
				}	
			}
			//Add enrollment to list
			stmt5=conn.createStatement();
			ResultSet rs5=stmt5.executeQuery("select * from Enrollment");
			while(rs5.next()) {
				String buid = rs5.getString(1);
				int courseid = rs5.getInt(2);
				double rawtotal = rs5.getDouble(3);
				for(Student student: this.studentList) {
					if(student.getBuid().equals(buid)) {
						student.addCourse(getCourseById(courseid));
					}
				}
				for(Course course: this.courseList) {
					if(course.courseid == courseid) {
						course.addStudent(getStudentById(buid));
					}
				}
				
			} 
		}catch(Exception e){
			System.out.println(e);
		}finally {
			if (stmt != null) stmt.close();
			if (stmt2 != null) stmt2.close();
			if (stmt3 != null) stmt3.close();
			if (stmt4 != null) stmt4.close();
			if (stmt5 != null) stmt5.close();
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
			if(course.courseid == courseid) {
				return course;
			}
		}
		return null;
	}
	
	//add a single graduate student to database
	public boolean AddGraduateStudent(Student s) throws SQLException {
		Statement stmt = null;
		try {
			stmt=conn.createStatement();
			stmt.executeUpdate("INSERT INTO Student (buid, fname, lname, stu_type, major, college, gpa) "
					+ "VALUES ('" + s.getBuid() + "', '" + s.getName().firstName + "', '" + s.getName().lastName + "', 'G', '" + s.getMajor() + "', '" + s.getCollege() + "', " + s.getGpa() + ")");
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			if (stmt != null) stmt.close();
		}
	}
	
	//add a single undergraduate student to database
	public boolean AddUnderGraduateStudent(Student s) throws SQLException {
		Statement stmt = null;
		try {
			stmt=conn.createStatement();
			stmt.executeUpdate("INSERT INTO Student (buid, fname, lname, stu_type, major, college, gpa) "
					+ "VALUES ('" + s.getBuid() + "', '" + s.getName().firstName + "', '" + s.getName().lastName + "', 'UG', '" + s.getMajor() + "', '" + s.getCollege() + "', " + s.getGpa() + ")");
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			if (stmt != null) stmt.close();
		}
	}
	
	//add a course to database, with out assignments
	public boolean AddCourse(Course c) throws SQLException {
		Statement stmt = null;
		try {
			stmt=conn.createStatement();
			stmt.executeUpdate("INSERT INTO Course (coursecode, semester, coursename, collegecode, year, section) "
					+ "VALUES ('" + c.getCourseName().getCode() + "', '" + c.getCourseName().getSemester() + "', '" + c.getCourseName().getName() + "', '" + c.getCourseName().getCollege() + "', " + c.getCourseName().getYear() + ", '" + c.getCourseName().getSection() + "')");
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			if (stmt != null) stmt.close();
		}
	}
	
	//add a assignment to database, must provide the courseid to indicate which course it belongs to.
	public boolean AddAssignment(Assignment a, int courseid) throws SQLException {
		Statement stmt = null;
		try {
			stmt=conn.createStatement();
			stmt.executeUpdate("INSERT INTO Assignment (courseid, weight, componentname, category, maxraw, curve) "
					+ "VALUES (" + courseid + ", " + a.getWeight() + ", '" + a.getName() + "', '" + a.getCategory() + "', " + a.getMaxScore() + ", " + a.getCurvedScore() + ")");
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			if (stmt != null) stmt.close();
		}
	}
	
	//add a score to database, must provide the buid to specify which student, and courseid to specify which course, and componentname to specify which assignment.
	public boolean AddScore(Score s, String buid, int courseid, String componentname) throws SQLException {
		Statement stmt = null;
		try {
			stmt=conn.createStatement();
			stmt.executeUpdate("INSERT INTO AssignmentScore (buid, courseid, componentname, pointslost, comment) "
					+ "VALUES ('" + buid + "', " + courseid + ", '" + componentname + "', " + s.getPointsLost() + ", '" + s.getComment() + "')");
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			if (stmt != null) stmt.close();
		}
	}
	
	//add an enrollment, use it when register a student to a course.
	public boolean AddEnrollment(String buid, int courseid) throws SQLException {
		Statement stmt = null;
		try {
			stmt=conn.createStatement();
			stmt.executeUpdate("INSERT INTO Enrollment (buid, courseid) "
					+ "VALUES ('" + buid + ", " + courseid + ")");
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}finally {
			if (stmt != null) stmt.close();
		}
	}
}
