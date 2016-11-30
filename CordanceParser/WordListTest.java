package CordanceParser;

import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;


/**
 * Created by robert on 30/11/2016.
 * This Junit test class tests the expected functionality of the WordList class.
 */
public class WordListTest {

    private WordList wl;

    //Run the following method before each test
    @Before
    public void setUp() {
        wl = new WordList(30);

    }

    /**
     * Test that the indexing and creation of the words is working correctly
     */
    @Test
    public void testWordCreation() {

        wl.addWord("Bob");
        wl.addWord("Loves");
        wl.addWord("Chocolate!");
        //Ensure that null can be added to the list
        wl.addWord(null);

        //Check the index has been incrementing correctly
        assertEquals("Check word the index has been incrementing correctly", 4 , (int) wl.addWord("test"));

        //Test acquisition of listed words
        assertEquals("Test acquisition of listed words","Chocolate!", wl.getWord(2));
        assertEquals("Test acquisition of listed words","Bob", wl.getWord(0));
        assertEquals("Tests acquisition of the created null listing form the list", null, wl.getWord(3));

    }

    /**
     *  Ensure that the correct exception is thrown when a invalid parameter is passed
     */
    @Test(expected=InvalidParameterException.class)
    public void testInvalidParameterException() {
        wl.getWord(5635);

    }

    /**
     * Test that the acquisition of the words is working correctly
     */
    @Test
    public void testWordAcquisition() {

        wl.addWord("Bob");
        wl.addWord("Loves");
        wl.addWord("Chocolate!");

        //Test acquisition of listed words
        assertEquals("Test acquisition of listed words","Chocolate!", wl.getWord(2));
        assertEquals("Test acquisition of listed words","Bob", wl.getWord(0));

    }

}
