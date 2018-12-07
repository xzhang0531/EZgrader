package objects;

public class StudentName implements Comparable<StudentName> {
	public String firstName;
	public String lastName;
	public String middleName;

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