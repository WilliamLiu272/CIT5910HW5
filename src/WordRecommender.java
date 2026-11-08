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

        System.out.println("left: " + leftSim);
        System.out.println("right: " + rightSim);
        System.out.println("Similarity: " + (leftSim+rightSim)/2.0);

      return ((leftSim+rightSim)/2.0);
    }

    public ArrayList<String> getWordSuggestions(String word, int tolerance, double commonPercent, int topN) {
        PriorityArray priorityArray = new PriorityArray(topN);
        // tolerance range
        int min = word.length() - tolerance;
        int max = word.length() + tolerance;

        // store all candidates in a arrayList
        if (dict != null) {
            for  (String s : dict) {
                double cp = commonPercent(word, s);
                // filters
                if ((cp >= commonPercent) && (min <= s.length()) && (s.length() <= max)) {
                    double sim = this.getSimilarity(word, s);
                    priorityArray.add(s, sim);
                }
            }
        } else {
            throw new NullPointerException("Dictionary File is Null");
        }

      return priorityArray.getTopN();
    }
  
    // You can of course write other methods as well.

    static double commonPercent(String user_str, String dict_str){
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