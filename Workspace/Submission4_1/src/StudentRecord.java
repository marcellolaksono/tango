/**
 * A class to represent a simplified student record.
 * 
 * @author Mary Ellen Foster <MaryEllen.Foster@glasgow.ac.uk>
 */
public abstract class StudentRecord {					//Marcello Laksono 2204494l

	// Fields
	private int studentNumber;
	private String studentName;
	private String degreeProgramme;

	// Static field to ensure unique IDs
	private static int NEXT_NUMBER = 1000;

	/**
	 * Creates a new StudentRecord with the given parameters.
	 */
	public StudentRecord(String studentName, String degreeProgramme) {
		this.studentName = studentName;
		this.degreeProgramme = degreeProgramme;
		this.studentNumber = NEXT_NUMBER++;
	}


	/**
	 * Returns the current student payment balance.
	 */


	/**
	 * If the payment amount is positive and less than the outstanding balance,
	 * it applies the payment and returns true. If the amount is invalid, it
	 * returns false.
	 * 
	 * @param amount The amount to pay
	 */


	// Getters
	public int getStudentNumber() {
		return studentNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getDegreeProgramme() {
		return degreeProgramme;
	}





	// Setters
	public void setDegreeProgramme(String degreeProgramme) {
		this.degreeProgramme = degreeProgramme;
	}



	/**
	 * Returns a nice string representation of this student record
	 */
	public String toString() {
		java.text.NumberFormat cf = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.getDefault());
		return studentNumber + ": name=" + studentName +", Degree Programme:"+ degreeProgramme+ getDetails();
	}
	// making protected abstract
	protected abstract String getDetails();
	
}
