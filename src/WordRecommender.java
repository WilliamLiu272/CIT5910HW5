import java.util.Scanner;

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
        // store both string into seperate harsets for commonPercent calculation
        Hashset<String> str1 = new Hashset<>();
        Hashset<String> str2 = new Hashset<>();
        for (char i : user_str){
            str1.add(i);
        }
        for (char i : dict_str){
            str2.add(i);
        }

        Set<Integer> unionSet = new HashSet<>(str1);
        unionSet.addAll(str2);
        union = unionSet.size();

        Set<Integer> intersectionSet = new HashSet<>(str1);
        intersectionSet.retainAll(str2);
        intersection = intersectionSet.size();

        return intersection/union;
    }
  }