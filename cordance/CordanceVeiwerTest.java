package cordance;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import CordanceParser.ParsedCordance;

public class CordanceVeiwerTest {

	private String searchWord = "hello";
	private CordanceVeiwer tester;
	private ParsedCordance pcTester;
	@Before
	public void SetUp(){
		String[] fileLocations = {"/cordance/emmaEd11Test.txt","/cordance/mansfieldParkEd10Test.txt"};
		pcTester = new ParsedCordance(fileLocations);
		tester = new CordanceVeiwer(pcTester);
		searchWord = "vex";
	}
	@Test
	public void TestCordance() {
		//check it returns the correct word
		assertEquals("hello", tester.getCordance(searchWord, 0), '\n'+"0: vex ");	

		//check it returns the words before and after the chosen word, in this case its just 1 word
		assertEquals(tester.getCordance(searchWord, 1), '\n'+"0: or vex her. ");	
		
		//check that the spaces at the end are removed
		assertEquals(tester.getCordance(searchWord, 2), '\n'+"0: distress or vex her. ");
		
	}
	public void CheckInvalidDate(){
		tester.getCordance("1", 2);
		//if it crashes it has failed
	}
}
