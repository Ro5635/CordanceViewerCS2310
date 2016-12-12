package bookView;

import CordanceParser.ParsedCordance;
 
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
   * constructor that initialises variables and makes a new cordanceveiwer
   * with the file location supplied
   * the default cordance size is 10 so its default is 10
   * @param filelocation
   */
  Cordance( ParsedCordance pc){
 
    currentSize = defaultsize;
 
    currentWord = "";
    
    cordanceVeiwer = new CordanceVeiwer(pc);
    
    parsedCordance = pc;
    
  }
  
  /**
   * reset folder location if needed
   * 
   * @param fileLocation
   */
  public void SetFileLocation( String[] folderLocation){
	  parsedCordance = new ParsedCordance(folderLocation);
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
 
    	
    	value =  cordanceVeiwer.getCordance(currentWord, new Integer(currentSize));
	}else{
		value = "file not valid please fix.";
	}
	return value;
  }
 

 
  @Override
 /**
  * returns the cordance with the supplied word and then uses the current size and word to make a new one
  * @param kwicID
  */
  public String getWiderContext(String kwicID) {
	String value = "";
	  if(parsedCordance != null){
		  System.out.println(kwicID);
    value =  cordanceVeiwer.getWiderContext( currentSize , kwicID);
	
	}else{
		value = "file not valid please fix.";
	}
	  return value;
	
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



public ParsedCordance getParsedCordance() {
	return parsedCordance;
}


public String getCurrentWord() {
	return currentWord;
}


public int getCurrentSize() {
	return currentSize;
}


 
 
 
 
 
}