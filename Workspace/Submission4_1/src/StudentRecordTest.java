import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentRecordTest {

	private static final String PROGRAMME = "DUMMY PROGRAMME";
	private static final String STUDENT_NAME = "DUMMY STUDENT NAME";
	private static final String DETAILS = "DETAILS";

	private StudentRecord studentRecord;
	
	@Before
	public void setup() {
		studentRecord = new StudentRecord(STUDENT_NAME, PROGRAMME) {
			@Override
			protected String getDetails() {
				return DETAILS;
			}
		};
	}

	@After
	public void teardown() {
		studentRecord = null;
	}
	
	@Test
	public void testGetStudentNumber() {
		Set<Integer> seenNumbers = new HashSet<>();
		for (int i = 0; i < 1000; i++) {
			StudentRecord sr = new StudentRecord(STUDENT_NAME, PROGRAMME) {
				@Override
				protected String getDetails() {
					return null;
				}
			};
			if (!seenNumbers.add(sr.getStudentNumber())) {
				Assert.fail("Duplicate student numbers found");
			}
		}
	}

	@Test
	public void testGetStudentName() {
		Assert.assertEquals("getStudentName does not return student name", STUDENT_NAME, studentRecord.getStudentName());
	}

	@Test
	public void testGetDegreeProgramme() {
		Assert.assertEquals("getDegreeProgramme does not return degree programme", PROGRAMME, studentRecord.getDegreeProgramme());
	}

	@Test
	public void testSetDegreeProgramme() {
		String newProgramme = "New Programme";
		studentRecord.setDegreeProgramme(newProgramme);
		Assert.assertEquals("getDegreeProgramme does not return degree programme after setDegreeProgramme", newProgramme, studentRecord.getDegreeProgramme());
	}

	@Test
	public void testToString() {
		String output = studentRecord.toString();
		String currencySymbol = Currency.getInstance(Locale.getDefault()).getSymbol();

		// Look for all the things we would expect to find in the output
		if (output.indexOf(STUDENT_NAME) == -1) {
			Assert.fail("Student name not found in toString() output");
		} else if (output.indexOf(PROGRAMME) == -1) {
			Assert.fail("Degree programme not found in toString() output");
		} else if (output.indexOf(DETAILS) == -1) {
			Assert.fail("getDetails() output not found in toString() output");
		} else if (output.indexOf(currencySymbol) != -1) {
			Assert.fail("Currency symbol found in StudentRecord toString() output");
		}
	}

}
