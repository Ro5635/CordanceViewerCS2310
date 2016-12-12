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
         * Initialise the WordTable
         *
         * TO DO

         Use huristics based on the file size to estimate a suitable initial capacity, for now set
         a reasonable number....
         */

        wordTable = new WordTable();


        //Create variable to track the line number
        long lineNo = 0;



        //Parse each file in turn
        for (String fileName : fileLocation) {

            try {

                //Check to see if it is a URL, if it is a URL handle the URL. Else pass it to buffered reader as a
                //FileReader, this only runs once per source handed to the cordance. The delay of the minimal additional
                //overhead is more then offset by the additional capability to use URL's as test files.

                if(true) {

                    buffRead = new BufferedReader(new FileReader(fileName));
                    wordScanner = new Scanner(buffRead);

                }else{

                    try {

                        URL newURL = new URL(fileName);
                        URLConnection urlConnection =  newURL.openConnection();
                        buffRead = new BufferedReader(new InputStreamReader( urlConnection.getInputStream() ));




                    }catch (MalformedURLException e){

                        //Re-throw the the exception as a bad file exception.
                        throw new InvalidParameterException("The passed URL is malformed, URL: " + fileName);
                    }catch (IOException e){

                        ///TO DO
                    }

                }

                //Holds the most recently read word
                String newWord;

                //Set the deliminator for tockanisation
                wordScanner.useDelimiter("--|\\n|\\r|\\p{javaWhitespace}+");


                //Read the first lines of the text which contain the title and the author
                ///and add these to the position object
                while (wordScanner.hasNext() && lineNo < 3) {

                    String newLine = wordScanner.nextLine();

                    lineNo++;

                    if (newLine != null && newLine != "") {

                        //Defined as the title line for all conforming inputs

                        //split the break line into the break name and its value
                        String[] breakPointSplit = splitBreakPoint(newLine);

                        positionInfo.updateBlockBreakValue(breakPointSplit[0], breakPointSplit[1]);

                    }else{
                                         /*
                        * This is an new line (black line)
                         */

                        //new lines are represented by null in the word list
                        int newWordID = wordList.addWord(null);


                        //add to the wordtable also, this would allow for the user in theory to
                        //search for new lines.
                        wordTable.addWord(null, newWordID);

                        //register this with the position object
                        positionInfo.addWordID(newWordID);

                    }

                }//End title and author special read

                //keep track of the number of successive empty lines, start at 1 because there is only 2
                //lines to the first break section.
                int successiveEmptyLines = 1;

                //build the regex parser for a break point here outside the loop:
                String regexAllCaps = "m/^[^\\p{Ll}]*$/";
                //create the pattern object
                Pattern patternAllCaps = Pattern.compile(regexAllCaps);


                //Go through the rest of the text, detecting any block sections along the way
                //by there 3 blank line head and all capital nature.
                while (wordScanner.hasNext()) {

                    newWord = wordScanner.next();

                    lineNo++;   //wordNo NOT lineNo

                    //If it is an new line place a null.

                    //REGEX find blank lines or length == 0 speed comparison
                    //if( wordScanner.findInLine("\\A\\S") == null){
                    if (newWord.length() == 0) {

                        if (++successiveEmptyLines == 3) {
                            //There is a chance that there is a break point here
                            boolean found = patternAllCaps.matcher(newWord).matches();

                            if (found) {

                                //update the new breakpoint and description, these will be the next two tokens
                                positionInfo.updateBlockBreakValue(wordScanner.next(), wordScanner.next());

                                //Reset the number of empty lines
                                successiveEmptyLines = 0;
                            }

                        } else {

                        /*
                        * This is an new line (black line)
                         */

                            //new lines are represented by null in the word list
                            int newWordID = wordList.addWord(null);


                            //add to the wordtable also, this would allow for the user in theory to
                            //search for new lines.
                            wordTable.addWord(null, newWordID);

                            //register this with the position object
                            positionInfo.addWordID(newWordID);
                        }

                    } else {

                        //Reset the number of successive empty lines
                        successiveEmptyLines = 0;

                        int newWordID = wordList.addWord(newWord);

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
     * @param breakpointLine
     * @return
     */
    private String[] splitBreakPoint(String breakpointLine) {

        //Split the line on each observance of a space
        String[] splitLine = breakpointLine.split(" ");

        //Get the name of the breakpoint
        String breakPointName = splitLine[0];

        //The remainder of the array is the value of the breakpoint
        String breakPointValue = "";

        //Use string builder in the place of concatenating the rest of the string as it is faster ( O(n) ).
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < splitLine.length; i++) {

            stringBuilder.append(splitLine[i]);

        }

        breakPointValue = stringBuilder.toString();

        return new String[]{breakPointName, breakPointValue};

    }


    /**
     * @param wordID
     * @return
     * @throws InvalidParameterException
     */
    public String getWordByID(int wordID) throws InvalidParameterException {

        return wordList.getWord(wordID);
    }

    /**
     * @param word
     * @return
     * @throws InvalidParameterException
     */
    public ArrayList<Integer> getIDsForWord(String word) throws InvalidParameterException {

        return wordTable.getWordIDs(word);
    }

    /**
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
