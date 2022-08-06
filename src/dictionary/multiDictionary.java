package dictionary;

import com.knziha.plod.dictionary.mdict;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
//import jdk.internal.icu.text.UnicodeSet;

public class multiDictionary {

  public static final String CHARSET_NAME = "UTF-8";
  public static final String NOT_EXIST = "<h2>This dictionary don't have this word! </h2>";
  private final ArrayList<mdict> mdxs = new ArrayList<mdict>();

  /**
   * constructor. filePath includes file paths of mdict.
   */
  public multiDictionary(String filePath) throws IOException {
    try {
      File file = new File(filePath);
      Scanner scan = new Scanner(file, CHARSET_NAME);
      while (scan.hasNextLine()) {
        String str = scan.nextLine();
        mdxs.add(new mdict(str));
      }
      scan.close();
    } catch (FileNotFoundException e) {
      System.out.println(e + "\n");
    }
  }

  /**
   * Look up a word in mdxs. key: input word.
   */
  public ArrayList<String> lookUp(String key) throws IOException {
    ArrayList<String> outputContent = new ArrayList<>();
    for (int i = 0; i < mdxs.size(); i++) {
      int searchResult = mdxs.get(i).lookUp(key, true);
      if (searchResult > 0) {
        outputContent.add(mdxs.get(i).getRecordAt(searchResult));
      } else {
        outputContent.add(NOT_EXIST);
      }
    }
    return outputContent;
  }

  /**
   * search.
   *
   * @param key input word
   * @param lm  limit word after input word
   * @return List words
   */
  private ArrayList<String> Search(String key, int lm) {
    ArrayList<String> entryNames = new ArrayList<>();
    int searchResult = mdxs.get(0).lookUp(key, false);
    for (int i = 0; i < lm; i++) {
      String temp = mdxs.get(0).getEntryAt(searchResult + i);
      if (temp.toLowerCase(Locale.ROOT).contains(key)) {
        entryNames.add(temp);
      } else {
        break;
      }
    }
    return entryNames;
  }

  public ArrayList<String> Search(String key) {
    return Search(key, 50);
  }

  /**
   * adjacent word method.
   *
   * @param key      input word
   * @param number>0 number of word after input word <0 number of word befor input word
   * @return List number words after input word.
   */
  public ArrayList<String> adjacentWord(String key, int number) throws IOException {
    ArrayList<String> entryNames = new ArrayList<>();
    int searchResult = mdxs.get(0).lookUp(key, false);
    for (int i = 0; i < number; i++) {
      if (number > 0) {
        entryNames.add(mdxs.get(0).getEntryAt(searchResult + i));
      } else {
        entryNames.add(mdxs.get(0).getEntryAt(searchResult - i));
      }
    }
    return entryNames;
  }

  public ArrayList<String> nameOfDic() {
    ArrayList<String> dictionaryName = new ArrayList<>();
    for (int i = 0; i < mdxs.size(); i++) {
      dictionaryName.add(mdxs.get(i)._Dictionary_Name);
    }
    return dictionaryName;
  }

}
