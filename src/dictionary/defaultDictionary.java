package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
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

  /**Số bước ít nhất để biến chuỗi S thành chuỗi T thông qua xóa, thêm, thay.
   */
  public int LevenshteinDistance(String S, String T) {
    int lenS = S.length();
    int lenT = T.length();
    S = " " + S;
    T = " " + T;
    int cost;
    int[][] lev = new int[lenS + 1][lenT + 1];
    lev[0][0] = 0;
    for(int i = 1; i <= lenS; i++) {
      lev[i][0] = i;
    }
    for(int i = 1; i <= lenT; i++) {
      lev[0][i] = i;
    }
    for(int i = 1; i <= lenS; i++) {
      for(int j = 1; j <= lenT; j++) {
        if(S.charAt(i) == T.charAt(j)) {
          cost = 0;
        } else {
          cost = 1;
        }
        lev[i][j] = Math.min(lev[i - 1][j] + 1, Math.min(lev[i][j - 1] + 1, lev[i - 1][j - 1] + cost));
      }
    }
    return lev[lenS][lenT];
  }

  public String fuzzySearch(String key) {
    key = dictionary.lowerCase(key);
    Set<String> keySet = dictionary.wordsList.keySet();
    String res = "";
    int levDisMin = Integer.MAX_VALUE;
    for(String tm : keySet) {
      int valueOfLev = LevenshteinDistance(key, tm);
      if(valueOfLev < levDisMin) {
        levDisMin = valueOfLev;
        res = tm;
      }
    }
    if (levDisMin <= 3) {
      return res;
    } else {
      return null;
    }
  }

  public static void main(String[] args) {
    defaultDictionary defaultDic = new defaultDictionary();
    dictionary.addANewWord("Hi", "test");
    dictionary.addANewWord("Hello", "hi");
    defaultDic.showAllWords();
    System.out.println(defaultDic.adjacentWord("Hiii"));
    
  }




}
