package CordanceParser;


import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by robert on 02/12/2016.
 */
public class Position {

    /**
     *
     */
    private HashMap<String, String> currentBlockBreaksValue;

    /**
     *
     */
    private ArrayList<String> listedBlockBreakes;

    /**
     *
     */
    private ArrayList< ArrayList<String> > wordBlockInformation;

    public Position() {

        //Initialise a new empty word block
        wordBlockInformation = new ArrayList<ArrayList<String>>();

        //Initialise a new list of block breaks
        listedBlockBreakes = new ArrayList<String>();

        //Initialise the data structure to hold the current values of the breaks
        currentBlockBreaksValue = new HashMap<String, String>();


    }



    public void addNewBlockBreak(String breakName) throws InvalidParameterException{

        //Ensure the break has not all ready been created.
        if(currentBlockBreaksValue.containsKey(breakName)){
            throw new InvalidParameterException("Break name all ready created, attempted to create: " + breakName);

        }

        //Add the new break
        listedBlockBreakes.add(breakName);

        //Add the new break to the current values with a default start of 0
        currentBlockBreaksValue.put(breakName, "0");


    }

    public void updateBlockBreakValue(String breakName, String value){

        //How to handle a non existing value ????

        //Update the data structure
        currentBlockBreaksValue.put(breakName, value);

    }


    public void addWordID(Integer wordID){

        ArrayList<String> positionData = new ArrayList<String>();

        for (String blockBreak : listedBlockBreakes) {

            //Add each break block to the list at its current value
            positionData.add(currentBlockBreaksValue.get(blockBreak) );

        }

        //Add teh wordID to the word information data strucutre along with the values
        //of each of the current break points in the book.
        wordBlockInformation.add(wordID, positionData);

    }


    public Map<String, String> getPositionInfoForWordID(Integer wordID){//THERE ARE A LOT OF POTENTIAL ERRORS THAT NEED CATCHING HERE

        //Get the position values for the wordID
        ArrayList<String> positionValues = wordBlockInformation.get(wordID);

        //build the Map of block names and there values
        Map<String, String> positionInfo = new LinkedHashMap<String, String>();

        //index used to track position in the block breaks name list
        int indexCurrentBreakName = 0;

        for (String positionValue: positionValues ) {

            //Add the position name and its value to the map
            positionInfo.put(listedBlockBreakes.get(indexCurrentBreakName++) , positionValue);

        }


        return positionInfo;
    }



}
