package CordanceParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by robert on 30/11/2016.
 */
public class ParsedCordance {

    /**
     * List of all words in the cordance, new lines and paragraph breaks are denoted by null values
     */
    private WordList wordList;

    /**
     * Contains each unique word in the system and the indexes for which the word appear in wordIndex.
     * @see WordTable
     */
    private WordTable wordTable;


    private Position positionInfo;

    /**
     * Constructor for ParsedCordance
     *
     * @param fileLocation The file location of the file for which the cordance is to be created
     */
    public ParsedCordance(String fileLocation) {

        //Create a new position to hold position data
        positionInfo = new Position();

        //Create a new buffered reader
        BufferedReader buffRead = null;
        Scanner wordScanner = null;

        /*
        Initialise the variable to place the data into
        TO DO

        Use huristics based on the file size to estimate a suitable initial capacity, for now set
        a resonable number....
        */
        wordList = new WordList(40000);


        /**
         * Initilaise the WordTable
         *
         * TO DO

         Use huristics based on the file size to estimate a suitable initial capacity, for now set
         a resonable number....
         */

        wordTable = new WordTable();


        try {

            //Create variable to track the line number
            long lineNo = 0;
            //Create the variable to track the number of succesive empty lines, 3 blank lines in a row
            //is a indicator of a block break.
            int noSuccessiveNewLines = 0;

            buffRead = new BufferedReader(new FileReader(fileLocation));
            wordScanner = new Scanner(buffRead);


            String newWord;
            //Set the deliminator
            wordScanner.useDelimiter("--|\\n|\\p{javaWhitespace}+");


            while (wordScanner.hasNext()) {

                newWord = wordScanner.next();

                lineNo++;

                //If it is an new line place a null.

                //REGEX find blank lines or length == 0 speed comparison
                //if( wordScanner.findInLine("\\A\\S") == null){
                if (newWord.length() == 0) {

                    int newWordID = wordList.addWord(null);

                    //add to the wordtable also, this would allow for the user in theory to
                    //search for new lines.
                    wordTable.addWord(null, newWordID);

                } else {

                    int newWordID = wordList.addWord(newWord);

                    //Add the word to the positionInfo object
                    positionInfo.addWordID(newWordID);

                    //Add to wordtable
                    wordTable.addWord(newWord, newWordID);

                }


            }


        } catch (FileNotFoundException e) {
            /* TO DO
            *  Give user a chance to try again not just give up.
            * */

            System.out.println("File not found");
            e.printStackTrace();
            System.exit(1);

        }





    }


    /**
     *
     * @param wordID
     * @return
     * @throws InvalidParameterException
     */
    public String getWordByID(int wordID) throws InvalidParameterException {

        return wordList.getWord(wordID);
    }

    /**
     *
     * @param word
     * @return
     * @throws InvalidParameterException
     */
    public ArrayList<Integer> getIDsForWord(String word) throws InvalidParameterException{

        return wordTable.getWordIDs(word);
    }

    /**
     *
     * @param wordID
     * @return
     * @throws InvalidParameterException
     */
    public Map<String, String> getPositionInfoByWordID(Integer wordID) throws InvalidParameterException{

        return positionInfo.getPositionInfoForWordID(wordID);
    }

    /**
     * Gets the current word list size
     *
     * @return int current word list size
     */
    public int getWordListSize(){

        return wordList.getListSize();

    }


}
