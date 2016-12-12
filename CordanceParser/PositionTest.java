package CordanceParser;

import org.junit.Before;
import org.junit.Test;



import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 *  Unit tests for the Position Class, sequentialy tests aspects of the Position class to ensure that it is functioning
 *  as expected
 *  @author Robert Curran
 *  @See Position
 *  @Date 11/12/16
 */
public class PositionTest {

    private Position positionInfo;

    //test data
    String[][] testDataText1;
    String[][] testDataText2;

    //Run the following method before each test
    @Before
    public void setUp() {
        //Initialise the Position object
        positionInfo = new Position();

        //////Setup Test

        //Define the initial data for the two test texts
        testDataText1 = new String[][] { {"Title", "Author", "Type", "Chapter"} ,
                {"The Martian" , "Andy Weir" , "EBook" , "Many Bad Things Happen"} };

        testDataText2 = new String[][]  { {"Title", "Author", "Edition", "Section"} ,
                {"The Art Of Electronics" , "Horowitz Hill" , "3" , "i"} };


    }


    /**
     * Test that the passing of a breakpoint to the position object and then listing a wordID with that breakpoint
     * is able to return the correct breakpoint information.
     */
    @Test
    public void testPassAndRetrieve() {

        //Add all of the initial data for book 1 to the Position object
        for (int index = 0; index < testDataText1[0].length; index++) {
            positionInfo.updateBlockBreakValue( testDataText1[0][index] , testDataText1[1][index] );

        }

        positionInfo.addWordID(1);

        //Change the current value of the chapter:
        positionInfo.updateBlockBreakValue("Chapter","More Bad Things Happen");

        //Add a word
        positionInfo.addWordID(2);

        //Change the current value of the chapter:
        positionInfo.updateBlockBreakValue("Chapter","Potatoes and ketchup");

        positionInfo.addWordID(3);


        //////Execute Verification of Tests

        //Data structure for holding each of the position results
        Map<String , String> wordPosInfo;

        wordPosInfo  = positionInfo.getPositionInfoForWordID(1);


        //Check that all of the initial values where saved correctly
        for (int index = 0; index < testDataText1[0].length; index++) {

            //Ensure the breakpoint name is retrieved successfully
            assertEquals("Check that breakpoint '" + testDataText1[0][index] + "' is retrieved correctly, Pass And Retrieve Test"
                    , true, wordPosInfo.containsKey( testDataText1[0][index] ));

            //Ensure that the value is correct
            assertEquals("Check that breakpoint '" + testDataText1[0][index] + "'s value is retrieved correctly, Pass And Retrieve Test"
                    , testDataText1[1][index] , wordPosInfo.get( testDataText1[0][index] ));

        }





    }

    /**
     * Test that the updating of single breakpoints within the text works correctly, this is where a single feild in a
     * existing set of feilds is changed. Only the given field should have changed.
     *
     */
    @Test
    public void testUpdatesToBook() {

        //Add all of the initial data for book 1 to the Position object
        for (int index = 0; index < testDataText1[0].length; index++) {
            positionInfo.updateBlockBreakValue( testDataText1[0][index] , testDataText1[1][index] );

        }

        positionInfo.addWordID(1);

        //////Now test update break points in the book:

        //Change the current value of the chapter:
        positionInfo.updateBlockBreakValue("Chapter","More Bad Things Happen");

        //Add a word
        positionInfo.addWordID(2);

        //Change the current value of the chapter:
        positionInfo.updateBlockBreakValue("Chapter","Potatoes and ketchup");

        positionInfo.addWordID(3);


        ////Check that each of the updates was applied

        //Data structure for holding each of the position results
        Map<String , String> wordPosInfo;

        //Check that chapter was updated
        wordPosInfo  = positionInfo.getPositionInfoForWordID(2);

        assertEquals("Test that retrieval of a updated value returns as set"
                , "More Bad Things Happen" , wordPosInfo.get("Chapter") );

        //Check the chapter was updated again
        wordPosInfo = positionInfo.getPositionInfoForWordID(3);

        assertEquals("Test that retrieval of a updated value returns as set"
                , "Potatoes and ketchup" , wordPosInfo.get("Chapter") );


        //Ensure that values that have not been updated are still as initially set, the breakpoint that we have changed
        //is the chapter, this is the last position in the array. Clearly this will have changed so it will not be tested.
        for (int index = 0; index < (testDataText1[0].length - 1); index++) {

            //Ensure the breakpoint name is retrieved successfully
            assertEquals("Check that breakpoint '" + testDataText1[0][index] + "' is retrieved correctly, Update single breakpoint test"
                    , true, wordPosInfo.containsKey( testDataText1[0][index] ));

            //Ensure that the value is correct
            assertEquals("Check that breakpoint '" + testDataText1[0][index] + "'s value is retrieved correctly, Update single breakpoint test"
                    , testDataText1[1][index] , wordPosInfo.get( testDataText1[0][index] ));

        }


    }



    /**
     * Test that the complete change of text results in propagation of the new data
     *
     */
    @Test
    public void testChangeOfSourceText() {

        //Add all of the initial data for book 1 to the Position object
        for (int index = 0; index < testDataText1[0].length; index++) {
            positionInfo.updateBlockBreakValue( testDataText1[0][index] , testDataText1[1][index] );

        }

        positionInfo.addWordID(1);


        //Register the change in source text
        positionInfo.registerSourceTextChange();

        //Add the initial data for the second book to the Position object
        for (int index = 0; index < testDataText2[0].length; index++) {
            positionInfo.updateBlockBreakValue( testDataText2[0][index] , testDataText2[1][index] );

        }

        positionInfo.addWordID(4);

        positionInfo.updateBlockBreakValue("Section", "ii");

        positionInfo.addWordID(5);

        ////Check the second text source

        //Data structure for holding each of the position results
        Map<String , String> wordPosInfo;


        //Load the values at wordID 4, this is where the second text is used
        wordPosInfo = positionInfo.getPositionInfoForWordID(4);


        //Check that all of the initial values of text 2 where saved correctly
        for (int index = 0; index < testDataText2[0].length; index++) {

            //Ensure the breakpoint name is retrieved successfully
            assertEquals("Check that breakpoint '" + testDataText2[0][index] + "' is retrieved correctly, Pass And Retrieve Test"
                    , true, wordPosInfo.containsKey( testDataText2[0][index] ));

            //Ensure that the value is correct
            assertEquals("Check that breakpoint '" + testDataText2[0][index] + "'s value is retrieved correctly, Pass And Retrieve Test"
                    , testDataText2[1][index] , wordPosInfo.get( testDataText2[0][index] ));

        }

        ////Check that the single updates where applied

        wordPosInfo  = positionInfo.getPositionInfoForWordID(5);

        assertEquals("Test that retrieval of a updated value returns as set"
                , "ii" , wordPosInfo.get("Section") );


        //Test that there is no breakpoints from testText1 that have been brought to testText2 incorrectly, breakpoint
        //'Type' should not be present on a wordID from testText2 as this is not a breakpoint used in this text.
        assertEquals("Testing that breakpoints from testData1 where not brought to testText2 incorrectly", false, wordPosInfo.containsKey("Type"));



    }





}
