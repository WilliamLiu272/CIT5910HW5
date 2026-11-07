import java.util.*;
import java.io.*;

public class WordRecommender {
    private HashSet<String> dict = new HashSet<>();

    //engDictionary.txt
    public WordRecommender(String dictionaryFile) {
      // TODO: change this!
        // adding all the words in the file to a hashset
        try (Scanner scanner = new Scanner(new FileInputStream(dictionaryFile))){
            while (scanner.hasNext()){
                this.dict.add(scanner.next());
            }
        } catch (IOException e) {
            System.out.println("Dictionary File Doesn't Exist");
        }
    }
  
    public double getSimilarity(String word1, String word2) {
      // TODO: change this!


      return 0.0;
    }
  
    public ArrayList<String> getWordSuggestions(String word, int tolerance, double commonPercent, int topN) {
      // TODO: change this!
      return null;
    }
  
    // You can of course write other methods as well.

    private double commonPercent(String user_str, String dict_str){
        // store both string into separate hashsets for commonPercent calculation
        HashSet<Character> str1 = new HashSet<>();
        HashSet<Character> str2 = new HashSet<>();
        for (char i : user_str.toCharArray()){
            str1.add(i);
        }
        for (char i : dict_str.toCharArray()){
            str2.add(i);
        }

        HashSet<Character> unionSet = new HashSet<>(str1);
        unionSet.addAll(str2);
        int union = unionSet.size();

        HashSet<Character> intersectionSet = new HashSet<>(str1);
        intersectionSet.retainAll(str2);
        int intersection = intersectionSet.size();

        return (double)intersection/union;
    }
  }