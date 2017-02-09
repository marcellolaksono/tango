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

public class EanChecksumTest {

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
		EanChecksum.main(null);
		System.out.flush();
		return baos.toString();
	}

	private final Pattern CHECKSUM_PATTERN = Pattern.compile("Checksum digit: (\\d)");
	private final Pattern INVALID_LENGTH_PATTERN = Pattern.compile("Invalid barcode length");
	private final Pattern INVALID_FORMAT_PATTERN = Pattern.compile("Invalid barcode format");

	private void checkChecksum(String input, int expected) {
		String output = runTest(input);
		Matcher m = CHECKSUM_PATTERN.matcher(output);
		if (m.find()) {
			Assert.assertEquals("Checksum digit does not match", expected, (int) Integer.valueOf(m.group(1)));
			// Check the other patterns do not also match
			if (INVALID_LENGTH_PATTERN.matcher(output).find() || INVALID_FORMAT_PATTERN.matcher(output).find()) {
				Assert.fail("Multiple output messages");
			}
		} else {
			Assert.fail("No checksum digit found in output");
		}
	}

	private void checkInvalidLength(String input) {
		String output = runTest(input);
		Matcher m = INVALID_LENGTH_PATTERN.matcher(output);
		if (!m.find()) {
			Assert.fail("No invalid length message found in output");
		} else {
			// Check the other patterns do not also match
			if (CHECKSUM_PATTERN.matcher(output).find() || INVALID_FORMAT_PATTERN.matcher(output).find()) {
				Assert.fail("Multiple output messages");
			}
		}
	}

	private void checkInvalidFormat(String input) {
		String output = runTest(input);
		Matcher m = INVALID_FORMAT_PATTERN.matcher(output);
		if (!m.find()) {
			Assert.fail("No invalid format message found in output");
		} else {
			// Check the other patterns do not also match
			if (CHECKSUM_PATTERN.matcher(output).find() || INVALID_LENGTH_PATTERN.matcher(input).find()) {
				Assert.fail("Multiple output messages");
			}
		}
	}

	@Test
	public void testValidEan13() {
		checkChecksum("400638133393", 1);
	}

	@Test
	public void testValidEan8() {
		checkChecksum("7351353", 7);
	}

	@Test
	public void testZeros() {
		checkChecksum("0000000", 0);
	}

	@Test
	public void testWrongLength() {
		checkInvalidLength("11111");
	}

	@Test
	public void testInvalidFormat() {
		checkInvalidFormat("12abcde");
	}

}
