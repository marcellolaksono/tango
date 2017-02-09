package students;

public class CourseResult {
	private CourseSpec spec;
	private String courseyear;
	private Grade studentgrade;
	
	/**
	 * @param spec
	 * @param courseyear
	 * @param studentgrade
	 */
	public CourseResult(CourseSpec spec, String courseyear, Grade studentgrade) {
		this.spec = spec;
		this.courseyear = courseyear;
		this.studentgrade = studentgrade;
	}
	/**
	 * @return the spec
	 */
	public CourseSpec getSpec() {
		return spec;
	}
	/**
	 * @return the courseyear
	 */
	public String getCourseyear() {
		return courseyear;
	}
	/**
	 * @return the studentgrade
	 */
	public Grade getStudentgrade() {
		return studentgrade;
	}
	
}
