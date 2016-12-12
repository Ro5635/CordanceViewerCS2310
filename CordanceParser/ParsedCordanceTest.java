package CordanceParser;

import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

/**
 * Created by robert on 01/12/2016.
 */
public class ParsedCordanceTest {

    /**
     * Test that the indexing and creation of the words is working correctly
     */
    @Test
    public void testWordCreation() throws URISyntaxException {

        //Include the test data file, it is included in the source
        String[] fileLocations = {"out/production/CordanceViewerCS2310/CordanceParser/testBookSorce.txt", ""};

        ParsedCordance pc = new ParsedCordance(fileLocations);

        System.out.println(pc.getPositionInfoByWordID(4));


    }

}



