package cordance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cordance Spliter
 *
 * This takes the file location of a book, from this it reads in the book to the system spliting it up as required. The methord to do this
 */
public class CordanceSpliter {

    /**
     * Array list of all words in the cordance, new lines and paragraph breaks are denoted by null values
     */
    private ArrayList wordIndex;

    /**
     * Contains each unique word in the system and the indexes for which the word appear in wordIndex.
     * @see ArrayList wordIndex
     */
    private HashMap wordCataogue;





    /**
     * Constructor for CordanceSpliter
     *
     * @param fileLocation The file location of the file for which the cordance is to be created
     */
    public CordanceSpliter(String fileLocation){

        //Create a new buffered reader
        BufferedReader buffRead = null;

        try{

            buffRead = new BufferedReader( new FileReader( fileLocation ) );

            int lineNum = 0;

            String newLine = buffRead.readLine();

            while(newLine != null){
                System.out.println("Line " + lineNum + newLine);
                newLine = buffRead.readLine();
                lineNum++;
            }



        }catch (FileNotFoundException e) {
            /* TO DO
            *  Give user a chance to try again not just give up.
            * */

            System.out.println("File not found");
            e.printStackTrace();
            System.exit(1);

        }catch (IOException e){

            System.out.println("IO Exception");


        }







    }


    /**
     *  Get's the word index for the cordance
     */
    public ArrayList getWordIndex(){



    }

    /**
     *  Get's the word catalogue for the cordance.
     */
    public HashMap getWordCatalogue(){


    }


}
