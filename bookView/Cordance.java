package bookView;
 

 
import cordance.CordanceSpliter;
 
import cordance.CordanceVeiwer;
 

 
public class Cordance implements Controller{
 

 
  private CordanceVeiwer cordanceVeiwer;
 

 
  private CordanceSpliter cordanceSpliter;
 

 
  private String currentWord;
 
  
 
  private int currentSize;
 
  
 
  Cordance(String filelocation){
 
    currentSize = 10;
 
    currentWord = "";
 
    cordanceSpliter = new CordanceSpliter(filelocation);
 
  }
 
  
 
  @Override
 
  public String getKWIC(String word) {
 
    currentWord = word;
 
    currentSize = 10;
 
    cordanceVeiwer = new CordanceVeiwer(cordanceSpliter.getWordIndex(),cordanceSpliter.getWordCatalogue(),cordanceSpliter.getPositionName());
 
    return cordanceVeiwer.getCordance(currentWord, new Integer(10));
 
  }
 

 
  @Override
 
  public String getKWIC(String word, int contextSize) {
 
    currentSize = contextSize;
 
    currentWord = word;
 
    cordanceVeiwer = new CordanceVeiwer(cordanceSpliter.getWordIndex(),cordanceSpliter.getWordCatalogue(),cordanceSpliter.getPositionName());
 
    return cordanceVeiwer.getCordance(currentWord, new Integer(currentSize));
 
  }
 

 
  @Override
 
  public String getWiderContext(String kwicID) {
 
    cordanceVeiwer = new CordanceVeiwer(cordanceSpliter.getWordIndex(),cordanceSpliter.getWordCatalogue(),cordanceSpliter.getPositionName());
 
    
 
    return cordanceVeiwer.getWiderContext(currentWord, currentSize);
 
  }
 
  
 
  
 

 
}