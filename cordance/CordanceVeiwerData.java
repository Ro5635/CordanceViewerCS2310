import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CordanceVeiwerData {
	
	private ArrayList<String> wordIndex;// the arraylist of all words in the book in order
	private HashMap<String, ArrayList<Int>> wordCataloge;// the hashmap of all the different words with there indexes 
	//the first element int he arraylist of the hashmap is the chapter in the book
	
	public CordanceVeiwerData(ArrayList<String> passedWordIndex, HashMap<String, ArrayList<int>> passedWordCataloge ) {
		wordIndex = passedWordIndex;
		wordCataloge = passedWordCataloge;
	}
	public String getCordance(String word, int num){
		ArrayList<int> temp = wordCataloge.get(word);
		Iterator<int> iter = temp.itorator;
		private String cordance;
		int currentPos;
		
		while (iter.hasNext()){
			cordance += "\r";
			currentPos = iter.next();
			for (int i = currentPos - num ;i<currentPos + num;i++){
				cordance += wordIndex[i];
			}
		}
		return cordance;
	}
	public setData(ArrayList<String> passedWordIndexer, HashMap<String, ArrayList<int>> passedWordCataloge ) {
		wordIndexer = passedWordIndexer;
		wordCataloge = passedWordCataloge;
	}
}
