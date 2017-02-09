import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
								//Marcello Laksono 2204494
public class IbanChecker {

	public static void main(String[] args) {
		// Read the IBAN, convert it to upper case, and remove all whitespace
		Scanner stdin = new Scanner(System.in);
		System.out.println("Enter the IBAN: ");
		String iban = stdin.nextLine().toUpperCase().replaceAll("\\s", "");
		stdin.close();
		String CC = iban.substring(0, 2); // variable CC is for Country Code
		String ibanref = "GRGBSACHTR";	//String used to check whether it is a part of country code
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //later used to iterate and add the corresponding index number+10
		String check = "";		
		String newban="";		//empty string used when changing alphabets to digits
		for (int i = 0; i < 10; i = i + 2) {
			check = ibanref.substring(i, i + 2);
			if (CC.equals(check)) {
				ibanref = "2722242126";
				check = ibanref.substring(i, i + 2);
				break;
			}
			else if(i==8){
				System.out.println("Unknown country code: " + CC);
				return;
			}
			
		}
		if (iban.length() != Integer.valueOf(check)) {
			System.out.println("Invalid IBAN length: "+iban.length());
			return;
		}

		for(int i=0; i<iban.length();i++){
			if (iban.substring(i,i+1).matches("[^A-Za-z0-9 ]")){
				System.out.println("Invalid character in IBAN: "+ iban.substring(i,i+1));	//iterating through input then catching the invalid characters
				return;
			}
		}
		iban=iban.substring(4)+iban.substring(0,4);		//changing positions of the characters as per the requirements
		for(int i=0;i<iban.length();i++){
			if(iban.substring(i,i+1).matches(".*[a-zA-Z].*")){		//iterating throguh each character and checking whether it is alpha and then adding a the suitable number associated
				for (int o=0;o<alphabet.length();o++){
					if(iban.substring(i,i+1).equals(alphabet.substring(o, o+1))){
						newban=newban+(o+10);
						
					}
				}
				}
			else{
				newban=newban+iban.substring(i,i+1);			//if it is not an alpha then it simply adds the number to the new string(newban)
			}
			}
				
		String digits=newban;
		int mod=mod97(digits);		//running mod97 with the string found
		if(mod==1){
			System.out.println("IBAN is valid");		//checking whether iban is valid if only mod == 1
		}
		else{
			System.out.println("IBAN is invalid");
		}
		
				
	}
		
		
		
	

	// TODO: Implement your code here

	/**
	 * Computes the remainder when dividing the number represented by the input
	 * parameter by 97. Implements the procedure described at
	 * https://en.wikipedia.org/wiki/International_Bank_Account_Number#Modulo_operation_on_IBAN
	 * to process very large numbers.
	 * 
	 * @param digits
	 *            The number to process, represented as a string
	 * @return The result of digits mod 97
	 */
	private static int mod97(String digits) {
		int n = Integer.valueOf(digits.substring(0, 9));
		int d = n % 97;
		digits = digits.substring(9);

		// Process the rest of the number
		while (digits.length() > 1) {
			int end = Math.min(7, digits.length());
			n = Integer.valueOf(d + digits.substring(0, end));
			d = n % 97;
			digits = digits.substring(end);
		}
		return d;
	}

}
