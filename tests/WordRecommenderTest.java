import org.junit.jupiter.api.Test;

public class WordRecommenderTest {

    @Test
    public void testGetWordSuggestions(){
        WordRecommender rec = new WordRecommender("testDictionary.txt");
        rec.getWordSuggestions("car", 2, 0, 10);
    }

}
