package objects;

import java.util.ArrayList;
import java.util.List;

public class Category implements Comparable<Category>{
	private String categoryName;
	private int categorySeq;
	private double gWeight;
	private double ugWeight;
	private List<Assignment> assignmentList;
	
	public Category(String categoryName, int categorySeq, double gWeight, double ugWeight) {
		this.categoryName = categoryName;
		this.categorySeq = categorySeq;
		this.gWeight = gWeight;
		this.ugWeight = ugWeight;
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
		double sumOfGWeights = 0;
		double sumOfUgWeights = 0;
		for(Assignment assignment: this.assignmentList) {
			sumOfGWeights += assignment.getGWeight();
			sumOfUgWeights += assignment.getUgWeight();
		}
		if(sumOfGWeights == this.gWeight && sumOfUgWeights == this.ugWeight) {
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
