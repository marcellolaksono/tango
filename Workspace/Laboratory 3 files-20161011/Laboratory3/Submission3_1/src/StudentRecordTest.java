import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * set of JUnit tests for Java lab 3 StudentRecord class defined by students.
 * Based largely on the BankAccount tests by Jeremy Singer for the 2014 class.
 *
 * These tests use reflection to invoke methods on StudentRecord - this should
 * prevent Eclipse code completion from automatically creating the empty methods
 * in StudentRecord source code.
 *
 * @author MaryEllen.Foster@glasgow.ac.uk
 */
public class StudentRecordTest {

	private InputStream stdin;
	private PrintStream stdout;
	private ByteArrayOutputStream baos;

	@Before
	public void setup() {
		stdin = System.in;
		stdout = System.out;
		baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(baos));
	}

	@After
	public void teardown() {
		String s = baos.toString();
		if (!s.isEmpty()) {
			Assert.fail("Output detected on System.out during test: \"" + s.trim() + "\"");
		}
		System.setIn(stdin);
		System.setOut(stdout);
	}

	private static final double TUITION_AMOUNT = 1820.0;
	private static final int YEAR_OF_STUDY = 1;
	private static final String PROGRAMME = "Maths";
	private static final String STUDENT_NAME = "Mary";

	private StudentRecord makeDefaultRecord() {
		try {
			return createRecord(STUDENT_NAME, PROGRAMME, YEAR_OF_STUDY, TUITION_AMOUNT);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			Assert.fail("Error creating student record for testing");
			return null;
		}
	}

	private Constructor<StudentRecord> getConstructor() throws NoSuchMethodException, SecurityException {
		return (Constructor<StudentRecord>) StudentRecord.class.getDeclaredConstructor(String.class, String.class,
				int.class, double.class);
	}

	private StudentRecord createRecord(String studentName, String degreeProgramme, int yearOfStudy,
			double tuitionAmount) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		return getConstructor().newInstance(studentName, degreeProgramme, yearOfStudy, tuitionAmount);
	}

	private void testField(String name, Class type) throws Exception {
		Field f = StudentRecord.class.getDeclaredField(name);
		Assert.assertEquals(name + " field type not correct", f.getType(), type);
		Assert.assertTrue(name + " is not private", Modifier.isPrivate(f.getModifiers()));
	}

	@Test
	public void testFields() throws Exception {
		testField("studentNumber", int.class);
		testField("studentName", String.class);
		testField("degreeProgramme", String.class);
		testField("yearOfStudy", int.class);
		testField("tuitionAmount", double.class);
		testField("totalPayments", double.class);
	}

	public void testFieldValue(StudentRecord sr, String name, Object value, String context)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = StudentRecord.class.getDeclaredField(name);
		f.setAccessible(true);
		Object obj = f.get(sr);
		Assert.assertEquals("Value of " + name + " field is not correct after " + context, value, obj);
	}

	@Test
	public void testConstructor() throws Exception {
		try {
			Constructor<StudentRecord> cons = getConstructor();
			Assert.assertTrue("constructor is not public", Modifier.isPublic(cons.getModifiers()));
			StudentRecord sr = makeDefaultRecord();
			// Check the fields after
			testFieldValue(sr, "studentName", STUDENT_NAME, "constructor");
			testFieldValue(sr, "degreeProgramme", PROGRAMME, "constructor");
			testFieldValue(sr, "yearOfStudy", YEAR_OF_STUDY, "constructor");
			testFieldValue(sr, "tuitionAmount", TUITION_AMOUNT, "constructor");
			testFieldValue(sr, "totalPayments", 0.0, "constructor");
		} catch (NoSuchMethodException ex) {
			Assert.fail("StudentRecord constructor not found");
		}
	}

	private void testGetMethod(StudentRecord sr, String methodName, Object expected) throws Exception {
		try {
			Method m = StudentRecord.class.getDeclaredMethod(methodName);
			Assert.assertTrue(methodName + " name method is not public", Modifier.isPublic(m.getModifiers()));
			Class expectedClass = expected.getClass();
			if (expectedClass.equals(Integer.class)) {
				expectedClass = int.class;
			} else if (expectedClass.equals(Double.class)) {
				expectedClass = double.class;
			}
			Assert.assertEquals(methodName + " method does not have correct return type", expectedClass,
					m.getReturnType());
			Assert.assertEquals(methodName + " return value incorrect", expected, m.invoke(sr));
		} catch (NoSuchMethodException e) {
			Assert.fail(methodName + " method not found");
		}
	}

	@Test
	public void testGetters() throws Exception {
		StudentRecord sr = makeDefaultRecord();
		testGetMethod(sr, "getStudentName", STUDENT_NAME);
		testGetMethod(sr, "getDegreeProgramme", PROGRAMME);
		testGetMethod(sr, "getYearOfStudy", YEAR_OF_STUDY);
		testGetMethod(sr, "getTuitionAmount", TUITION_AMOUNT);
		testGetMethod(sr, "getTotalPayments", 0.0);
	}

	@Test
	public void testSetDegreeProgramme() throws Exception {
		StudentRecord sr = makeDefaultRecord();
		try {
			Method m = StudentRecord.class.getDeclaredMethod("setDegreeProgramme", String.class);
			Assert.assertEquals("setDegreeProgramme method does not have void return type", void.class,
					m.getReturnType());
			String newProgramme = "English";
			m.invoke(sr, newProgramme);
			testFieldValue(sr, "degreeProgramme", newProgramme, "setDegreeProgramme");
			testGetMethod(sr, "getDegreeProgramme", newProgramme);
		} catch (NoSuchMethodException ex) {
			Assert.fail("setDegreeProgramme method not found");
		}
	}

	@Test
	public void testSetYearOfStudy() throws Exception {
		StudentRecord sr = makeDefaultRecord();
		try {
			Method m = StudentRecord.class.getDeclaredMethod("setYearOfStudy", int.class);
			Assert.assertEquals("setYearOfStudy method does not have void return type", void.class, m.getReturnType());
			int newYear = 5;
			m.invoke(sr, newYear);
			testFieldValue(sr, "yearOfStudy", newYear, "setYearOfStudy");
			testGetMethod(sr, "getYearOfStudy", newYear);
		} catch (NoSuchMethodException ex) {
			Assert.fail("setYearOfStudy method not found");
		}
	}

	@Test
	public void testUniqueNumbers() throws Exception {
		Set<Integer> seenNumbers = new TreeSet<>();
		try {
			Method m = StudentRecord.class.getDeclaredMethod("getStudentNumber");
			for (int i = 0; i < 1000; i++) {
				StudentRecord sr = createRecord("Student" + i, "Programme" + i, YEAR_OF_STUDY, TUITION_AMOUNT);
				Integer number = (Integer) m.invoke(sr);
				if (seenNumbers.contains(number)) {
					Assert.fail("Found identical student numbers");
				}
				seenNumbers.add(number);
			}
		} catch (NoSuchMethodException ex) {
			Assert.fail("Couldn't access getStudentNumber method");
		}
	}

	@Test
	public void testMakeValidPayment() throws Exception {
		StudentRecord sr = makeDefaultRecord();
		try {
			Method m = StudentRecord.class.getDeclaredMethod("makePayment", double.class);
			Assert.assertTrue("makePayment method is not public", Modifier.isPublic(m.getModifiers()));
			Assert.assertEquals("makePayment return value is incorrect", boolean.class, m.getReturnType());
			double paymentAmount = 500.0;
			Object result = m.invoke(sr, paymentAmount);
			Assert.assertTrue("Return value from successful makePayment is incorrect", (Boolean) result);
			testFieldValue(sr, "totalPayments", paymentAmount, "successful makePayment");
		} catch (NoSuchMethodException ex) {
			Assert.fail("makePayment method not found");
		}
	}

	@Test
	public void testPayFullTuition() throws Exception {
		StudentRecord sr = makeDefaultRecord();
		try {
			Method m = StudentRecord.class.getDeclaredMethod("makePayment", double.class);
			Assert.assertTrue("makePayment method is not public", Modifier.isPublic(m.getModifiers()));
			Assert.assertEquals("makePayment return value is incorrect", boolean.class, m.getReturnType());
			Object result = m.invoke(sr, TUITION_AMOUNT);
			Assert.assertTrue("Return value from successful makePayment is incorrect", (Boolean) result);
			testFieldValue(sr, "totalPayments", TUITION_AMOUNT, "successful makePayment");
		} catch (NoSuchMethodException ex) {
			Assert.fail("makePayment method not found");
		}
	}

	@Test
	public void testMakeInvalidPayment() throws Exception {
		StudentRecord sr = makeDefaultRecord();
		try {
			Method m = StudentRecord.class.getDeclaredMethod("makePayment", double.class);
			Assert.assertTrue("makePayment method is not public", Modifier.isPublic(m.getModifiers()));
			Assert.assertEquals("makePayment return value is incorrect", boolean.class, m.getReturnType());
			Object result = m.invoke(sr, 2000.0);
			Assert.assertFalse("Return value from unsuccessful makePayment is incorrect", (Boolean) result);
			testFieldValue(sr, "totalPayments", 0.0, "unsuccessful makePayment");
		} catch (NoSuchMethodException ex) {
			Assert.fail("makePayment method not found");
		}
	}

	@Test
	public void testGetBalance() throws Exception {
		StudentRecord sr = makeDefaultRecord();
		try {
			Method m = StudentRecord.class.getDeclaredMethod("getBalance");
			Assert.assertTrue("getBalance method is not public", Modifier.isPublic(m.getModifiers()));
			Assert.assertEquals("getBalance return value is incorrect", double.class, m.getReturnType());

			// getBalance() should always equal getTuitionAmount() -
			// getTotalPayments()
			Double expected = TUITION_AMOUNT;
			Object result = m.invoke(sr);
			Assert.assertEquals("getBalance return value is incorrect with zero payments", expected, (Double) result);

			try {
				Method m2 = StudentRecord.class.getDeclaredMethod("makePayment", double.class);
				int firstPayment = 500;
				m2.invoke(sr, firstPayment);
				result = m.invoke(sr);
				expected -= firstPayment;
				Assert.assertEquals("getBalance return value is incorrect after partial payment", expected,
						(Double) result);

				m2.invoke(sr, expected);
				result = m.invoke(sr);
				expected = 0.0;
				Assert.assertEquals("getBalance return value is incorrect after full payment", expected,
						(Double) result);
			} catch (NoSuchMethodException ex) {
				Assert.fail("Unable to test getBalance() fully -- makePayment method not found");
			}
		} catch (NoSuchMethodException ex) {
			Assert.fail("getBalance method not found");
		}
	}

	@Test
	public void testToString() {
		StudentRecord sr = makeDefaultRecord();
		String output = sr.toString();
		java.text.NumberFormat cf = java.text.NumberFormat.getCurrencyInstance(
		        java.util.Locale.getDefault());
		String tuitionString = cf.format(TUITION_AMOUNT);
		String paymentString = cf.format(0);

		// Look for all the things we would expect to find in the output
		if (output.indexOf(STUDENT_NAME) == -1) {
			Assert.fail("Student name not found in toString() output");
		} else if (output.indexOf(PROGRAMME) == -1) {
			Assert.fail("Degree programme not found in toString() output");
		} else if (output.indexOf(tuitionString) == -1) {
			Assert.fail("Properly formatted tuition amount not found in toString() output");
		} else if (output.indexOf(paymentString) == -1) {
			Assert.fail("Properly formatted payment amount not found in toString() output");
		}
	}

}
