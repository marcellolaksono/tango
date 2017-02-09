import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FormerStudentTest {

	private static final String PROGRAMME = "DUMMY PROGRAMME";
	private static final String STUDENT_NAME = "DUMMY STUDENT NAME";
	private static final int GRAD_YEAR = 2010;
	private static final String DEGREE_CLASS = "2:2";

	private FormerStudent formerStudent;
	
	@Before
	public void setup() {
		formerStudent = new FormerStudent(STUDENT_NAME, PROGRAMME, GRAD_YEAR, DEGREE_CLASS);
	}

	@After
	public void teardown() {
		formerStudent = null;
	}
	
	@Test
	public void testGetDetails() {
		String details = formerStudent.getDetails();

		if (details.indexOf(String.valueOf(GRAD_YEAR)) == -1) {
			Assert.fail("Graduation year not found in getDetails() output");
		} else if (details.indexOf(DEGREE_CLASS) == -1) {
			Assert.fail("Degree class not found in getDetails() output");
		} else if (details.indexOf(STUDENT_NAME) != -1) {
			Assert.fail("Student name found in getDetails() output");
		} else if (details.indexOf(PROGRAMME) != -1) {
			Assert.fail("Student programme of study found in getDetails() output");
		}
	}
	
	@Test
	public void testGetGradYear() {
		Assert.assertEquals("getGraduationYear return value not correct", GRAD_YEAR, formerStudent.getGraduationYear());
	}

	@Test
	public void testGetDegreeClass() {
		Assert.assertEquals("getDegreeClass return value not correct", DEGREE_CLASS, formerStudent.getDegreeClass());
	}

}
