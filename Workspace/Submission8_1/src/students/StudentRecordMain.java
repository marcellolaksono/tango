package students;

import java.util.stream.Collectors;

/**
 * A simple class with a main method to test the implementation of CourseSpec
 * and StudentRecord.
 * 
 * @author mefoster
 */
public class StudentRecordMain {

	public static void main(String[] args) {
		// Create some course specifications to use below
		CourseSpec bskt100 = new CourseSpec("BSKT", "100", "Basket Weaving", 10);
		CourseSpec bskt200 = new CourseSpec("BSKT", "200", "Advanced Basket Weaving", 10);
		CourseSpec astro100 = new CourseSpec("ASTRO", "100", "Introductory Stargazing", 10);
		CourseSpec bskt100New = new CourseSpec("BSKT", "100", "Introduction to Basket Weaving", 10);

		// Create a student record and give it some past courses
		StudentRecord studentRecord = new StudentRecord("Firstname Lastname", "BSKT", 3);
		studentRecord.addCourseResult(bskt100, "2014-2015", Grade.G2);
		studentRecord.addCourseResult(astro100, "2014-2015", Grade.D3);
		studentRecord.addCourseResult(bskt100New, "2015-2016", Grade.A2);
		studentRecord.addCourseResult(astro100, "2015-2016", Grade.B2);
		// One current course too
		studentRecord.addCurrentCourse(bskt200);

		// Print out the full student record
		System.out.println(studentRecord);

		// Print out all courses taken at least once previously (ID and
		// department only)
		String courseStr = studentRecord.getPastCourses().stream().map(c -> c.getDepartment() + c.getCode())
				.collect(Collectors.joining(", "));
		System.out.println("\nAll previous courses: " + courseStr);
	}
}
