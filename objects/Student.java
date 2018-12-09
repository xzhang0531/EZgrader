package objects;
import java.util.ArrayList;
import java.util.List;

public abstract class Student implements Comparable<Student>{
	protected StudentName name;
	protected String buid;
	protected String major;
	protected String college;
	protected Double gpa;
	protected String type;
	protected List<Course> coursesList;

	public Student() {

	}

	public Student(StudentName name, String buid, String major, String college, double gpa, String type){
		this.name = name;
		this.buid = buid;
		this.major = major;
		this.college = college;
		this.gpa = gpa;
		this.type = type;
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
	
	public String getType(){
		return this.type;
	}

	public void setType(String type){
		this.type = type;
	}
	
	public List<Course> getCoursesList(){
		return this.coursesList;
	}

	public void setCoursesList(List<Course> coursesList){
		this.coursesList = coursesList;
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
	
	@Override
	public int compareTo(Student otherStudent) {
		if (this.type.equals(otherStudent.getType())) {
			return 0;
		}
		if (this.type.equals("UG")) {
			return -1;
		} else {
			return 1;
		}
	}
}


 

