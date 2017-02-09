import java.util.ArrayList;
														//2204494l Marcello Laksono
public class WordFinder {
	
	
	protected String source;				//String of the source file or url
	protected ArrayList<String> contents = new ArrayList<String>();			//Content of the source as array list of strings

	protected WordFinder(String source){		
		this.source=source;
		contents=new ArrayList<String>();			
		
	}


	public String toString() {
		return source ;					//returning the source to the computer
	}
	public int count (String word){
		int count=0;
		for (String line : contents){					
		for (String string: line.split("[\\s\\p{Punct}]+")){			//splitting when it encounters white space
			if(string.equalsIgnoreCase(word)){
				count++;
			}
		}
		}
		return count;
		
	}
	
	public static WordFinder createWordFinder(String source) throws java.io.IOException {		//Checking whether it is URL or File path 
																								//and running the appropriate WordFinder
			if (source.toLowerCase().startsWith("http"))
			{return new UrlWordFinder(source);}
			else{
			return new FileWordFinder(source);
			}
		}

}

