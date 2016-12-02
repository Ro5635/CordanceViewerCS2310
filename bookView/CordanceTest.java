package bookView;

import static org.junit.Assert.*;

import org.junit.Test;

public class CordanceTest {
	
	
	private Cordance tester;
	@Test
	public void test() {
		
		
		tester = new Cordance("C:/Users/ryan/Documents/university/year 2/cordance veiwer/CordanceViewerCS2310/data/pandpEd12.txt");
		assertEquals(tester.getCurrentSize(), 10);
		assertEquals(tester.getCurrentWord(), "");
		
		assertEquals(tester.get);
		
		
	}

}
