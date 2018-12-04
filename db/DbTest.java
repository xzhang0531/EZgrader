package db;

import objects.Assignment;
import objects.Course;
import objects.CourseName;
import objects.Score;

public class DbTest {
	public static void main(String[] args) {
		Database db = new Database();
		db.connect("root", "sss5533");
		db.dropEntireDb();
		db.insertFakeData();
		db.updateDB();
		
		CourseName cn = new CourseName("123", "fsdf", "sd", "evv", "2019", "wf");
		Course a = new Course(cn);
		db.AddCourse(a);
		Assignment as = new Assignment("name", "cate", 0.2, 100.0, 2.0);
		db.AddAssignment(as, 1);
		Score s = new Score(8.0, "");
		db.AddScore(s, "U77094012", 1, "name");
		

		System.out.println(db.studentList.size());
		System.out.println(db.courseList.size());
		System.out.println(db.courseList.get(0).assignmentList.size());
		System.out.println(db.courseList.get(0).assignmentList.get(0).getScoreList().size());
	}
}
