package cordance;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.Position;



public class CordanceVeiwer {
	
	private ArrayList<String> wordIndex;// the arraylist of all words in the book in order
	private HashMap<String, ArrayList<Integer>> wordCataloge;// the hashmap of all the different words with there indexes 
	//the first element int he arraylist of the hashmap is the chapter in the book
	private ArrayList<String> positionName;//holds the chapeters of the words
	
	/**
	 *  constructor sets up data
	 * @param passedWordIndex
	 * @param passedWordCataloge
	 * @param passedPositionName
	 */
	public CordanceVeiwer(ArrayList<String> passedWordIndex, HashMap<String, ArrayList<Integer>> passedWordCataloge ,ArrayList<String> passedPositionName ) {
		wordIndex = passedWordIndex;
		wordCataloge = passedWordCataloge;
		positionName = passedPositionName;
	}
	/**
	 *  returns a string with all the words cordances in with num number of words to the left and right
	 * @param word
	 * @param num
	 * @return
	 */
	public String getCordance(String word, int num){
		ArrayList<Integer> temp = wordCataloge.get(word);
		String cordance = "";
		//loop through the hashmaps arraylist of indexes of word positions
		for(Integer currentPos:temp ){
			cordance += "\r";
			cordance += positionName.get(currentPos.intValue());
			//writes the cordance for the num of words left and right
			for (int i = currentPos.intValue() - num ;i<currentPos.intValue() + num;i++){
				if(wordIndex.get(i) != null){
					cordance += wordIndex.get(i) + " ";		
				}
			}
		}
		return cordance;
	}
	/**
	 *  allows reseting of data
	 * @param passedWordIndex
	 * @param passedWordCataloge
	 */
	public void setData(ArrayList<String> passedWordIndex, HashMap<String, ArrayList<Integer>> passedWordCataloge ) {
		wordIndex = passedWordIndex;
		wordCataloge = passedWordCataloge;
	}
}
