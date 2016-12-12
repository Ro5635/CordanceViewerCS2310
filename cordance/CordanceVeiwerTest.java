package cordance;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import CordanceParser.ParsedCordance;

public class CordanceVeiwerTest {

	private ArrayList<String> wordIndex;
	private HashMap<String, ArrayList<Integer>> wordCataloge;

	private String searchWord = "hello";
	private CordanceVeiwer tester;
	private ParsedCordance pcTester;

	@Test
	public void test() {


		String[] fileLocations = {"C:/Users/ryan/Documents/university/year 2/cordance veiwer/CordanceViewerCS2310/CordanceParser/testBookExpressEmma.txt", "C:/Users/ryan/Documents/university/year 2/cordance veiwer/CordanceViewerCS2310/CordanceParser/testBookExpressMansfeild.txt"};
		pcTester = new ParsedCordance(fileLocations);
		tester = new CordanceVeiwer(pcTester);
		searchWord = "vex";

		
		System.out.println(tester.getCordance(searchWord, 10));

		//assertEquals("Test for x", tester.getCordance(searchWord, 10), "by maticulously adding words to an arraylist so that i can test a cordance program i'm making. ");				
	}

}
