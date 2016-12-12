package CordanceParser;

import org.junit.Test;




/**
 * Created by robert on 01/12/2016.
 */
public class ParsedCordanceTest {

    /**
     * Test that the indexing and creation of the words is working correctly
     */
    @Test
    public void testWordCreation(){

        //Include the test data file, it is included in the source
        String[] fileLocations = {"out/production/CordanceViewerCS2310/CordanceParser/testBookSourceEmma.txt", "out/production/CordanceViewerCS2310/CordanceParser/testBookSourcePP.txt"};

        ParsedCordance pc = new ParsedCordance(fileLocations);

        System.out.println(pc.getPositionInfoByWordID(4));


    }

}



