package bookView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

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
		}
    	parsedCordance = new ParsedCordance(books);	    
	}
	public ParsedCordance getParsedCordance(){
		return parsedCordance;
	}
}
