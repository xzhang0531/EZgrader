package gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import objects.Graduate;
import objects.Student;
import objects.StudentName;
import objects.UnderGraduate;

public class File {

		List<Student> studentsAdded = new ArrayList<Student>();
		
		public List<Student> awesomeFileReader(String pathOfFile) {
			java.io.File file = new java.io.File(pathOfFile);
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				
				String line = null;
				while((line = reader.readLine())!=null) {
					String[] item = line.split(", ");
					
					StudentName name = new StudentName(item[0], item[2], item[1]);
					if(item[7].equals("Undergraduate")) {
						UnderGraduate student = new UnderGraduate(name, item[3], item[4], item[5], Double.valueOf(item[6]));
						studentsAdded.add(student);
					}
					else if(item[7].equals("Graduate")) {
						Graduate student = new Graduate(name, item[3], item[4], item[5], Double.valueOf(item[6]));
						studentsAdded.add(student);
					}	
				}
				reader.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			return studentsAdded;
		}
}