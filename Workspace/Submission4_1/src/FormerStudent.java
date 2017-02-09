	public class FormerStudent extends StudentRecord{
		//fields								//Marcello Laksono 2204494l
		private int graduationYear;
		private String degreeClass;
		//class
		public FormerStudent(String studentName, String degreeProgramme, int graduationYear, String degreeClass){
			super(studentName, degreeProgramme);
			this.graduationYear=graduationYear;
			this.degreeClass=degreeClass;
					
		}
		
		//GetDetails()
		protected String getDetails() {
			// TODO Auto-generated method stub
			return ", Graduation Year:"+graduationYear+", degree Class:"+degreeClass;}
		//Getters 
		public int getGraduationYear() {
			return graduationYear;
		}

		public String getDegreeClass() {
			return degreeClass;
		}
	}
	