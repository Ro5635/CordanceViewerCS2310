package CordanceParser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;


/**
 * Created by robert on 01/12/2016.
 */
public class ParsedCordanceTest {

    ParsedCordance pc;

    /**
     * This runs before each test and sets up the parsedCordance object ready for tessting
     */
    @Before
    public void setUp() {

        //Include the test data file, it is included in the source
        String[] fileLocations = {"CordanceParser/testBookSourceEmma.txt" ,"CordanceParser/testBookExpressMansfeild.txt"};

        pc = new ParsedCordance(fileLocations);

    }


    /**
     * Deconstructs and cleans up ready for the next test
     */
    @After
    public void tearDown(){

        //Overwrite reference to object, let garbage collector do its stuff.
        pc = null;

    }

    /**
     * Test that the indexing and creation of the words is working correctly
     */
    @Test
    public void testWordCreationAndRetrival(){

        ArrayList<Integer> wordID = pc.getIDsForWord("baby-linen");

        //check there is only one wordID returned and it is 161480
        //this will be the case with the included test files.

        //Explicitly defining as a integer to get around ambiguity with long.
        assertEquals("Testing that wordID is allocating and retrieving Correctly" , Integer.valueOf(161480) , wordID.get(0) );


    }

    /**
     * Test that the acquisition of the position data
     */
    @Test
    public void testGetPositionInfoByWordID() {

        ArrayList<Integer> wordID = pc.getIDsForWord("baby-linen");

        Map<String, String> positionInfo =  pc.getPositionInfoByWordID(wordID.get(0));

        assertEquals("Test the recall of the position information","2", positionInfo.get("Paragraph") );


    }

}



