import java.util.ArrayList;
import java.util.HashSet;

public class PriorityArray {
    private final ArrayList<String> words = new ArrayList<>();
    private final ArrayList<Double> wordSimilarity = new ArrayList<>();
    private final int topN;

    public PriorityArray(int topN) {
        this.topN = topN;
    }

    // using a pair of parallel lists for words and their similarity values
    public void add(String word, double sim){
        words.add(word);
        wordSimilarity.add(sim);
    }

    public ArrayList<String> getTopN(){
        ArrayList<String> result = new ArrayList<>();
        // hashset stores the INDEX of the words added to the result list
        HashSet<Integer> selected = new HashSet<>();

        // sort and get the top words from the list
        while (result.size() < topN && result.size() < words.size()) {
            int maxIndex = -1;

            //get the index of the most similar word from the list
            for (int i = 0; i < wordSimilarity.size(); i++){
                // check if the INDEX of the word is already taken
                if (selected.contains(i)) continue;

                if (maxIndex == -1 || wordSimilarity.get(i) > wordSimilarity.get(maxIndex)) {
                    maxIndex = i;
                }
            }

            //update the result list and the hashset to keep track of the words taken
            selected.add(maxIndex);
            result.add(words.get(maxIndex));
        }

        return result;
    }
}
