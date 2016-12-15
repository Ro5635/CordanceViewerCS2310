package CordanceParser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * PasedCordance, parses a cordance for use in a cordance, accepts an array of file locations. Allows for the querying of
 * line information such as the title and line number. This information is extracted from the text using the standards
 * defined on: https://IMUSTPUTTHATLINKHERE.com
 *
 * @author Robert Curran
 * @date 5 December 2016
 */
public class ParsedCordance {

    /**
     * List of all words in the cordance, new lines and paragraph breaks are denoted by null values
     * @see WordList
     */
    private WordList wordList;

    /**
     * Contains each unique word in the system and the indexes for which the word appear in wordIndex.
     *
     * @see WordTable
     */
    private WordTable wordTable;


    private Position positionInfo;

    /**
     * Constructor for ParsedCordance
     *
     * @param fileLocation The file location of the file for which the cordance is to be created
     */
    public ParsedCordance(String[] fileLocation) {

        //Create a new position to hold position data
        positionInfo = new Position();

        //Create a new buffered reader
        BufferedReader buffRead;
        Scanner wordScanner;

        /*
        Initialise the variable to place the data into
        TO DO

        Use huristics based on the file size to estimate a suitable initial capacity, for now set
        a resonable number....
        */
        wordList = new WordList(40000);


        /**
         * Initialise the WordTable
         *
         * TO DO

         Use heuristics based on the file size to estimate a suitable initial capacity, for now set
         a reasonable number....
         */

        wordTable = new WordTable();


        //Create variable to track the line number
        long lineNo = 0;



        //Parse each file in turn
        for (String fileName : fileLocation) {

            try {

                //Register the the change in the source text
                positionInfo.registerSourceTextChange();


                //Check to see if it is a URL, if it is a URL handle the URL. Else pass it to buffered reader as a
                //FileReader, this only runs once per source handed to the cordance. The delay of the minimal additional
                //overhead is more then offset by the additional capability to use URL's as test files.


                    buffRead = new BufferedReader(new FileReader(fileName));
                    wordScanner = new Scanner(buffRead);


                //Holds the most recently read word
                String newWord;

                //Set the deliminator for tockanisation
                wordScanner.useDelimiter("--|\\r|\\n|\\p{javaWhitespace}+");


                //Track the line number
                int readlines = 0;

                //Read the first 3 lines of the text which contain the title and the author
                ///and add these to the position object
                while (wordScanner.hasNext() && readlines < 3) {

                    String newLine = wordScanner.nextLine();


                    lineNo++;

                    if (newLine != null && newLine.replaceAll("[^a-zA-Z ]", "") != "" ) {

                        //First line title and third line author for all conforming inputs
                        //split the break line into the break name and its value
                        String[] breakPointSplit = splitBreakPoint(newLine);

                        positionInfo.updateBlockBreakValue(breakPointSplit[0], breakPointSplit[1]);

                    }else{

                        /*
                        * This is an new line (blank line)
                        */

                        //new lines are represented by null in the word list
                        int newWordID = wordList.addWord(null);


                        //add to the wordtable also, this would allow for the user in theory to
                        //search for new lines.
                        wordTable.addWord(null, newWordID);

                        //register this with the position object
                        positionInfo.addWordID(newWordID);

                    }

                    readlines++;

                }//End title and author special read




                //keep track of the number of successive empty lines, start at 4 because there is only 1
                //line to the first break section.
                int successiveEmptyLines = 4;

                //build the regex parser for a break point here outside the loop:
                String regexAllCaps = "([A-Z])+";
                //create the pattern object
                Pattern patternAllCaps = Pattern.compile(regexAllCaps);

                //Number of paragraphss
                //This is started at minus two to account for the leading lines as per the text specification
                int paragraphNum = -2;

                //boolean to aid tracking of paragraph number, the transition from empty line to a normal content
                //line marks a paragraph start
                boolean paragraphTransition = true;

                //Register the paragraph breakpoint
                positionInfo.updateBlockBreakValue("Paragraph", "0");



                //Go through the rest of the text, detecting any block sections along the way
                //by there 3 blank line head and all capital nature.
                while (wordScanner.hasNext()) {

                    //remove all punctuation and set to lower case, save teh original value for world listing
                    String originalWord = wordScanner.next();;
                    newWord = originalWord.replaceAll("[^a-zA-Z ]", "").toLowerCase();                             //CLEAN UP

                    lineNo++;                                                                                   //wordNo NOT lineNo

                    //If it is an new line place a null.

                    //REGEX find blank lines or length == 0 speed comparison
                    //if( wordScanner.findInLine("\\A\\S") == null){

                    if (newWord.length() == 0) {

                        successiveEmptyLines++;


                        if(successiveEmptyLines == 2) {

                        /*
                        * This is an new line (blank line)
                         */
                            paragraphTransition = true;

                            //new lines are represented by null in the word list
                            int newWordID = wordList.addWord(null);

                            //add to the wordtable also, this would allow for the user in theory to
                            //search for new lines.
                            wordTable.addWord(null, newWordID);

                            //register this with the position object
                            positionInfo.addWordID(newWordID);
                        }

                    } else {

                        //The new word is not a new line

                        if(successiveEmptyLines == 7  ){

                            //There is a chance that there is a break point here

                            //Ensure that this is not the last line in the document
                            if(wordScanner.hasNext()) {

                                boolean found = patternAllCaps.matcher(originalWord).matches();

                                if (found && wordScanner.hasNext()) {

                                    //update the new breakpoint and description, these will be the next two tokens
                                    positionInfo.updateBlockBreakValue(originalWord, wordScanner.next());

                                }

                            }


                            //Reset the number of successive empty lines
                            successiveEmptyLines = 0;

                        }else{

                            //Reset the number of successive empty lines
                            successiveEmptyLines = 0;
                        }

                        if(paragraphTransition){

                            paragraphNum++;

                            //update the count in the position object
                            positionInfo.updateBlockBreakValue("Paragraph", Integer.toString(paragraphNum) );

                            paragraphTransition = false;
                        }




                        //The original version of the new word with any additional grammar must be added to the word list.
                        int newWordID = wordList.addWord(originalWord);

                        //Add the word to the positionInfo object
                        positionInfo.addWordID(newWordID);

                        //Add to wordtable
                        wordTable.addWord(newWord, newWordID);

                        //register this with the position object, this captures a snapshot of the state of all the break
                        //points at this word id.
                        positionInfo.addWordID(newWordID);

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


        }//End for each file loop


    }


    /**
     * This separates a breakpoint into the value and the title, for example is the string "Title Emma's Book" was passed then
     * the string {"Title", "Emma's Book"} would be feturned by this function.
     * @param breakpointLine
     * @return  String[] breakPointName , breakPointValue
     */
    private String[] splitBreakPoint(String breakpointLine) {

        //Split the line on each observance of a space
        String[] splitLine = breakpointLine.split(" ");

        //Get the name of the breakpoint, strip the punctuation from the name
        String breakPointName = splitLine[0].replaceAll("[^a-zA-Z ]", "");

        //The remainder of the array is the value of the breakpoint, the punctuation is left in the value
        String breakPointValue;

        //Use string builder in the place of concatenating the rest of the string as it is faster ( O(n) ).
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < splitLine.length; i++) {

            stringBuilder.append(splitLine[i] + " ");

        }

        breakPointValue = stringBuilder.toString();

        return new String[]{breakPointName, breakPointValue};

    }


    /**
     * This gets the word that is recorded for a given wordID, every occerance of a word in the cordance has a unique wordID.
     * @param wordID The integer value of wordID
     * @return String represented by the passed wordID
     * @throws InvalidParameterException
     */
    public String getWordByID(int wordID) throws InvalidParameterException {

        return wordList.getWord(wordID);
    }

    /**
     * This gets the WordIDs that match a given string, this allows for the searching of the cordance as any wordIDs that
     * match the passed string will be returned in the ArrayLIst of integers as wordIDs. These wordIds can be used to with
     * the getWordByID method to get the origional string represented by each wordID.
     * @param word The string to get the wordIDs for
     * @return ArrayList of WordIDs
     * @throws InvalidParameterException
     * @see String getWordByID()
     */
    public ArrayList<Integer> getIDsForWord(String word) throws InvalidParameterException {

        //prepare the passed value for search, this means removing punctuation and setting to lowercase.
        word = word.replaceAll("[^a-zA-Z ]", "").toLowerCase();

        return wordTable.getWordIDs(word);

    }

    /**
     * Gets the position information for a wordID, this is returned as a map of the breakpoints. For example it would
     * return the title, author and paragraph number of a given wordID.
     *
     * @param wordID
     * @return
     * @throws InvalidParameterException
     */
    public Map<String, String> getPositionInfoByWordID(Integer wordID) throws InvalidParameterException {

        return positionInfo.getPositionInfoForWordID(wordID);
    }

    /**
     * Gets the current word list size
     *
     * @return int current word list size
     */
    public int getWordListSize() {

        return wordList.getListSize();

    }


}
