import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CordanceVeiwer {
	
	private ArrayList<String> wordIndex;// the arraylist of all words in the book in order
	private HashMap<String, ArrayList<Integer>> wordCataloge;// the hashmap of all the different words with there indexes 
	//the first element int he arraylist of the hashmap is the chapter in the book
	
	public CordanceVeiwer(ArrayList<String> passedWordIndex, HashMap<String, ArrayList<Integer>> passedWordCataloge ) {
		wordIndex = passedWordIndex;
		wordCataloge = passedWordCataloge;
	}
	public String getCordance(String word, int num){
		ArrayList<Integer> temp = wordCataloge.get(word);
		String cordance;
		
		for(Integer currentPos:temp ){
			cordance += "\r";
			for (int i = currentPos.intValue() - num ;i<currentPos.intValue() + num;i++){
				cordance += wordIndex.get(i);
			}
		}
		return cordance;
	}
	
	public void setData(ArrayList<String> passedWordIndex, HashMap<String, ArrayList<Integer>> passedWordCataloge ) {
		wordIndex = passedWordIndex;
		wordCataloge = passedWordCataloge;
	}
}
