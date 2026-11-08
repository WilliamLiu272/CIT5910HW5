import java.util.*;
import java.io.*;

public class WordRecommender {
    private final HashSet<String> dict = new HashSet<>();

    public WordRecommender(String dictionaryFile) {
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
        int length = Math.min(word1.length(), word2.length());
        int leftSim = 0;
        int rightSim = 0;

        // left similarity
        for (int i = 0; i < length; i++){
            if (word1.charAt(i) == word2.charAt(i)){
                leftSim++;
            }
        }

        //right similarity
        for (int i = 1; i <= length; i++){
            if (word1.charAt(word1.length()-i) == word2.charAt(word2.length()-i)){
                rightSim++;
            }
        }

      return ((leftSim+rightSim)/2.0);
    }

    public ArrayList<String> getWordSuggestions(String word, int tolerance, double commonPercent, int topN) {
        PriorityArray priorityArray = new PriorityArray(topN);
        // tolerance range
        int min = word.length() - tolerance;
        int max = word.length() + tolerance;

        // store all candidates in a priorityArray
        for  (String s : dict) {
            // filters
            if ((min <= s.length()) && (s.length() <= max) && (commonPercent(word, s) >= commonPercent)) {
                priorityArray.add(s, getSimilarity(word, s));
            }
        }

      return priorityArray.getTopN();
    }
  
    // You can of course write other methods as well.

    static double commonPercent(String userStr, String dictStr){
        // store both string into separate hashsets for commonPercent calculation
        HashSet<Character> str1 = new HashSet<>();
        HashSet<Character> str2 = new HashSet<>();
        for (char c : userStr.toCharArray()){
            str1.add(c);
        }
        for (char c : dictStr.toCharArray()){
            str2.add(c);
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