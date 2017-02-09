import java.util.ArrayList;
import java.util.Scanner;

public class WordFinderMain {			//2204494l Marcello Laksono
	
	public static void main(String[] args){	
	ArrayList<WordFinder> WordFinderl = new ArrayList<>();//making arraylist of WordFinder
	for (int i=0;i<args.length;i++){						
		try {WordFinderl.add(WordFinder.createWordFinder(args[i]));			//Try and catch to catch IO Exception
		} catch(java.io.IOException x){
			System.out.println("error: IOException found in " + args[i]);}

	
	}
	System.out.println("please input word to search for:");		//Asking for input
    Scanner sc = new Scanner(System.in);			//Input Variable
    String Word =sc.nextLine();			//Initialinzing which string would be checked
    sc.close();
    for(WordFinder i:WordFinderl){
    	System.out.println("The Count is:" +  i.count(Word));			//output of the count of the word
    }
    
	
	
	
	
}
}

	
