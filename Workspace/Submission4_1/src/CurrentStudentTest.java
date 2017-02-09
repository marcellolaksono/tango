import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CurrentStudentTest {

	private static final double TUITION_AMOUNT = 5000.0;
	private static final double DELTA = 0.01;
	private static final int YEAR_OF_STUDY = 1;
	private static final String PROGRAMME = "DUMMY PROGRAMME";
	private static final String STUDENT_NAME = "DUMMY STUDENT NAME";

	private CurrentStudent currentStudent;
	
	@Before
	public void setup() {
		currentStudent = new CurrentStudent(STUDENT_NAME, PROGRAMME, YEAR_OF_STUDY, TUITION_AMOUNT);
	}

	@After
	public void teardown() {
		currentStudent = null;
	}
	
	@Test
	public void testGetDetails() {
		java.text.NumberFormat cf = java.text.NumberFormat.getCurrencyInstance(
		        java.util.Locale.getDefault());
		String tuitionString = cf.format(currentStudent.getTuitionAmount());
		String paymentString = cf.format(currentStudent.getTotalPayments());
		String details = currentStudent.getDetails();

		if (details.indexOf(tuitionString) == -1) {
			Assert.fail("Properly formatted tuition amount not found in getDetails() output");
		} else if (details.indexOf(paymentString) == -1) {
			Assert.fail("Properly formatted payment amount not found in getDetails() output");
		} else if (details.indexOf(STUDENT_NAME) != -1) {
			Assert.fail("Student name found in getDetails() output");
		} else if (details.indexOf(PROGRAMME) != -1) {
			Assert.fail("Student programme of study found in getDetails() output");
		}
	}

	@Test
	public void testGetBalance() {
		Assert.assertEquals("getBalance return value not correct", TUITION_AMOUNT, currentStudent.getBalance(), DELTA);
	}

	@Test
	public void testMakeFullPayment() {
		currentStudent.makePayment(TUITION_AMOUNT);
		Assert.assertEquals("Balance is not zero after full payment", 0.0, currentStudent.getBalance(), DELTA);
	}

	@Test
	public void testMakePartialPayment() {
		currentStudent.makePayment(TUITION_AMOUNT / 2);
		Assert.assertEquals("Balance is not zero after full payment", TUITION_AMOUNT/2, currentStudent.getBalance(), DELTA);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMakeBadPayment() {
		currentStudent.makePayment(TUITION_AMOUNT * 2);
	}

	@Test
	public void testGetYearOfStudy() {
		Assert.assertEquals("Initial year of study is not correct", YEAR_OF_STUDY, currentStudent.getYearOfStudy());
	}

	@Test
	public void testGetTuitionAmount() {
		Assert.assertEquals("Initial tuition amount is not correct", TUITION_AMOUNT, currentStudent.getTuitionAmount(), DELTA);
	}

	@Test
	public void testGetTotalPayments() {
		Assert.assertEquals("Initial total payment amount is not correct", 0.0, currentStudent.getTotalPayments(), DELTA);
	}

	@Test
	public void testSetYearOfStudy() {
		int newYear = 5;
		currentStudent.setYearOfStudy(newYear);
		Assert.assertEquals("Modified year of study is not correct", newYear, currentStudent.getYearOfStudy());
	}

}
