import java.io.*;
public class DictionaryManagement {
    public static void insertWord(Word word) {
        word.setVietnamese(word.getVietnamese().toLowerCase());
        word.setEnglish(word.getEnglish().toLowerCase());
        Dictionary.dictionary.add(word);
    }

    public static void deleteWord(Word word) {
        word.setEnglish(word.getEnglish().toLowerCase());
        word.setVietnamese(word.getVietnamese().toLowerCase());
        Dictionary.dictionary.remove(word);
    }
}