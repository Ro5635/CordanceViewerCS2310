package cordance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
     * Array list of descriptions of the differnt postions avalible in the book.
     */
    private ArrayList positionName;





    /**
     * Constructor for CordanceSpliter
     *
     * @param fileLocation The file location of the file for which the cordance is to be created
     */
    public CordanceSpliter(String fileLocation){

        //Create a new buffered reader
        BufferedReader buffRead = null;
        Scanner wordScanner = null;

        /*
        Initialise the variable to place the data into
        TO DO

        Use huristics based on the file size to estimate a suitable initial capacity, for now set
        a resonable number....
        */
        wordIndex = new ArrayList<String>(200000);


        try{

            buffRead = new BufferedReader( new FileReader( fileLocation ) );
            wordScanner = new Scanner(buffRead);


            int lineNum = 0;

            String newWord;
            //Set the deliminator
            wordScanner.useDelimiter("--|\\n|\\p{javaWhitespace}+");


            while(wordScanner.hasNext()){
                lineNum++;
                newWord = wordScanner.next();

                //If it is an new line place a null.

                //REGEX find blank lines or length == 0 speed comparison
                //if( wordScanner.findInLine("\\A\\S") == null){
                if(newWord.length() == 0){

                    wordIndex.add(null);

                }else{

                    wordIndex.add(newWord);
                }




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


        return new ArrayList(33);

    }

    /**
     *  Get's the word catalogue for the cordance.
     */
    public HashMap getWordCatalogue(){

        return new HashMap();
    }

    /**
     * Gets the array of differnt positions avalible in the application.
     * @return ArrayList<String> descriptions of the different positions in the book
     */
    public ArrayList getPositionName(){




        return new ArrayList(33);

    }


    public static void main(String args[]){

        CordanceSpliter cs = new CordanceSpliter("/Users/robert/Desktop/abook.txt");




    }


}
