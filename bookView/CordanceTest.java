package bookView;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CordanceParser.ParsedCordance;
import cordance.CordanceVeiwer;

public class CordanceTest {
	
	
	private Cordance tester;
	private ParsedCordance pc;
	private String searchWord ;
	
	@Before
	public void SetUp(){
		String[] fileLocations = {"C:/Users/ryan/Documents/university/year 2/cordance veiwer/CordanceViewerCS2310/cordance/emmaEd11Test.txt","C:/Users/ryan/Documents/university/year 2/cordance veiwer/CordanceViewerCS2310/cordance/mansfieldParkEd10Test.txt"};
		pc = new ParsedCordance(fileLocations);
		tester = new Cordance(pc);
		searchWord = "vex";
	}
	
	
	@Test
	public void GetKWICOneWordTest() {
		

		
	}

}
