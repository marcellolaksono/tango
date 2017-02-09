import java.util.Scanner;


/**
 * Starter code for JOOSE2 Laboratory 1, Submission 2.
 * 
 * @author Mary Ellen Foster <mefoster@gmail.com>
 *
 */
public class ExamMarker {

	public static void main(String[] args) {
		Scanner standardInput = new Scanner(System.in);
		// TODO enter your code here for Submission 1		
		System.out.print("Enter mark 1:");
		int FirstStudent= standardInput.nextInt();
		System.out.print("Enter mark 2:");
		int SecondStudent= standardInput.nextInt();
		System.out.print("Enter mark 3:");
		int ThirdStudent= standardInput.nextInt();
		int average1= ((FirstStudent+SecondStudent+ThirdStudent)/3);
		System.out.println("Average mark ="+ average1);
		int best=0;
		int worst=0;
		if(FirstStudent>SecondStudent&&FirstStudent>ThirdStudent){
			 best = 1;}
		
		if(SecondStudent>FirstStudent&&SecondStudent>ThirdStudent){
			 best = 2;}
		
		if(ThirdStudent>SecondStudent&&ThirdStudent>FirstStudent){
			 best = 3;}
		
		if(FirstStudent<SecondStudent&&FirstStudent<ThirdStudent){
			 worst = 1;}
		
		if(SecondStudent<FirstStudent&&SecondStudent<ThirdStudent){
			 worst = 2;}
		
		if(ThirdStudent<SecondStudent&&ThirdStudent<FirstStudent){
			 worst = 3;}
		
		System.out.println("Best Student:"+ best );
		System.out.println("Worst Student:"+ worst);
		
		standardInput.close();
	}

}
