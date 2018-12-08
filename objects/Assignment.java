package objects;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Assignment implements Gradeable, Comparable<Assignment>{
	
	private String assignmentName;
	private int assignmentSeq;
	private double gWeight;
	private double ugWeight;
	private double maxScore;
	private double curvedScore;
	private HashMap<Student, Score> scoreList;
	
	public Assignment(String name, String category, double gWeight, double ugWeight, double maxScore, double curvedScore){
		this.assignmentName = name;
		this.gWeight = gWeight;
		this.ugWeight = ugWeight;
		this.maxScore = maxScore;
		this.curvedScore = curvedScore;
		this.scoreList = new HashMap<>();
	}
	
	public String getAssignmentName() {
		return this.assignmentName;
	}
	
	public void setAssignmentName(String name) {
		this.assignmentName = name;
	}
	
	public int getAssignmentSeq() {
		return this.assignmentSeq;
	}
	
	public void setAssignmentSeq(int seq) {
		this.assignmentSeq = seq;
	}
	
	
	public double getGWeight() {
		return this.gWeight;
	}
	
	public void setGWeight(double weight) {
		this.gWeight = weight;
	}
	
	public double getUgWeight() {
		return this.ugWeight;
	}
	
	public void setUgWeight(double weight) {
		this.ugWeight = weight;
	}
	
	public double getMaxScore() {
		return this.maxScore;
	}
	
	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}
	
	public double getCurvedScore() {
		return this.curvedScore;
	}
	
	public void setCurvedScore(double curvedScore) {
		this.curvedScore = curvedScore;
	}

	public HashMap<Student, Score> getScoreList() {
		return this.scoreList;
	}
	
	public void setScoreList(HashMap<Student, Score> scoreList) {
		this.scoreList = scoreList;
	}
	
	public double getMax() {
		double max = Integer.MIN_VALUE;
		for (Map.Entry<Student, Score> studentScores : this.scoreList.entrySet()) {
			max = Math.max(max, studentScores.getValue().calculateScore(this.maxScore));
		}
		return max;
	}

	public double getMin() {
		double min = Integer.MAX_VALUE;
		for (Map.Entry<Student, Score> studentScores : this.scoreList.entrySet()) {
			min = Math.min(min, studentScores.getValue().calculateScore(this.maxScore));
		}
		return min;
	}

	public double calculateAverage() {
		double average = 0;
		for (Map.Entry<Student, Score> studentScores : this.scoreList.entrySet()) {
			average += studentScores.getValue().calculateScore(this.maxScore);
		}
		return average / this.scoreList.size();
	}

	public double calculateMedian() {
		double[] scores = new double[this.scoreList.size()];
		int index = 0;
		for (Map.Entry<Student, Score> studentScores : this.scoreList.entrySet()) {
			scores[index++] = studentScores.getValue().calculateScore(this.maxScore);
		}
		Arrays.sort(scores);
		if (scores.length % 2 == 0) {
			return (scores[scores.length/2] +scores[scores.length/2 + 1])/2;
		} else {
			return scores[scores.length/2];
		}
	}



	@Override
	public int compareTo(Assignment compareAssignment) {
		return this.getAssignmentSeq() - compareAssignment.assignmentSeq;
	}
	
}
