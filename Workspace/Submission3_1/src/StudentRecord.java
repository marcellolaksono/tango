public class StudentRecord {

	// Write your code here.								//Marcello Laksono 2204494l
	private int studentNumber;
	private String studentName;							//declaration of variables
	private String degreeProgramme;
	private int yearOfStudy;
	private double tuitionAmount;
	private double totalPayments;
	private static int next_studentnumber=0;
	/**
	 * @param studentNumber
	 * @param studentName
	 * @param degreeProgramme
	 * @param yearOfStudy
	 * @param tuitonPayments
	 * @param totalPayments
	 */
	public StudentRecord(String studentName, String degreeProgramme, int yearOfStudy,double tuitonAmount) {
		this.studentName = studentName;
		this.degreeProgramme = degreeProgramme;
		this.yearOfStudy = yearOfStudy;																				//making the sutdentrecord class
		this.tuitionAmount = tuitonAmount;
		this.studentNumber=next_studentnumber++;
		this.totalPayments=0.00;
	}
	


	public double getBalance(){
		return tuitionAmount-totalPayments;
	}
	
	public boolean makePayment(double amount){
		if(amount>0 && amount<=getBalance()){
			totalPayments+=amount;
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * @return the degreeProgramme
	 */
	public String getDegreeProgramme() {
		return degreeProgramme;
	}

	/**
	 * @param degreeProgramme the degreeProgramme to set
	 */
	public void setDegreeProgramme(String degreeProgramme) {
		this.degreeProgramme = degreeProgramme;
	}

	/**
	 * @return the yearOfStudy
	 */
	public int getYearOfStudy() {
		return yearOfStudy;
	}

	/**
	 * @param yearOfStudy the yearOfStudy to set
	 */
	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	/**
	 * @return the studentNumber
	 */
	public int getStudentNumber() {
		return studentNumber;
	}

	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * @return the tuitionAmount
	 */
	public double getTuitionAmount() {
		return tuitionAmount;
	}

	/**
	 * @return the totalPayments
	 */
	public double getTotalPayments() {
		return totalPayments;
	}
	
	java.text.NumberFormat cf = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.getDefault());
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudentRecord [studentNumber=" + studentNumber + ", studentName=" + studentName + ", degreeProgramme="
				+ degreeProgramme + ", yearOfStudy=" + yearOfStudy + ", tuitionAmount=" +  cf.format(tuitionAmount)
				+ ", totalPayments=" +  cf.format(totalPayments) + "]";
	}

	
}
