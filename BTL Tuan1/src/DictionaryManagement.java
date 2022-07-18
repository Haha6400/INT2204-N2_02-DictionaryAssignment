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
    
    private void searchFilter(String searchTerm){
        DefaultListModel filteredItems = new DefaultListModel();
        words.stream().forEach((word) -> {
            String new_word = word.toString().toLowerCase();
            if (new_word.startsWith(searchTerm.toLowerCase())) {
                word.setWord_target(new_word);
                filteredItems.addElement(word.getWord_target()); 
                vnmeses.put(word.getWord_target(), word.getWord_explain());
            }
        });
        listModel = filteredItems;
        jList1.setModel(listModel);
    }
}
