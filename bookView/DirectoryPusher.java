package bookView;

import java.io.File;
import java.io.FileNotFoundException;

import CordanceParser.ParsedCordance;

public class DirectoryPusher {
	
	//the parsed cordance that it will ask to read all the files
	private ParsedCordance parsedCordance;
	
	/**
	 * takes a folder loction and then loops through all the different files in it and
	 * gets parsed cordance to read them
	 * @param FolderLocation
	 * @throws FileNotFoundException
	 */
	public DirectoryPusher(String FolderLocation) throws FileNotFoundException {
		File targetDirectory = new File(FolderLocation);
		File[] files = targetDirectory.listFiles();
		String[] books = new String[files.length];
		//loops through files and makes an array then passes to parsed cordance
		int i = 0;
		for (File f : files){
		    books[i] = f.getAbsolutePath();
		    i++;
		}
    	parsedCordance = new ParsedCordance(books);//sets up a parsedcordance with the files it finds
	}
	public ParsedCordance getParsedCordance(){
		return parsedCordance;
	}
}
