package CordanceParser;


import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.IntBinaryOperator;


/**
 * Position class tracks the breakpoints used within the cordance, the breakpoint values are recorded for each of the
 * wordIDs that are passed to it. The break points are then accessed using the wordID.
 *
 * @author Robert Curran
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
    private Map< Integer , ArrayList<String> > wordBlockInformation;

    public Position() {

        //Initialise a new empty word block
        wordBlockInformation = new HashMap<Integer , ArrayList<String> >();

        //Initialise a new list of block breaks
        listedBlockBreakes = new ArrayList<String>();

        //Initialise the data structure to hold the current values of the breaks
        currentBlockBreaksValue = new HashMap<String, String>();


    }



    private void addNewBlockBreak(String breakName) throws InvalidParameterException{

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

        //If the break is not all ready defined in the system define it
        if(!currentBlockBreaksValue.containsKey(breakName)){
            //Add the new block break
            addNewBlockBreak(breakName);            //TO DO HANDLE THE EXCEPTION

        }

        //Update the data structure
        currentBlockBreaksValue.put(breakName, value);

    }


    public void addWordID(Integer wordID){

        ArrayList<String> positionData = new ArrayList<String>();

        for (String blockBreak : listedBlockBreakes) {

            //Add each break block to the list at its current value
            positionData.add(currentBlockBreaksValue.get(blockBreak) );

        }

        //Add the wordID to the word information data structure along with the values
        //of each of the current break points in the book.
        wordBlockInformation.put(wordID, positionData);

    }


    public Map<String, String> getPositionInfoForWordID(Integer wordID) throws InvalidParameterException {//THERE ARE A LOT OF POTENTIAL ERRORS THAT NEED CATCHING HERE

        //Get the position values for the wordID
        ArrayList<String> positionValues = wordBlockInformation.get(wordID);

        //build the Map of block names and there values
        Map<String, String> positionInfo = new LinkedHashMap<String, String>();

        //index used to track position in the block breaks name list
        int indexCurrentBreakName = 0;

        try {

            for (String positionValue : positionValues) {

                //If the position value is not null add the position name and its value to the map, null values are in feilds
                //used in other texts but not this current text.
                if (positionValue != null && positionValue != " ") {
                    positionInfo.put(listedBlockBreakes.get(indexCurrentBreakName), positionValue);
                }

                indexCurrentBreakName++;

            }

        }catch( NullPointerException e){
            throw new InvalidParameterException("The passed wordID does not exist in the position tracker, the passed wordID was: " + wordID);

        }

        return positionInfo;
    }

    /**
     * This method should be called when the text that is being used is changed. This is so that tracking of the block breaks
     * continues correctly for the new source with different breakpoints
     *
     * This guards against the situation where the first text added has a break section (such as edition) that the second
     * book does not contain, calls to the position information without this method would list the edition for text two also.
     */
    public void registerSourceTextChange(){

        //Reset all of the beaks that are currently listed to null, therefore unless they are addressed in teh new source they will
        //not be given in the output.

        currentBlockBreaksValue.keySet();

        for( Map.Entry<String, String> key : currentBlockBreaksValue.entrySet() ){
            //Set the value of the entry to null
            key.setValue(null);

        }



    }



}
