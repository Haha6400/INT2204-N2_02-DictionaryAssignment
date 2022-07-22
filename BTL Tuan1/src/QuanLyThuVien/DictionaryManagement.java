package QuanLyThuVien;

import java.io.*;

public class DictionaryManagement {
    public static void insertFromFile(){
        try {
            Dictionary.dictionary.clear();
            Dictionary.hoTroTraCuu.clear();
            FileInputStream fis = new FileInputStream("dictionaries.txt");
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line=new String();

            do {
                line = br.readLine();
                if (line != null) {
                    String arr[] = line.split("\t\t");
                    Word word=new Word();
                    word.setEnglish(arr[0]);
                    word.setVietnamese(arr[1]);
                    word.setVietnamese(word.getVietnamese());
                    Dictionary.dictionary.add(word);
                    Dictionary.hoTroTraCuu.put(arr[0],arr[1]);
                }
            }
            while (line != null);
            br.close();
            isr.close();
            fis.close();

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

    }
    public static void exportToFle(){
        try{
            FileOutputStream fos=new FileOutputStream("dictionaries.txt");
            OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw=new BufferedWriter(osw);
            for (int i=0;i<Dictionary.dictionary.size();i++)
            {
                Word word=Dictionary.dictionary.get(i);
                bw.write(word.getEnglish() + "\t\t" + word.getVietnamese());
                bw.newLine();
            }
            bw.close();
            osw.close();
            fos.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void insertWord(Word word){
        word.setVietnamese(word.getVietnamese().toLowerCase());
        word.setEnglish(word.getEnglish().toLowerCase());
        Dictionary.dictionary.add(word);
    }
    public static void deleteWord(Word word){
        word.setEnglish(word.getEnglish().toLowerCase());
        word.setVietnamese(word.getVietnamese().toLowerCase());
        Dictionary.dictionary.remove(word);
    }
    public static String dictionaryLookUp(String english){
        english=english.toLowerCase();
        if (Dictionary.hoTroTraCuu.containsKey(english)==false)
            return null;
        else
            return Dictionary.hoTroTraCuu.get(english);
    }
}
