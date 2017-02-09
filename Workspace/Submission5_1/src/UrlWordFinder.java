
public class UrlWordFinder extends WordFinder {				//2204494l marcello laksono
	public UrlWordFinder(String url)		
	throws java.io.IOException{
	super(url);

	java.util.Scanner s = new java.util.Scanner (new java.net.URL(source).openStream());		
	while(s.hasNextLine()){			//adding the to content while the source has a next line
		contents.add(s.nextLine());}
	s.close();	
	

	}
	
}

