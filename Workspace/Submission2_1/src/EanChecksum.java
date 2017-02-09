public class EanChecksum {			//Marcello Laksono 2204494
	public static void main(String[] args) {
		// Read the barcode from the user
		java.util.Scanner stdin = new java.util.Scanner(System.in);
		System.out.println("Enter the barcode number: ");
		String barcode = stdin.next();
		stdin.close();
		int mult=3;
		int psum=0;
		// TODO: Implement your code here
			if(barcode.length()!=7 && barcode.length()!=12){	//checking that barcode length is correct
				System.out.println("Invalid barcode length");	
				return;
				}
			if (barcode.matches(".*[a-zA-Z].*")){			//checking whether barcode has any letters
				System.out.println("Invalid barcode format");
				return;
				}
			String reverse = new StringBuffer(barcode).
			reverse().toString();
				for(int i=0; i<barcode.length(); i++){
					psum+=Character.getNumericValue(reverse.charAt(i))*mult;	
					if (mult==3){
						mult=1;
					}
					else{
						mult=3;
					}}
			int minus=10;
			if(psum==0){
				minus=0;
			}
			int mod1 =psum%10;
			int checkdigit=minus-mod1;		
			System.out.println("Checksum digit: "+checkdigit);

		}
		
		

	}



