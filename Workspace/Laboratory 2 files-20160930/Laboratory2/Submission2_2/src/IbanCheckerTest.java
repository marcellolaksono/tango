import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IbanCheckerTest {

	InputStream stdin;
	PrintStream stdout;

	@Before
	public void setup() {
		stdin = System.in;
		stdout = System.out;
	}

	@After
	public void teardown() {
		System.setIn(stdin);
		System.setOut(stdout);
	}

	/**
	 * Run a test with the given standard input, reading the result from stdout.
	 * 
	 * @param val
	 * @return
	 */
	private String runTest(Object val) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		System.setIn(new ByteArrayInputStream(String.valueOf(val).getBytes()));
		System.setOut(ps);
		IbanChecker.main(null);
		System.out.flush();
		return baos.toString();
	}

	// Patterns
	private static final Pattern INVALID_COUNTRY_PATTERN = Pattern.compile("Unknown country code: (\\S+)");
	private static final Pattern INVALID_LENGTH_PATTERN = Pattern.compile("Invalid IBAN length: (\\d+)");
	private static final Pattern INVALID_CHAR_PATTERN = Pattern.compile("Invalid character in IBAN: (\\S)");
	private static final Pattern INVALID_IBAN_PATTERN = Pattern.compile("IBAN is invalid");
	private static final Pattern VALID_IBAN_PATTERN = Pattern.compile("IBAN is valid");

	// Method to check each pattern (and not the others)
	private void checkInvalidCountry(String input, String expected) {
		String output = runTest(input);
		Matcher m = INVALID_COUNTRY_PATTERN.matcher(output);
		if (m.find()) {
			Assert.assertEquals("Invalid country message incorrect", expected, m.group(1));
			// Check the others
			if (INVALID_LENGTH_PATTERN.matcher(output).find() || INVALID_CHAR_PATTERN.matcher(output).find()
					|| INVALID_IBAN_PATTERN.matcher(output).find() || VALID_IBAN_PATTERN.matcher(output).find()) {
				Assert.fail("Multiple output messages");
			}
		} else {
			Assert.fail("Invalid country message not found");
		}
	}

	private void checkInvalidCharacter(String input, String expected) {
		String output = runTest(input);
		Matcher m = INVALID_CHAR_PATTERN.matcher(output);
		if (m.find()) {
			Assert.assertEquals("Invalid character message incorrect", expected, m.group(1));
			// Check the others
			if (INVALID_LENGTH_PATTERN.matcher(output).find() || INVALID_COUNTRY_PATTERN.matcher(output).find()
					|| INVALID_IBAN_PATTERN.matcher(output).find() || VALID_IBAN_PATTERN.matcher(output).find()) {
				Assert.fail("Multiple output messages");
			}
		} else {
			Assert.fail("Invalid character message not found");
		}
	}

	private void checkInvalidLength(String input, int expected) {
		String output = runTest(input);
		Matcher m = INVALID_LENGTH_PATTERN.matcher(output);
		if (m.find()) {
			Assert.assertEquals("Invalid length message incorrect", expected, (int) Integer.valueOf(m.group(1)));
			// Check the others
			if (INVALID_COUNTRY_PATTERN.matcher(output).find() || INVALID_CHAR_PATTERN.matcher(output).find()
					|| INVALID_IBAN_PATTERN.matcher(output).find() || VALID_IBAN_PATTERN.matcher(output).find()) {
				Assert.fail("Multiple output messages");
			}
		} else {
			Assert.fail("Invalid length message not found");
		}
	}

	private void checkInvalidIban(String input) {
		String output = runTest(input);
		Matcher m = INVALID_IBAN_PATTERN.matcher(output);
		if (m.find()) {
			if (INVALID_COUNTRY_PATTERN.matcher(output).find() || INVALID_CHAR_PATTERN.matcher(output).find()
					|| INVALID_LENGTH_PATTERN.matcher(output).find() || VALID_IBAN_PATTERN.matcher(output).find()) {
				Assert.fail("Multiple output messages");
			}
		} else {
			Assert.fail("Invalid IBAN message not found");
		}
	}

	private void checkValidIban(String input) {
		String output = runTest(input);
		Matcher m = VALID_IBAN_PATTERN.matcher(output);
		if (m.find()) {
			if (INVALID_COUNTRY_PATTERN.matcher(output).find() || INVALID_CHAR_PATTERN.matcher(output).find()
					|| INVALID_LENGTH_PATTERN.matcher(output).find() || INVALID_IBAN_PATTERN.matcher(output).find()) {
				Assert.fail("Multiple output messages");
			}
		} else {
			Assert.fail("Valid IBAN message not found");
		}
	}

	private static final String[] IBANS = {
			"GR16 0110 1250 0000 0001 2300 695",
			"GB29 NWBK 6016 1331 9268 19",
			"SA03 8000 0000 6080 1016 7519",
			"CH93 0076 2011 6238 5295 7",
			"TR33 0006 1005 1978 6457 8413 26"
	};
	
	@Test
	public void testValidIbans() {
		for (String iban : IBANS) {
			checkValidIban(iban);
		}
	}
	
	@Test
	public void testInvalidCountry() {
		String badIban = IBANS[0].replaceAll("GR", "ZZ");
		checkInvalidCountry(badIban, "ZZ");
	}

	@Test
	public void testInvalidLength() {
		String badIban = IBANS[2].replaceAll("SA", "CH");
		checkInvalidLength(badIban, 24);
	}
	
	@Test
	public void testInvalidCharacter() {
		String badIban = IBANS[4].replaceAll("5", "!");
		checkInvalidCharacter(badIban, "!");
	}
	
	@Test
	public void testInvalidIban() {
		String badIban = IBANS[3].replaceAll("2", "7");
		checkInvalidIban(badIban);
	}

}
