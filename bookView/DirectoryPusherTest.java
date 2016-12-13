package bookView;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import CordanceParser.ParsedCordance;

public class DirectoryPusherTest {


	DirectoryPusher tester;
	ParsedCordance pc;
	@Before
	public void SetUp(){
		try {
			tester = new DirectoryPusher("/bookView/testtexts");
		} catch (FileNotFoundException e) {
			System.exit(000);
			System.out.println("you need to put in a corect test directory");
		}
	}
	
	@Test
	public void CheckFilesJoined() {//check if the files are concatinated
		pc = tester.getParsedCordance();
		String concat = "";
		for(int i = 0; i < pc.getWordListSize(); i++){
			concat += pc.getWordByID(i) +" ";
		}
		assertEquals("the files should be the same as the string","CHAPTER I null hello Title: Mansfield Park null Author: Jane Austen null CHAPTER I null hello again ",concat);//check the concatanation is equal to the files
	}

}
