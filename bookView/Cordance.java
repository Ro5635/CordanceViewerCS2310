package bookView;

import CordanceParser.ParsedCordance;
 
import cordance.CordanceVeiwer;
 

 
public class Cordance implements Controller{
 
	
	//constant size
	private static final int defaultsize  = 10;
	
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
   * @param word
   * @param contextSize
   */
  public String getKWIC(String word, int contextSize) {
	  String value = "";
	  if(parsedCordance != null){//while this has a parsed cordance it will ask it for a cordance then return it
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
}