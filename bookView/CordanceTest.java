package bookView;

import static org.junit.Assert.*;

import org.junit.Test;

public class CordanceTest {
	
	
	private Cordance tester;
	@Test
	public void test() {
		
		
		tester = new Cordance();
		assertEquals(tester.getCurrentSize(), 10);
		assertEquals(tester.getCurrentWord(), "");
		assertEquals(tester.getKWIC("he"), "file not valid please fix.");
		
		
		
	}

}
