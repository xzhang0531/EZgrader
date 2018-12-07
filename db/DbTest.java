package db;

import java.sql.SQLException;

import objects.Assignment;
import objects.Course;
import objects.CourseName;
import objects.Score;

public class DbTest {
	public static void main(String[] args) throws SQLException {
		Database db = new Database();
		db.connect("root", "sss5533");
		db.dropEntireDb();
		db.insertFakeData();
		db.updateDB();
		
		Course a = new Course("Test", "CS667", "A3", "Fall", "2019", "CAS");
		db.AddCourse(a);
		Assignment as = new Assignment("name", "cate", 0.2, 100.0, 2.0);
		db.AddAssignment(as, 1);
		Score s = new Score(8.0, "");
		db.AddScore(s, "U77094012", 1, "name");
		

		System.out.println(db.studentList.size());
		System.out.println(db.courseList.size());
		System.out.println(db.courseList.get(0).getAssignmentList().size());
		System.out.println(db.courseList.get(0).getAssignmentList().get(0).getScoreList().size());
	}
}
