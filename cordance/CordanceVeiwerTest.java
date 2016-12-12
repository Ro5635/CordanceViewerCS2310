package cordance;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import CordanceParser.ParsedCordance;

public class CordanceVeiwerTest {

	private ArrayList<String> wordIndex;
	private HashMap<String, ArrayList<Integer>> wordCataloge;

	private String searchWord = "hello";
	private CordanceVeiwer tester;
	private ParsedCordance pcTester;
	@Before
	public void SetUp(){
		String[] fileLocations = {"C:/Users/ryan/Documents/university/year 2/cordance veiwer/CordanceViewerCS2310/cordance/emmaEd11Test.txt","C:/Users/ryan/Documents/university/year 2/cordance veiwer/CordanceViewerCS2310/cordance/mansfieldParkEd10Test.txt"};
		pcTester = new ParsedCordance(fileLocations);
		tester = new CordanceVeiwer(pcTester);
		searchWord = "vex";
	}
	@Test
	public void TestCordance() {
		System.out.println(tester.getCordance(searchWord, 2));

		//check it returns the correct word
		assertEquals("hello", tester.getCordance(searchWord, 0), '\n'+"0: vex ");	

		//check it returns the words before and after the chosen word, in this case its just 1 word
		assertEquals(tester.getCordance(searchWord, 1), '\n'+"0: or vex her. ");	
		
		//check that the spaces at the end are removed
		assertEquals(tester.getCordance(searchWord, 2), '\n'+"0: distress or vex her. ");
		
	}
}
