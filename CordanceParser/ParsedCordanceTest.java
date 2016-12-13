package CordanceParser;

import org.junit.Test;

import java.util.ArrayList;


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
        String[] fileLocations2 = {"CordanceParser/testBookSourceEmma.txt" ,"CordanceParser/testBookExpressMansfeild.txt"};
        String[] fileLocations = {"cordance/emmaEd11Test.txt", "cordance/mansfieldParkEd10Test.txt"};


        ParsedCordance pc = new ParsedCordance(fileLocations2);



        ArrayList<Integer> test =  pc.getIDsForWord("Ward");

        System.out.println("TRY: " + test.size());

        for (Integer a: test) {

            System.out.println(a);

        }

//
//        System.out.println("Value = " + pc.getWordByID(24812));
//
//        for(int i = (24812 - 10); i < (24812 + 10); i++ ){
//            System.out.println( pc.getWordByID(i) );
//        }
//
//
//        System.out.println(pc.getIDsForWord("a"));
//
//        for(int i =0; i <= 23; i++){
//
//            System.out.print( pc.getWordByID(i) + " "  );
//
//        }

       //System.out.println(pc.getIDsForWord(null));

        //System.out.println("THIS: " +  pc.getWordByID(3) );















    }

}



