import java.util.ArrayList;

public class PriorityArray {
    private ArrayList<String> words = new ArrayList<>();
    private ArrayList<Double> wordSimilarity = new ArrayList<>();
    private ArrayList<String> sortedWords = new ArrayList<>();
    private int size;

    public PriorityArray(int size) {
        this.size = size;
    }

    public void add(String word, double sim){
        words.add(word);
        wordSimilarity.add(sim);
    }

    public ArrayList<String> getTopN(){

        // sort and get the top words from the list
        while (sortedWords.size() < size){
            double max = 0;
            int maxIndex = -1;

            //get the index of the most similar word from the list
            for (int i = 0; i < wordSimilarity.size(); i++){
                if (wordSimilarity.get(i)  > max){
                    max = wordSimilarity.get(i);
                    maxIndex = i;
                }
            }

            // add the most similar word into sortedWords list
            sortedWords.add(words.get(maxIndex));

            //remove the most similar word from the list, so next iteration gets the next most similar word
            wordSimilarity.remove(maxIndex);
            words.remove(maxIndex);
        }

        return sortedWords;
    }
}
