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
		

		
	}
}
