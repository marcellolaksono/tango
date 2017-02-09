/**
 * Demo code implementing a number guessing game.
 * 
 * Version 1: using while (guess != value)
 * 
 * @author mefoster
 *
 */
public class Lecture4v1 {
	public static void main(String[] args) {
		// Choose the value
		int value = (int) (1 + Math.random() * 20);

		java.util.Scanner s = new java.util.Scanner(System.in);

		// Initialise it to a value that will definitely be wrong so the while loop runs
		int guess = -1;

		// Process the guesses
		while (guess != value) {
			// Get the next guess
			System.out.println("Enter your guess: ");
			guess = s.nextInt();

			// Check if it's correct
			if (guess < value) {
				System.out.println("HIGHER");
			} else if (guess > value) {
				System.out.println("LOWER");
			} else {
				// They got it!
				System.out.println("CORRECT");
			}
		}

		s.close();
	}
}
