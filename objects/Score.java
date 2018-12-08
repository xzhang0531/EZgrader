package objects;

public class Score implements Comparable<Score>{
	private double pointsLost;
	private double percentage;
	private String comment;


	public Score(double pointsLost, String comment) {
		this.pointsLost = pointsLost;
		this.comment = comment;
		this.percentage = 0;
	}

	public double getPointsLost() {
		return pointsLost;
	}

	public void setPointsLost(double pointsLost) {
		this.pointsLost = pointsLost;
	}

	public double getPercentage() {
		return this.percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean hasComment() {
		return !comment.equals("");
	}



	public double calculateScore(double maxScore) {
		return maxScore - this.pointsLost;
	}

	public double calculatePercentage(double maxScore) {
		double percentage = (maxScore - this.pointsLost) / maxScore * 100;
		setPercentage(percentage);
		return percentage;
	}

	public int compareTo(Score two) {
		return (int)(this.percentage - two.percentage);
	}
}
