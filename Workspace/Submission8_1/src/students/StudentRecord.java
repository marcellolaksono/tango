package students;

import java.util.List;

/**
 * A class representing a student record.
 * 
 * @author mefoster
 */
public class StudentRecord {

	/** The student name */
	private String name;
	/** The student ID */
	private int id;
	/** The degree programme */
	private String degreeProgramme;
	/** The year of study */
	private int yearOfStudy;

	/** Static field to ensure unique IDs */
	private static int NEXT_ID = 1000;
	public void addCurrentCourse (CourseSpec course){
		
	}
	
	public List<CourseSpec> getCurrentCourses(){
		return null;
		
	}
	
	public void addCourseResult (CourseSpec spec, String year, Grade grade)
	{
		
	}
	
	
	public List<CourseSpec> getPastCourses(){
		return null;
	}
	/**
	 * Creates a new StudentRecord with the given properties and a uniquely
	 * generated ID.
	 * 
	 * @param name
	 *            Student name
	 * @param degreeProgramme
	 *            Degree programme
	 * @param yearOfStudy
	 *            Year of study
	 */
	public StudentRecord(String name, String degreeProgramme, int yearOfStudy) {
		this.name = name;
		this.degreeProgramme = degreeProgramme;
		this.yearOfStudy = yearOfStudy;
		this.id = NEXT_ID++;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the degreeProgramme
	 */
	public String getDegreeProgramme() {
		return degreeProgramme;
	}

	/**
	 * @return the yearOfStudy
	 */
	public int getYearOfStudy() {
		return yearOfStudy;
	}

}
