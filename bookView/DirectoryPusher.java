package bookView;

import java.io.File;
import java.io.FileNotFoundException;

import CordanceParser.ParsedCordance;

public class DirectoryPusher {
	
	private ParsedCordance parsedCordance;
	
	public DirectoryPusher(String FolderLocation) throws FileNotFoundException {
		File targetDirectory = new File(FolderLocation);
		File[] files = targetDirectory.listFiles();
		String[] books = new String[files.length];
		int i = 0;
		for (File f : files){
		    books[i] = f.getAbsolutePath();
		    i++;
		}
    	parsedCordance = new ParsedCordance(books);	    
	}
	public ParsedCordance getParsedCordance(){
		return parsedCordance;
	}
}
