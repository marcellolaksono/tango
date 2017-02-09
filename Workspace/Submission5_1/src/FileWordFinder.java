
public class FileWordFinder extends WordFinder{
														//2204494l Marcello Laksono
	
	
	public FileWordFinder(String Filename)
		throws java.io.IOException{
		super(Filename);
		
	
	java.util.Scanner s = new java.util.Scanner (new java.io.File(source));
	
	while(s.hasNextLine()){		//adding the to content while the source has a next line
		contents.add(s.nextLine());
	}
	s.close();
	}
}
