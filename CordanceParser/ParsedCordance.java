package CordanceParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.InvalidParameterException;
import java.util.ArrayList;
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



    /**
     * Constructor for ParsedCordance
     *
     * @param fileLocation The file location of the file for which the cordance is to be created
     */
    public ParsedCordance(String fileLocation) {

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

            buffRead = new BufferedReader(new FileReader(fileLocation));
            wordScanner = new Scanner(buffRead);


            String newWord;
            //Set the deliminator
            wordScanner.useDelimiter("--|\\n|\\p{javaWhitespace}+");


            while (wordScanner.hasNext()) {

                newWord = wordScanner.next();

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



    public String getWordByID(int wordID) throws InvalidParameterException {

        return wordList.getWord(wordID);
    }

    public ArrayList<Integer> getIDsForWord(String word) throws InvalidParameterException{

        return wordTable.getWordIDs(word);
    }

    public String getPositionInfoByWordID() throws InvalidParameterException{
        //TO Do
        return "Volume * Chapter *";
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
