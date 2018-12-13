package objects;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Course implements Gradeable{
	private int courseid;
	private CourseName courseName;
	private List<Student> studentList;
	private List<Category> categoryList;
	private Map<Student, Double> finalScoreList;

	
	public Course(String name, String code, String section, String semester, String year, String college) {
		this.studentList =  new ArrayList();
		this.categoryList = new ArrayList();
		this.finalScoreList = new HashMap<>();
		this.courseName = new CourseName(name, code, section, semester, year, college);
	}
	
	public Course(int cid, String name, String code, String section, String semester, String year, String college) {
		this.courseid = cid;
		this.studentList =  new ArrayList();
		this.categoryList = new ArrayList();
		this.finalScoreList = new HashMap<>();
		this.courseName = new CourseName(name, code, section, semester, year, college);
	}
	
	public int getCourseId() {
		return this.courseid;
	}
	
	public void setCourseId(int cid) {
		this.courseid = cid;
	}
	
	public CourseName getCourseName() {
		return this.courseName;
	}
	
	public void setCourseName(CourseName courseName) {
		this.courseName = courseName;
	}

	public List<Student> getStudentList() {
		return this.studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	
	public List<Category> getCategoryList() {
		return this.categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	
	public Map<Student, Double> getFinalScoreList() {
		return this.finalScoreList;
		
	}

	public boolean addStudent(Student s) {
		return this.studentList.add(s);
	}
	public boolean deleteStudent(Student s) {
		return this.studentList.remove(s);
	}
	 
	public boolean addCategory(Category category) {
		return this.categoryList.add(category);
	}
	public boolean deleteCategory(Category category) {
		return this.categoryList.remove(category);
	}
	 
	public boolean weightEqualsOne(){
		double sumOfGWeights = 0;
		double sumOfUgWeights = 0;
		for(Category category: this.categoryList) {
			sumOfGWeights += category.getGWeight();
			sumOfUgWeights += category.getUgWeight();
		}
		if(sumOfGWeights == 1.0 && sumOfUgWeights == 1.0) {
			return true;
		}
		else{
			return false;
		}
	} 
		

	public double getMax() {
		double max = Integer.MIN_VALUE;
		for (Map.Entry<Student, Double> studentFinalScores : finalScoreList.entrySet()) {
			max = Math.max(max, studentFinalScores.getValue());
		}
		return max;
		
	}
	
	public double getMin() {
		double min = Integer.MAX_VALUE;
		for (Map.Entry<Student, Double> studentFinalScores : finalScoreList.entrySet()) {
			min = Math.min(min, studentFinalScores.getValue());
		}
		return min;
	}
	
	public double calculateAverage() {
		double runningSum = 0;
		double numberOfStudents = 0;
		for(Map.Entry<Student, Double> studentFinalScores : finalScoreList.entrySet()) {
			runningSum +=studentFinalScores.getValue();
			numberOfStudents++;
			
		}
		double average = runningSum/numberOfStudents;
		return average;
	}

	public double calculateMedian() {
		double[] scores = new double[finalScoreList.size()];
		int index = 0;
		for (Map.Entry<Student, Double> studentFinalScores : finalScoreList.entrySet()) {
			scores[index++] = studentFinalScores.getValue();
		}
		Arrays.sort(scores);
		if (scores.length % 2 == 0) {
			return (scores[scores.length/2 - 1] +scores[scores.length/2])/2;
		} else {
			return scores[scores.length/2];
		}
	}
	
	public Assignment getAssignment(String assignmentName) {
		for(Category c: categoryList) {
			for(Assignment a: c.getAssignmentList()) {
				if(a.getAssignmentName().equals(assignmentName)) {
					return a;
				}
			}
		}
		return null;
	}
	
	
	public void calculateFinalScore() {
		for(Student s: studentList) {
			String stuType = s.getType();
			double finalScore = 0;
			for(Course c:s.getCoursesList()) {
				if(c.getCourseId() == this.courseid) {
					for(Category ca: c.getCategoryList()) {
						double weight1 = stuType.equals("UG")?ca.getUgWeight():ca.getGWeight();
						for(Assignment a: ca.getAssignmentList()) {
							double weight2 = stuType.equals("UG")?a.getUgWeight():a.getGWeight();
							double score = a.getScoreList().get(s) == null?0:a.getScoreList().get(s).getPercentage();
							double curve = a.getCurvedScore();
							finalScore += (score + curve) * weight2 * weight1;
						}
					}
				}
			}
			finalScoreList.put(s, finalScore);
		}
	}
}


