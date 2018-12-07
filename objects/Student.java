package objects;
import java.util.ArrayList;
import java.util.List;

public abstract class Student {
	protected StudentName name;
	protected String buid;
	protected String major;
	protected String college;
	protected Double gpa;
	protected List<Course> coursesList;

	public Student() {

	}

	public Student(StudentName name, String buid, String major, String college, double gpa){
		this.name = name;
		this.buid = buid;
		this.major = major;
		this.college = college;
		this.gpa = gpa;
		this.coursesList = new ArrayList<>();
	}

	public StudentName getName(){
		return this.name;
	}

	public void setName(StudentName name){
		this.name = name;
	}

		public String getBuid(){
		return this.buid;
	}

	public void setBuid(String id){
		this.buid = id;
	}

	public String getMajor(){
		return this.major;
	}

	public void setMajor(String major){
		this.major = major;
	}

	public String getCollege(){
		return this.college;
	}

	public void setCollege(String college){
		this.college = college;
	}

	public Double getGpa(){
		return this.gpa;
	}

	public void setGpa(double gpa){
		this.gpa = gpa;
	}

	public boolean addCourse(Course course){
		return this.coursesList.add(course);
	}

	public Double getFinalScore(Course course){
		return course.getFinalScoreList().get(this);
	}

	public Score getAssignmentScore(Assignment assignment){
		return assignment.getScoreList().get(this);
	}
}


 

