package dictionary;

import java.io.IOException;
import java.util.ArrayList;

public class DictionaryApplication {

  private final multiDictionary multiDic = new multiDictionary(
      "C:\\OOP\\INT2204_DictionaryAssignment\\src\\mdxs.txt");
  private final defaultDictionary defaultDic = new defaultDictionary();

  public DictionaryApplication() throws IOException {
    defaultDic.insertFromFile();
  }

  public static void main(String[] args) throws IOException {
    System.out.println("START:\n");
    System.out.println("TEST:\n");
    System.out.println("LOOK UP TEST:\n");
    multiDictionary mdict = new multiDictionary(
        "C:\\OOP\\INT2204_DictionaryAssignment\\src\\mdxs.txt");
    String key = "hello";
    ArrayList<String> wordExplains = mdict.lookUp(key);
    for (int i = 0; i < wordExplains.size(); i++) {
      System.out.println(wordExplains.get(i));
    }

    System.out.println("SEARCHER TEST:\n");
    ArrayList<String> wordTargets = mdict.Search(key);
    for (int i = 0; i < wordTargets.size(); i++) {
      System.out.println(wordTargets.get(i));
    }

    System.out.println("ADJACENT TEST:\n");
    ArrayList<String> words = mdict.adjacentWord(key, 15);
    for (int i = 0; i < words.size(); i++) {
      System.out.println(words.get(i));
    }
    System.out.println("END.\n");
  }

  public multiDictionary getMultiDic() {
    return multiDic;
  }

  public defaultDictionary getDefaultDic() {
    return defaultDic;
  }
}
