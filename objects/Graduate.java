package objects;
import java.util.ArrayList;

public class Graduate extends Student {
	private String undergraduateMajor; 
	private String specialization;
	
	public Graduate(StudentName name, String buid, String major, String college, double gpa){
		this.name = name;
		this.buid = buid;
		this.major = major;
		this.college = college;
		this.gpa = gpa;
		this.type = "G";
		this.coursesList = new ArrayList<>();
	}
	
	public Graduate(StudentName name, String buid, String major, String college, double gpa, String undergraduateMajor, String specialization){
		this.name = name;
		this.buid = buid;
		this.major = major;
		this.college = college;
		this.gpa = gpa;
		this.type = "G";
		this.coursesList = new ArrayList<>();
		this.undergraduateMajor = undergraduateMajor; 
		this.specialization = specialization;
	}

	public String getUndergraduateMajor(){
		return this.undergraduateMajor;
	}

	public void setUndergraduateMajor(String major){
		this.undergraduateMajor = major;
	}

	public String getSpecialization(){
		return this.specialization;
	}

	public void setSpecialization(String specialization){
		this.specialization = specialization;
	}

	
}
