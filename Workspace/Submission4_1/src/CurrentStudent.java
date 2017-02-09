public class CurrentStudent extends StudentRecord {	//Marcello Laksono 2204494l
	//fields
	private int yearOfStudy;
	private double tuitionAmount;
	private double totalPayments;
	
	//Class 
	public CurrentStudent(String studentName, String degreeProgramme, int yearOfStudy, double tuitionAmount){
		super(studentName, degreeProgramme);
		this.yearOfStudy=yearOfStudy;
		this.tuitionAmount=tuitionAmount;
		
		}
	
	protected String getDetails() {
		// TODO Auto-generated method stub
		java.text.NumberFormat cf = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.getDefault());
		return ", Year of Study:"+yearOfStudy+", Tuition Amount:"+cf.format(tuitionAmount)+",Total Payments:"+cf.format(totalPayments);}
	
	
	public double getTotalPayments(){
		return totalPayments;
	}
	
	
	public double getBalance() {
		return tuitionAmount - totalPayments;
	}
	
	
	public void makePayment(double amount) {
		if (amount > getBalance() || amount <= 0) {
			throw new IllegalArgumentException("payment amount invalid");		//exceptions
		}
		totalPayments += amount;
	}
	//getters
	public int getYearOfStudy() {
		return yearOfStudy;
	}

	//setters
	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}


	public double getTuitionAmount() {
		return tuitionAmount;
	}


	}
