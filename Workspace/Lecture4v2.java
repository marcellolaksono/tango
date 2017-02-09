/**
 * Demo code implementing a number guessing game.
 * 
 * Version 2: using while (true)
 * 
 * @author mefoster
 *
 */
public class Lecture4v2 {
	public static void main(String[] args) {
		// Choose the value
		int value = (int) (1 + Math.random() * 20);

		java.util.Scanner s = new java.util.Scanner(System.in);

		// Process the guesses
		while (true) {
			// Get the next guess
			System.out.println("Enter your guess: ");
			int guess = s.nextInt();

			// Check if it's correct
			if (guess < value) {
				System.out.println("HIGHER");
			} else if (guess > value) {
				System.out.println("LOWER");
			} else {
				// They got it!
				System.out.println("CORRECT");
				break;
			}
		}

		s.close();
	}
}
