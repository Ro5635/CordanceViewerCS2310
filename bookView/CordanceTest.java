package bookView;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CordanceParser.ParsedCordance;

public class CordanceTest {
	
	
	private Cordance tester;
	private ParsedCordance pc;
	private String searchWord ;
	
	@Before
	public void SetUp(){
		String[] fileLocations = {"/cordance/emmaEd11Test.txt","/cordance/mansfieldParkEd10Test.txt"};
		pc = new ParsedCordance(fileLocations);
		tester = new Cordance(pc);
		searchWord = "vex";
		
	}
	@Test
	public void CheckForInvalidWord(){
		tester.getKWIC("aaaaaaa");
		tester.getKWIC(" vex");
		tester.getKWIC(" vex ");
		//if the program doesnt crash then this is passed
		//should print need a valid word
	}
	@Test
	public void CheckWiderContext(){
		tester.getKWIC("vex");//start the cordance with 1 output
		System.out.println(tester.getWiderContext("0"));
		//////////////////////////////////////check what it is
		tester.getKWIC("or");// start with more than 1 id option
		System.out.println(tester.getWiderContext("1"));
		
		System.out.println(tester.getWiderContext("4"));//check eronious 
		
	}
	
	@Test
	public void GetKWICSizeTest() {
		//check it returns the correct cordance
		assertEquals("hello", tester.getKWIC(searchWord, 0), '\n'+"0: vex ");
		//check it returns the correct cordance with different size
		assertEquals("hello", tester.getKWIC(searchWord, 1), '\n'+"0: or vex her. ");		
		//check it returns the correct cordance with bigger size
		assertEquals("hello", tester.getKWIC(searchWord, 100), '\n'+"0: Emma Woodhouse, handsome, clever, and rich, with a comfortable home and happy disposition, seemed to unite some of the best blessings of existence; and had lived nearly twenty-one years in the world with very little to distress or vex her. ");
	}
	@Test
	public void GetKWICMultiWord() {
		searchWord = "or";
		System.out.println(tester.getKWIC(searchWord, 0));
		//check it gets multiple words from the books
		assertEquals("hello", tester.getKWIC(searchWord, 0), '\n'+"0: or " + '\n'+"1: or " + '\n'+"2: or ");
		System.out.println(tester.getKWIC(searchWord, 1));
		//check it gets multiple words from the books and displays a cordance
		assertEquals("hello", tester.getKWIC(searchWord, 1), '\n'+"0: distress or vex " + '\n'+"1: two or three " + '\n'+"2: fortune, or connexions, ");
		System.out.println(tester.getKWIC(searchWord, 1000));
		//check it gets multiple words from the books and displays a cordance
		assertEquals("hello", tester.getKWIC(searchWord, 1000), '\n'+"0: Emma Woodhouse, handsome, clever, and rich, with a comfortable home and happy disposition, seemed to unite some of the best blessings of existence; and had lived nearly twenty-one years in the world with very little to distress or vex her. " + '\n'+"1: Mr. Weston was a native of Highbury, and born of a respectable family, which for the last two or three generations had been rising into gentility and property.  He had received a good education, but, on succeeding early in life to a small independence, had become indisposed for any of the more homely pursuits in which his brothers were engaged, and had satisfied an active, cheerful mind and social temper by entering into the militia of his county, then embodied. " + '\n'+"2: About thirty years ago Miss Maria Ward, of Huntingdon, with only seven thousand pounds, had the good luck to captivate Sir Thomas Bertram, of Mansfield Park, in the county of Northampton, and to be thereby raised to the rank of a baronet's lady, with all the comforts and consequences of an handsome house and large income. All Huntingdon exclaimed on the greatness of the match, and her uncle, the lawyer, himself, allowed her to be at least three thousand pounds short of any equitable claim to it. She had two sisters to be benefited by her elevation; and such of their acquaintance as thought Miss Ward and Miss Frances quite as handsome as Miss Maria, did not scruple to predict their marrying with almost equal advantage. But there certainly are not so many men of large fortune in the world as there are pretty women to deserve them. Miss Ward, at the end of half a dozen years, found herself obliged to be attached to the Rev. Mr. Norris, a friend of her brother-in-law, with scarcely any private fortune, and Miss Frances fared yet worse. Miss Ward's match, indeed, when it came to the point, was not contemptible:  Sir Thomas being happily able to give his friend an income in the living of Mansfield; and Mr. and Mrs. Norris began their career of conjugal felicity with very little less than a thousand a year. But Miss Frances married, in the common phrase, to disoblige her family, and by fixing on a lieutenant of marines, without education, fortune, or connexions, did it very thoroughly.  She could hardly have made a more untoward choice.  Sir Thomas Bertram had interest, which, from principle as well as pride--from a general wish of doing right, and a desire of seeing all that were connected with him in situations of respectability, he would have been glad to exert for the advantage of Lady Bertram's sister; but her husband's profession was such as no interest could reach; and before he had time to devise any other method of assisting them, an absolute breach between the sisters had taken place. It was the natural result of the conduct of each party, and such as a very imprudent marriage almost always produces. To save herself from useless remonstrance, Mrs. Price never wrote to her family on the subject till actually married. Lady Bertram, who was a woman of very tranquil feelings, and a temper remarkably easy and indolent, would have contented herself with merely giving up her sister, and thinking no more of the matter; but Mrs. Norris had a spirit of activity, which could not be satisfied till she had written a long and angry letter to Fanny, to point out the folly of her conduct, and threaten her with all its possible ill consequences.  Mrs. Price, in her turn, was injured and angry; and an answer, which comprehended each sister in its bitterness, and bestowed such very disrespectful reflections on the pride of Sir Thomas as Mrs. Norris could not possibly keep to herself, put an end to all intercourse between them for a considerable period. ");
		
	}
	@Test
	public void CheckDefaultSize() {
		//check it returns the correct words with defauls size of 10
		assertEquals("hello", tester.getKWIC(searchWord), '\n'+"0: years in the world with very little to distress or vex her. ");			
	}
}
