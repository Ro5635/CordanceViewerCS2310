package cordance;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Map;

import CordanceParser.ParsedCordance;


/**
 * cordance veiwer runs the algorithm which takes the data sets and outputs a string
 * with the cordances in it.
 * @author ryan
 *
 */
public class CordanceVeiwer {
	//the data holder whish this peruses for the relevent words/values
	private ParsedCordance parsedcordance;
	
	//the stored output for this class which is used in making an extended view
	private ArrayList<Integer> output;
	
	
	/**
	 *  constructor sets up data and requests a data set to use
	 *  @param ParsedCordance
	 */
	public CordanceVeiwer(ParsedCordance passedParsedcordance ) { 
		parsedcordance = passedParsedcordance;
		output = new ArrayList<>();
	}
	/**
	 *  returns a string with all the words cordances in with num number of words to the left and right
	 * @param word
	 * @param num
	 * @return
	 */
	public String getCordance(String word, int num){
		output.clear();
		String cordance = "";
		
		ArrayList<Integer> temp;// = new ArrayList<Integer>(11);
		try{
			temp = parsedcordance.getIDsForWord(word);
			int ID = 0;//id for output to the user which word to look at in the extended view
			int size = parsedcordance.getWordListSize();
			
			//loop through the arraylist of indexes of word positions
			for(Integer currentPos:temp ){
				cordance += "\n" + ID + ": ";
				ID++;
				cordance += getLineOfCordance(currentPos, num, size);
				output.add(currentPos);
			}
		}catch (InvalidParameterException e){
			System.out.println("please put in a valid word");
			
		}
		return cordance;
	}
	/**
	 * will return a cordance with wider context and more details using the last output used with num being the
	 * number of words left and right and kwickid being the requested id
	 * @param num
	 * @param kwicID
	 * @return
	 */
	public String getWiderContext(int num, String kwicID){
		Map<String, String> wordCompact;
		if(output.isEmpty() == true){
			wordCompact = "please enter an actual cordance to veiw first";
		}else{
			
			wordCompact += parsedcordance.getPositionInfoByWordID(output.get(Integer.parseInt(kwicID)).intValue());
			
		}
		return wordCompact;
	}
	
	/**
	 * takes currentpos which is the current position in the words arraylist
	 * num which is the length of cordance it wants 
	 * and the size of the total word list and gives a single line of the cordance
	 * @param currentPos
	 * @param num
	 * @param size
	 * @return
	 */
	private String getLineOfCordance (int currentPos , int num, int size){
		String cordanceline = "";
		//writes the cordance for the num of words left and right
		for (int i = currentPos - num ;i<currentPos + num +1;i++){
			if(0 < i && i < size){// if i is in the bounds of the whole array 
				if(parsedcordance.getWordByID(i) != null){//if the book dosent shows a line brake
					cordanceline += (parsedcordance.getWordByID(i) + " ");//put word in an array
				}else{
					//delete all words up to the middle if they have a line brake
					if(i < currentPos){
						cordanceline = "";
					}else if(i > currentPos){// stop the loop if the linebrake is after middle
						i = currentPos+num+1;
					}
				}				
			}
		}
		return cordanceline;
	}
	
	
	/**
	 *  allows reseting of data
	 * @param passedWordIndex
	 * @param passedWordCataloge
	 */
	public void setData( ParsedCordance passedParsedCordance ) {
		parsedcordance = passedParsedCordance;
	}
	
	
	
	
}
