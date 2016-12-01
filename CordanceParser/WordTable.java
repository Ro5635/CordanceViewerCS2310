package CordanceParser;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * WordTable holds the relevant wordIDs for every supplied word.
 *
 * @author Robert Curran
 * @date 30/11/2016
 */
  class WordTable {

    /**
     * Contains each unique word in the system and the indexes for which the word appear in wordIndex.
     * @see ArrayList wordIndex
     */
    private HashMap<String, ArrayList<Integer>> wordTable;

    /**
     * Constructor for WordTable, sets up the object for use.
     */
    public WordTable(){

        //Instantiate teh data structure
        wordTable = new HashMap<String, ArrayList<Integer>>();

    }


    /**
     * Adds a word to the data structure, when you request all the savd IDs for a word this new ID would be returned for
     * the given word.
     * @param WordID The ID to be used to represent this instance of the word.
     */
    public void addWord(String word, int  wordID){

        //is the word in the wordTable all ready
        if( wordTable.containsKey(word) ){
            //Add the new wordID to the existing record
            ArrayList<Integer> existingIndex = (ArrayList<Integer>) wordTable.get(word);
            existingIndex.add(wordID);

            wordTable.put(word , existingIndex);

        }else{
            //Create the new record

            ArrayList<Integer> newIndexArray = new ArrayList<Integer>();

            newIndexArray.add(wordID);

            wordTable.put(word, newIndexArray );


        }

    }

    /**
     * Gets ArrayList of WordIDs for the supplied word, the IDs are returned as an ArrayList<Integer>
     *
     * @param targetWord The word to get the corresponding WordIDs for
     * @return ArrayList<Integer> All of the recorded wordIDs for the given word
     */
    public ArrayList<Integer> getWordIDs(String targetWord) throws InvalidParameterException{

        try {

            //return the ArrayList of WordID's for the given word
            return wordTable.get(targetWord);

        }catch(NullPointerException e){

            System.err.println(e.toString());

            //rewrite the access exception as an exception in the passed parameter.
            throw new InvalidParameterException("That key did not exist, attempted key: " + targetWord);

        }
    }


}
