package objects;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Assignment implements Gradeable{
	
	private String name;
	private String category;
	private double weight;
	private double maxScore;
	private double curvedScore;
	private HashMap<Student, Score> scoreList;
	
	public Assignment(String name, String category, double weight, double maxScore){
		this.name = name;
		this.category = category;
		this.weight = weight;
		this.maxScore = maxScore;
		this.curvedScore = 0;
		this.scoreList = new HashMap<>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getMaxScore() {
		return maxScore;
	}
	
	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}
	
	public double getCurvedScore() {
		return curvedScore;
	}
	
	public void setCurvedScore(double curvedScore) {
		this.curvedScore = curvedScore;
	}

	public HashMap<Student, Score> getScoreList() {
		return scoreList;
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
	
}
