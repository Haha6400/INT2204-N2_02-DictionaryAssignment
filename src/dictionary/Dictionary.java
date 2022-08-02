package dictionary;

import java.util.TreeMap;

public class Dictionary {

  TreeMap<String, String> wordsList = new TreeMap<String, String>();

  public TreeMap<String, String> getWords() {
    return wordsList;
  }

  public String lowerCase(String word) {
    return word.toLowerCase();
  }

  public boolean addANewWord(String word, String explain) {
    word = lowerCase(word);
    explain = lowerCase(explain);
    if (wordsList.get(word) != null) {
      wordsList.put(word, explain);
      return true;
    } else {
      return false;
    }
  }

  public void removeAWord(String removeWord) {
    wordsList.remove(removeWord);
  }

  public void changeAWord(String key, String mean) {
    key = lowerCase(key);
    mean = lowerCase(mean);
    wordsList.replace(wordsList.get(key), mean);
  }

  public int numberOfWords() {
    return wordsList.size();
  }
}
