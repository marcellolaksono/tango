import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExamMarker2Test {
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

    private String runTest(int numValues, double... values) {
        String testInput = numValues + " ";
        for (double value : values) {
            testInput += value + System.lineSeparator();
        }
        testInput = testInput.trim();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        System.setOut(ps);
        ExamMarker2.main(null);
        System.out.flush();
        return baos.toString();
    }

	private void checkStdOut(String testOutput, int best, int worst, double avg) {
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
        String testOut = runTest(5, 70.5, 55, -45, 45, 65.3, 800, 80);
        checkStdOut(testOut, 5, 3, 63.160000000000004);
    }
    
    @Test
    public void testOneMark() {
    	String testOut = runTest(1, 50.0);
    	checkStdOut(testOut, 1, 1, 50.0);
    }

}
