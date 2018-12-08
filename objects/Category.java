package objects;

import java.util.ArrayList;
import java.util.List;

public class Category implements Comparable<Category>{
	private String categoryName;
	private int categorySeq;
	private double weight;
	private List<Assignment> assignmentList;
	
	public Category(String categoryName, int categorySeq, double weight) {
		this.categoryName = categoryName;
		this.categorySeq = categorySeq;
		this.weight = weight;
		this.assignmentList = new ArrayList<>();
	}
	
	public String getCategoryName() {
		return this.categoryName;
	}
	
	public void setCategoryName(String category) {
		this.categoryName = category;
	}
	
	public int getCategorySeq() {
		return this.categorySeq;
	}
	
	public void setCategorySeq(int seq) {
		this.categorySeq = seq;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public List<Assignment> getAssignmentList() {
		return this.assignmentList;
	}

	public void setAssignmentList(List<Assignment> assignmentList) {
		this.assignmentList = assignmentList;
	}
	
	public boolean addAssignment(Assignment assignment) {
		return this.assignmentList.add(assignment);
	}
	public boolean deleteAssignment(Assignment assignment) {
		return this.assignmentList.remove(assignment);
	}
	
	public boolean weightEqualsCategoryWeight(){
		double sumOfWeights = 0;
		for(Assignment assignment: this.assignmentList) {
			sumOfWeights += assignment.getWeight();
		}
		if(sumOfWeights == this.weight) {
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public int compareTo(Category compareCategory) {
		return this.getCategorySeq() - compareCategory.getCategorySeq();
	} 
	
}
