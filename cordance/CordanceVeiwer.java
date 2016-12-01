package cordance;

import java.util.ArrayList;

import CordanceParser.ParsedCordance;



public class CordanceVeiwer {
	
	private ParsedCordance parsedcordance;
	
	private ArrayList<ArrayList<String>> output;
	
	
	/**
	 *  constructor sets up data
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
		ArrayList<Integer> temp = parsedcordance.getIDsForWord(word);
		System.out.println(temp);
		int ID = 0;
		
		String cordance = "";
		ArrayList<String> cordanceSet = new ArrayList<String>();
		
		//
		int size = parsedcordance.getWordListSize();//get size once to make it run faster
		//
		
		//loop through the hashmaps arraylist of indexes of word positions
		for(Integer currentPos:temp ){
			cordance += "\r" + ID + ": ";
			ID++;
			//writes the cordance for the num of words left and right
			
			for (int i = currentPos.intValue() - num ;i<currentPos.intValue() + num;i++){
				if(0 < i && i < size){// if i is in the bounds of the whole array 
					if(parsedcordance.getWordByID(i) != null){//if the book dosent shows a line brake
						cordanceSet.add(parsedcordance.getWordByID(i) + " ");//put word in an array
					}else{
						//delete all words up to the middle if they have a line brake
						if(i < currentPos.intValue()){
							for(int a = 0;a<cordanceSet.size();a++){
								cordanceSet.set(a, " _X_ ");
							}
						}else if(i > currentPos.intValue()){// stop the loop if the linebrake is after middle
							i = currentPos.intValue()+num+1;
						}
					}
					output.add(cordanceSet);
					cordance += getStringOfCordance(cordanceSet);
					
				}
				cordanceSet = new ArrayList<String>();
			}
			
		}
		return cordance;
	}
	/**
	 * will return a cordance with wider context and more details
	 * @param word
	 * @param num
	 * @return
	 */
	public String getWiderContext(String word,int num){
		String a = "";
		if(output.isEmpty() == true){
			a = "please enter an actual cordance to veiw first";
		}else{
			
		}
		
		return a;
	}
	
	
	
	private String getStringOfCordance( ArrayList<String> input){
		String a = "";
		for(String b:input){
			a += b;
		}
		return a;
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
