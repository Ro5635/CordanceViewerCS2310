package bookView;

import CordanceParser.ParsedCordance;
import cordance.CordanceSpliter;
 
import cordance.CordanceVeiwer;
 

 
public class Cordance implements Controller{
 
	
	//constant size
	private static final int defaultsize  = 10;

  //the tui object which controlls user input
  private static TUI tui ;
  
  //the cordance object which controlls the carous classes and passes values to tui
  private static Cordance cordance;
	
  //the object that models the cordanceveiwer owned by this object
  private CordanceVeiwer cordanceVeiwer;
 

  //the object that models the parsedCordance owned by this object
  private ParsedCordance parsedCordance;
 

  //the temporary word the user wishes to search is held in this
  private String currentWord;
 
  
  //the temporary size the user wishes to generate is held in this
  private int currentSize;
 
  
  /**
   * constructor that initialises variables and makes a new cordancesplitter
   * with the file location supplied
   * the default cordance size is 10 so its default is 10
   * @param filelocation
   */
  Cordance(){
 
    currentSize = defaultsize;
 
    currentWord = "";
 
  }
 
  void SetFileLocation( String fileLocation){
	  parsedCordance = new ParsedCordance(fileLocation);
  }
 
  @Override
  /**
   * returns the cordance with the supplied word
   * @param word
   * 
   */
  public String getKWIC(String word) {
	String value = "";
    if(parsedCordance != null){

        currentWord = word;
     
        currentSize = defaultsize;
     
        cordanceVeiwer = new CordanceVeiwer(parsedCordance);
        value =cordanceVeiwer.getCordance(currentWord, new Integer(10));      
    }else{
    	value = "file not valid please fix.";
    }
    return value;
  }
 

 
  @Override
 /**
  * returns the cordance with the supplied word and size
  */
  public String getKWIC(String word, int contextSize) {
	  String value = "";
	  if(parsedCordance != null){
		currentSize = contextSize;
 
    	currentWord = word;
 
    	cordanceVeiwer = new CordanceVeiwer(parsedCordance);
 
    	value =  cordanceVeiwer.getCordance(currentWord, new Integer(currentSize));
	}else{
		value = "file not valid please fix.";
	}
	return value;
  }
 

 
  @Override
 /**
  * returns the cordance with the supplied word and then uses the current size and word to make a new one
  * 
  */
  public String getWiderContext(String kwicID) {
	String value = "";
	  if(parsedCordance != null){
    cordanceVeiwer = new CordanceVeiwer(parsedCordance);
 
    value =  cordanceVeiwer.getWiderContext(currentWord, currentSize);
	
	}else{
		value = "file not valid please fix.";
	}
	  return value;
	
  }
   
 
/**
 * sets up the program by instanciating the 2 objects tui and cordance 
 * @param args
 */
 public static void main(String[] args) {
	cordance = new Cordance();
	cordance.SetFileLocation("C:/Users/ryan/Documents/university/year 2/cordance veiwer/CordanceViewerCS2310/data/pandpEd12.txt");
	tui = new TUI(cordance);
 }

public static TUI getTui() {
	return tui;
}

public static Cordance getCordance() {
	return cordance;
}


public CordanceVeiwer getCordanceVeiwer() {
	return cordanceVeiwer;
}



public ParsedCordance getCordanceSpliter() {
	return parsedCordance;
}


public String getCurrentWord() {
	return currentWord;
}


public int getCurrentSize() {
	return currentSize;
}


 
 
 
 
 
}