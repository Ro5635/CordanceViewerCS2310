package bookView;
 

 
import cordance.CordanceSpliter;
 
import cordance.CordanceVeiwer;
 

 
public class Cordance implements Controller{
 

  //the object that models the cordanceveiwer owned by this object
  private CordanceVeiwer cordanceVeiwer;
 

  //the object that models the cordancesplitter owned by this object
  private CordanceSpliter cordanceSpliter;
 

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
  Cordance(String filelocation){
 
    currentSize = 10;
 
    currentWord = "";
 
    cordanceSpliter = new CordanceSpliter(filelocation);
 
  }
 
  
 
  @Override
  /**
   * returns the cordance with the supplied word
   * @param word
   * 
   */
  public String getKWIC(String word) {
 
    currentWord = word;
 
    currentSize = 10;
 
    cordanceVeiwer = new CordanceVeiwer(cordanceSpliter.getWordIndex(),cordanceSpliter.getWordCatalogue(),cordanceSpliter.getPositionName());
 
    return cordanceVeiwer.getCordance(currentWord, new Integer(10));
 
  }
 

 
  @Override
 /**
  * returns the cordance with the supplied word and size
  */
  public String getKWIC(String word, int contextSize) {
 
    currentSize = contextSize;
 
    currentWord = word;
 
    cordanceVeiwer = new CordanceVeiwer(cordanceSpliter.getWordIndex(),cordanceSpliter.getWordCatalogue(),cordanceSpliter.getPositionName());
 
    return cordanceVeiwer.getCordance(currentWord, new Integer(currentSize));
 
  }
 

 
  @Override
 /**
  * returns the cordance with the supplied word and then uses the current size and word to make a new one
  * 
  */
  public String getWiderContext(String kwicID) {
 
    cordanceVeiwer = new CordanceVeiwer(cordanceSpliter.getWordIndex(),cordanceSpliter.getWordCatalogue(),cordanceSpliter.getPositionName());
 
    
 
    return cordanceVeiwer.getWiderContext(currentWord, currentSize);
 
  }
 
  
 
  
 

 
}