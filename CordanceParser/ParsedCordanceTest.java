package CordanceParser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by robert on 01/12/2016.
 */
public class ParsedCordanceTest {

    /**
     * Test that the indexing and creation of the words is working correctly
     */
    @Test
    public void testWordCreation() {

        String[] fileLocations = {"/Users/robert/Desktop/desktop/abook.txt",""};

        ParsedCordance pc = new ParsedCordance(fileLocations);

        System.out.println(pc.getPositionInfoByWordID(4));


    }



}
