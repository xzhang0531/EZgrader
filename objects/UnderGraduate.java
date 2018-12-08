package objects;
import java.util.ArrayList;

public class UnderGraduate extends Student {
	private String year;
	public UnderGraduate(StudentName name, String buid, String major, String college, double gpa, String year){
		this.name = name;
		this.buid = buid;
		this.major = major;
		this.college = college;
		this.gpa = gpa;
		this.type = "UG";
		this.coursesList = new ArrayList<>();
		this.year = year;
	}

	public UnderGraduate(StudentName name, String buid, String major, String college, double gpa){
		this.name = name;
		this.buid = buid;
		this.major = major;
		this.college = college;
		this.gpa = gpa;
		this.type = "UG";
		this.coursesList = new ArrayList<>();
	}

	public String getYear(){
		return this.year;
	}

	public void setYear(String year){
		this.year = year;
	}
}
