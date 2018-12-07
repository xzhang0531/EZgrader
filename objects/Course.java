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
	private List<Assignment> assignmentList;
	private Map<Student, Double> finalScoreList;

	public Course() {
		this.studentList =  new ArrayList();
		this.assignmentList = new ArrayList();
		this.finalScoreList = new HashMap<>();
	}
	
	public Course(String name, String code, String section, String semester, String year, String college) {
		this.studentList =  new ArrayList();
		this.assignmentList = new ArrayList();
		this.finalScoreList = new HashMap<>();
		this.courseName = new CourseName(name, code, section, semester, year, college);
	}
	
	public Course(int cid, String name, String code, String section, String semester, String year, String college) {
		this.courseid = cid;
		this.studentList =  new ArrayList();
		this.assignmentList = new ArrayList();
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
	
	public List<Assignment> getAssignmentList() {
		return this.assignmentList;
	}

	public void setAssignmentList(List<Assignment> assignmentList) {
		this.assignmentList = assignmentList;
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
	 
	public boolean addAssignment(Assignment assignment) {
		return this.assignmentList.add(assignment);
	}
	public boolean deleteAssignment(Assignment assignment) {
		return this.assignmentList.remove(assignment);
	}
	 
	public boolean weightEqualsOne(){
		double sumOfWeights = 0;
		for(Assignment assignment: this.assignmentList) {
			sumOfWeights += assignment.getWeight();
		}
		if(sumOfWeights == 1.0) {
			return true;
		}
		else{
			return false;
		}
	} 
	
	public boolean calculateFinalScore() {

		for(Student student : studentList) {
			double finalGrade = 0.0;
			for(Assignment assignment:  assignmentList) {
				double currentStudentRawScore = student.getAssignmentScore(assignment).calculateScore(assignment.getMaxScore());
				double currentStudentCurvedScore = currentStudentRawScore+assignment.getCurvedScore();
				if(currentStudentCurvedScore > assignment.getMaxScore()) {
					currentStudentCurvedScore = assignment.getMaxScore();
				}
				finalGrade += (currentStudentCurvedScore * assignment.getWeight());
			}
			
			this.finalScoreList.put(student, finalGrade);
		}
		return true;
		
	}
	
	public boolean curve(Assignment assignment , double value) {
		
		assignment.setCurvedScore(value);
		return true;
	}
	
	public boolean changeWeight(Assignment assignment, double newWeight) {
		
		assignment.setWeight(newWeight);
		return true;
		
	}
	
	public boolean changeWeight(String category, double newWeight) {
		double numberOfCategories = 0;
		for(Assignment assignment : assignmentList) {
			if(assignment.getCategory().equals(category)) {
				numberOfCategories++;
			}
		}
		double newCategoryWeight = newWeight/numberOfCategories;
		for(Assignment assignments : assignmentList) {
			if(assignments.getCategory().equals(category)) {
				assignments.setWeight(newCategoryWeight);
			}
		}
		return true;
		
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
			return (scores[scores.length/2] +scores[scores.length/2 + 1])/2;
		} else {
			return scores[scores.length/2];
		}
	}
}


