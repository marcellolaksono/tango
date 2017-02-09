import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExamMarkerTest {
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

	private String runTest(int val1, int val2, int val3) throws IOException {
		String testInput = val1 + " " + val2 + " " + val3;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		System.setOut(ps);
		ExamMarker.main(null);
		System.out.flush();
		return baos.toString();
	}

	private void checkStdOut(String testOutput, int best, int worst, int avg) {
		boolean hasBest = false, hasWorst = false, hasAvg = false;
		for (String line : testOutput.toLowerCase().split(System.lineSeparator())) {
			line = line.trim();
			if (line.contains("best")) {
				Assert.assertTrue("Best value wrong", line.endsWith(String.valueOf(best)));
				hasBest = true;
			} else if (line.contains("worst")) {
				Assert.assertTrue("Worst value wrong", line.endsWith(String.valueOf(worst)));
				hasWorst = true;
			} else if (line.contains("average")) {
				Assert.assertTrue("Average value wrong", line.endsWith(String.valueOf(avg)));
				hasAvg = true;
			}
		}
		Assert.assertTrue("Best value missing", hasBest);
		Assert.assertTrue("Worst value missing", hasWorst);
		Assert.assertTrue("Average value missing", hasAvg);
	}

	@Test
	public void testExample() throws Exception {
		String testOutput = runTest(70, 55, 65);
		checkStdOut(testOutput, 1, 2, 63);
	}
}
