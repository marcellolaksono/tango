import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PrimesTest {
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

	private String runTest(Object val) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		System.setIn(new ByteArrayInputStream(String.valueOf(val).getBytes()));
		System.setOut(ps);
		Primes.main(null);
		System.out.flush();
		return baos.toString();
	}

	private static final Pattern p = Pattern.compile("(\\d+) is prime");

	private void checkStdOut(String testOutput, Integer[] values) {
		ArrayList<Integer> primes = new ArrayList<>();
		for (String line : testOutput.toLowerCase().split(System.lineSeparator())) {
			Matcher m = p.matcher(line.trim());
			if (m.find()) {
				primes.add(Integer.valueOf(m.group(1)));
			}
		}
		Assert.assertArrayEquals(
				"Output value " + primes + " does not match expected values " + Arrays.toString(values), values,
				primes.toArray());
	}

	private void doTest(Object inputVal, Integer[] outputArray) throws Exception {
		String testOutput = runTest(inputVal);
		checkStdOut(testOutput, outputArray);
	}

	@Test
	public void testNegativeNumber() throws Exception {
		doTest(-1, new Integer[0]);
	}

	@Test
	public void testTen() throws Exception {
		doTest(10, new Integer[] { 2, 3, 5, 7 });
	}

	@Test
	public void testOne() throws Exception {
		doTest(1, new Integer[0]);
	}
}
