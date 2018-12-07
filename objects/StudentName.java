package objects;

public class StudentName implements Comparable<StudentName> {
	private String firstName;
	private String lastName;
	private String middleName;
	
	public StudentName(String firstName, String lastName, String middleName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
	}
	
	public String getFirstName(){
	    return this.firstName;
	}
	
	public String getLastName(){
	    return this.lastName;
	}
	
	public String getMiddleName(){
	    return this.middleName;
	}
	
	public String getFullName(){
	    return this.firstName + " " + this.lastName;
	}


	public int compareTo(StudentName name2) {
		char firstLetter1 = this.firstName.charAt(0);
		char firstLetter2 = name2.firstName.charAt(0);
		if (firstLetter1 > firstLetter2){
			return 1;
		}else if(firstLetter1 > firstLetter2){
			return -1;
		}else{
			return 0;
		}
	}
}