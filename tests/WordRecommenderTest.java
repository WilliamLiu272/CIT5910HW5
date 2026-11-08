import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class WordRecommenderTest {

    @Test
    public void testGetWordSuggestions(){
        WordRecommender rec = new WordRecommender("testDictionary.txt");
        ArrayList<String> words = new ArrayList<>();

        words = rec.getWordSuggestions("car", 2, 0, 4);

        System.out.println(words);
    }

    @Test
    public void testGetSimilarity(){
        WordRecommender rec = new WordRecommender("testDictionary.txt");
        rec.getSimilarity("table", "comfortable");
    }

}
