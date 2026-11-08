import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.stream.Stream;

public class WordRecommenderTest {

    // WordRecommender.getWordSuggestions
    static Stream<Arguments> suggestionCases() {
        return Stream.of(
                Arguments.of("car",    2, 0.5, 5, List.of("bar", "cart", "rat", "ca")),
                Arguments.of("car",    2, 0.5, 2, List.of("bar", "cart")),
                Arguments.of("zzzzz",  2, 0.5, 4, List.of())
        );
    }
    @ParameterizedTest
    @MethodSource("suggestionCases")
    public void testGetWordSuggestions(String word, int tolerance, double percent,
                                       int topN, List<String> expected) {
        WordRecommender rec = new WordRecommender("testDictionary.txt");
        assertEquals(expected, rec.getWordSuggestions(word, tolerance, percent, topN));
    }

    //WordRecommender.getSimilarity
    @ParameterizedTest
    @CsvSource({
            "oblige, oblivion, 2.5",
            "aghast, gross,    1.5",
            "table,  comfortable, 2.5",
            "cat,    cat,      3.0",
            "abc,    xyz,      0.0",
            "a,      a,        1.0"
    })
    public void testGetSimilarity(String w1, String w2, double expected){
        WordRecommender rec = new WordRecommender("testDictionary.txt");
        assertEquals(expected, rec.getSimilarity(w1, w2));
    }

    // WordRecommender.commonPercent
    @ParameterizedTest
    @CsvSource({
            "car, cart, 0.75",
            "abc, xyz, 0.0",
            "car, arc, 1.0",
            "oblige, oblivion, 0.5"
    })
    public void testCommonPercent(String w1, String w2, double expected){
        WordRecommender rec = new WordRecommender("testDictionary.txt");
        assertEquals(expected, rec.commonPercent(w1, w2));
    }

    // PriorityArray.add
    @Test
    public void testAddSingleWord() {
        PriorityArray pa = new PriorityArray(4);
        pa.add("cart", 2.5);
        assertEquals(List.of("cart"), pa.getTopN());
    }

    // PriorityArray.getTopN
    @Test
    public void testAddMultipleWords() {
        PriorityArray pa = new PriorityArray(4);
        pa.add("low", 1.0);
        pa.add("high", 3.0);
        pa.add("mid", 2.0);
        assertEquals(List.of("high", "mid", "low"), pa.getTopN());
    }


}
