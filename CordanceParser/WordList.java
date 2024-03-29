package CordanceParser;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Models a list of words accessed by a integer ID.
 * @author Robert Curran
 * @Date 30/11/16
 */
    class WordList {

    /**
     * Array list of all words in the word index, new lines and paragraph breaks are denoted by null values.
     */
    private ArrayList<String> wordIndex;

    private int currentIndex;


    /**
     * Constructor for the wordlist, creates a new empty wordlist.
     *
     */
    public WordList(){
        //Initialise the word list
        wordIndex = new ArrayList<String>();

        //Set the current index position to 0
        currentIndex = 0;

    }

    /**
     * Constructor for the wordlist, creates a new empty wordlist.
     *
     * @param InitialSize int Initial size for the underlying data structure, set this to 10% larger than the expected
     *                    size of the word list for the most efficient 'addWord' operation.
     */
    public WordList(int InitialSize){
        //Initialise the word list
        wordIndex = new ArrayList(InitialSize);

        //Set the current index position to 0
        currentIndex = 0;
    }



    /**
     *
     * @param word String The word to be saved in the data structure
     * @return Integer currentIndex The position where the inserted word is stored
     */
    public Integer addWord(String word){

        wordIndex.add(word);

        return currentIndex++;
    }

    /**
     *
     * @param indexPosition int the position to get the word for
     * @return String word at the given position
     */
    public String getWord(int indexPosition) throws InvalidParameterException{

        try{

            return wordIndex.get(indexPosition);

        }catch (IndexOutOfBoundsException e){
            //Re-throw as a parameter invalid exception
            throw new InvalidParameterException();
        }
    }


    /**
     * Gets the current size of the word list
     *
     * @return int size of the current word list
     */
    public int getListSize(){
        return wordIndex.size();
    }


}
