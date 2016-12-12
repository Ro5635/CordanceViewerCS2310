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
        String[] fileLocations = {"CordanceParser/testBookSourceEmma.txt", "CordanceParser/testBookSourcePP.txt"};

        ParsedCordance pc = new ParsedCordance(fileLocations);

        System.out.println(pc.getIDsForWord("a"));

        for(int i =7; i <= 15; i++){

            System.out.println( pc.getWordByID(i)  );

        }

        System.out.println(pc.getIDsForWord(null));

        System.out.println("THIS: " +  pc.getWordByID(3) );















    }

}



