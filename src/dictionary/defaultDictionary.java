package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class defaultDictionary {

  private static final String CHARSET_NAME = "UTF-8";
  private static final Dictionary dictionary = new Dictionary();

  public static Dictionary getDictionary() {
    return dictionary;
  }

  public void insertFromFile() {
    try {
      File file = new File("./src/dictionaries.txt");
      Scanner scan = new Scanner(file, CHARSET_NAME);
      while (scan.hasNextLine()) {
        String str = scan.nextLine();
        String[] spl = str.split(": ");
        dictionary.getWords().put(spl[0], spl[1]);
      }
      scan.close();
    } catch (FileNotFoundException e) {
      System.out.println("e: " + e);
    }
  }

  public ArrayList<String> showAllWords() {
    ArrayList<String> result = new ArrayList<String>();
    Set<String> keySet = dictionary.wordsList.keySet();
    for(String key : keySet) {
      result.add(key + ": " + dictionary.wordsList.get(key));
    }
    return result;
  }

  public String lookUp(String key) {
    key = dictionary.lowerCase(key);
    return dictionary.wordsList.get(key);
  }

  public ArrayList<String> adjacentWord(String key) {
    key = dictionary.lowerCase(key);
    ArrayList<String> list = new ArrayList<String>();
    list.add(key);
    int c = 9;
    String tm = key;
    while(c != 0) {
      try {
        tm = dictionary.wordsList.higherKey(tm);
        //higherKey() return least key greater than key
      } catch (NullPointerException e) {
        break;
      }
      if(tm != null) {
        list.add(tm);
      }
      c--;
    }
    return list;
  }


}
