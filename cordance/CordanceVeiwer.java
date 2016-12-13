package cordance;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
		StringBuilder cordance = new StringBuilder();
		word.replaceAll("\\s","");
		ArrayList<Integer> temp;// = new ArrayList<Integer>(11);
		try{
			temp = parsedcordance.getIDsForWord(word);
			int ID = 0;//id for output to the user which word to look at in the extended view
			int size = parsedcordance.getWordListSize();
			
			//loop through the arraylist of indexes of word positions
			for(Integer currentPos:temp ){
				cordance.append("\n");
				cordance.append(ID);
				cordance.append(": ");
				ID++;
				cordance.append(getLineOfCordance(currentPos, num, size));
				output.add(currentPos);
			}
		}catch (InvalidParameterException e){
			System.out.println("please put in a valid word that exists in the text");
			System.exit(011);
		}catch (NullPointerException e) {
			System.out.println("please put in a valid word that exists in the text");
			System.exit(100);
		}
		return cordance.toString();
	}
	/**
	 * will return a cordance with wider context and more details using the last output used with num being the
	 * number of words left and right and kwickid being the requested id
	 * @param num
	 * @param kwicID
	 * @return
	 */
	public String getWiderContext(int num, String kwicID){
		int kwicIDInt = (Integer.parseInt(kwicID));
		Map<String, String> wordCompact;
		StringBuilder widerContext = new StringBuilder();
		if(output.isEmpty() == true){
			widerContext.append("please enter an actual cordance to veiw first");
		}else{
			try{//makes a word out of data in the structure position 
				wordCompact = parsedcordance.getPositionInfoByWordID(output.get(kwicIDInt));
				Set<String> wordCompactKeySet = wordCompact.keySet();
				String KeyWord;
				Iterator<String> iter = wordCompactKeySet.iterator();
				while (iter.hasNext()){//iterate through the stucture for the key and value
					//the key is the type and the value is the asigned value so it needs to print the console
					KeyWord = iter.next();
					widerContext.append(KeyWord); 
					widerContext.append(" ");  
					widerContext.append(wordCompact.get(KeyWord)); 
					widerContext.append('\n'); 
				}
				//add word data on the end
				widerContext.append('\n');
				widerContext.append("word number: "); 
				widerContext.append(output.get(kwicIDInt)); 
				widerContext.append('\n');
				widerContext.append( getLineOfCordance(output.get(kwicIDInt), 10, 100));
				
			}catch(Exception e){
				System.out.println("please put in an actual ID that apears in the cordance");
				System.exit(101);
			}
		}
		return widerContext.toString();
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
		StringBuilder cordanceline = new StringBuilder();
		//writes the cordance for the num of words left and right
		for (int i = currentPos - num ;i<currentPos + num +1;i++){
			if(0 < i && i < size){// if i is in the bounds of the whole array 
				if(parsedcordance.getWordByID(i) != null){//if the book dosent shows a line brake
					cordanceline.append(parsedcordance.getWordByID(i)); //put word in an array
					cordanceline.append(" ");
				}else{
					//delete all words up to the middle if they have a line brake
					if(i < currentPos){
						cordanceline = new StringBuilder(); 
					}else if(i > currentPos){// stop the loop if the linebrake is after middle
						i = currentPos+num+1;
					}
				}				
			}
		}
		return cordanceline.toString();
	}
}
