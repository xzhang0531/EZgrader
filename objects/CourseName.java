package objects;

public class CourseName {
	private final String name;
	private final String code;
	private final String section;
	private final String semester;
	private final String year;
	private final String college;

	public CourseName(String name, String code, String section, String semester, String year, String college) {
		this.name = name;
		this.code = code;
		this.section = section;
		this.semester = semester;
		this.year = year;
		this.college = college;
	}

	public String getName() {
		return this.name;
	}

	public String getCode() {
		return this.code;
	}

	public String getSection() {
		return this.section;
	}

	public String getSemester() {
		return this.semester;
	}

	public String getYear() {
		return year;
	}

	public String getCollege() {
		return college;
	}
}
