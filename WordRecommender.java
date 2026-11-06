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
  }