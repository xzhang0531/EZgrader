package db;

import java.sql.SQLException;

import objects.Assignment;
import objects.Category;
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
		
		Course a = new Course("Intro to physics", "CS667", "A3", "Fall", "2019", "CAS");
		db.AddCourse(a);
		Category c = new Category("Exam", 0, 0.8);
		db.AddCategory(c, 2);
		Assignment as = new Assignment("Exam1", "Exam", 0.2, 100.0, 2.0);
		
		db.AddAssignment(as, 2, "Exam");
		Score s = new Score(8.0, "");
		db.AddScore(s, "U77094012", 2, "Exam1");
		
	}
}
