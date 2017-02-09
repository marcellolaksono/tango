package students.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import students.CourseSpec;
import students.Grade;
import students.StudentRecord;

public class StudentRecordTest {
	
	private StudentRecord studentRecord;
	private CourseSpec bskt100, bskt200, astro100;
	private CourseSpec bskt300;
	
	@Before
	public void setUp() throws Exception {
		bskt100 = new CourseSpec("BSKT", "100", "Basket Weaving", 10);
		bskt200 = new CourseSpec("BSKT", "200", "Advanced Basket Weaving", 10);
		astro100 = new CourseSpec("ASTRO", "100", "Introductory Stargazing", 10);
		bskt300 = new CourseSpec("BSKT", "300", "Basket Weaving Final Project", 20);
		
		studentRecord = new StudentRecord("Firstname Lastname", "BSKT", 3);
		studentRecord.addCourseResult(bskt100, "2015-2016", Grade.G2);
		studentRecord.addCourseResult(astro100, "2014-2015", Grade.D3);
		studentRecord.addCourseResult(bskt100, "2014-2015", Grade.A2);
		studentRecord.addCourseResult(astro100, "2015-2016", Grade.B2);
		studentRecord.addCurrentCourse(bskt200);
	}

	@After
	public void tearDown() throws Exception {
		bskt100 = null;
		bskt200 = null;
		astro100 = null;
		bskt300 = null;
		studentRecord = null;
	}

	@Test
	public void testComputeGPA() {
		Assert.assertEquals("GPA is incorrect", 11.75, studentRecord.computeGPA(), 0.1);
	}

	@Test
	public void testGetPastCourses() {
		List<CourseSpec> courseSpecs = Arrays.asList(bskt100, astro100);
		Assert.assertEquals("List of past courses is incorrect", courseSpecs, studentRecord.getPastCourses());
	}

	@Test
	public void testGetCurrentCourses() {
		List<CourseSpec> courseSpecs = Collections.singletonList(bskt200);
		Assert.assertEquals("List of current courses is incorrect", courseSpecs, studentRecord.getCurrentCourses());
	}
	
	@Test
	public void testGetCurrentCoursesIsImmutable() {
		studentRecord.getCurrentCourses().add(astro100);
		Assert.assertFalse("getCurrentCourses() allows list to be modified", studentRecord.getCurrentCourses().contains(astro100));
	}
	
	@Ignore("Only valid if you've implemented weighted average")
	@Test
	public void testComputeGPAWeighted() {
		studentRecord.addCourseResult(bskt300, "2015-2016", Grade.B2);
		Assert.assertEquals("Weighted GPA is incorrect", 13.166, studentRecord.computeGPA(), 0.1);
	}
	
}
