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
                // normal case: sorted from most to least similar
                Arguments.of("car", 2, 0.5, 4, List.of("bar", "cart", "races", "arc")),
                // topN truncates the result
                Arguments.of("car", 2, 0.5, 2, List.of("bar", "cart")),
                // fewer candidates than topN: return all of them
                Arguments.of("car", 2, 0.5, 10, List.of("bar", "cart", "races", "arc")),
                // tolerance 0: only words of the same length
                Arguments.of("car", 0, 0.5, 4, List.of("bar", "arc")),
                // commonPercent 1.0: only words with exactly the same letters
                Arguments.of("car", 2, 1.0, 4, List.of("arc")),
                // no candidates at all
                Arguments.of("zzzzz", 2, 0.5, 4, List.of()),
                // topN of 0
                Arguments.of("car", 2, 0.5, 0, List.of())
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
            "oblige,    oblivion,    2.5",
            "aghast,    gross,       1.5",
            "table,     comfortable, 2.5",
            "committee, comet,       2.0",
            "cat,       cat,         3.0",
            "abc,       xyz,         0.0",
            "a,         a,           1.0",
            "car,       arc,         0.0"
    })
    public void testGetSimilarity(String w1, String w2, double expected){
        WordRecommender rec = new WordRecommender("testDictionary.txt");
        assertEquals(expected, rec.getSimilarity(w1, w2));
    }

    // WordRecommender.commonPercent
    @ParameterizedTest
    @CsvSource({
            "committee, comet,    0.8333333333",
            "gardener,  nerdier,  0.5714285714",
            "car,       cart,     0.75",
            "car,       arc,      1.0",
            "oblige,    oblivion, 0.5",
            "abc,       xyz,      0.0"
    })
    public void testCommonPercent(String w1, String w2, double expected){
        WordRecommender rec = new WordRecommender("testDictionary.txt");
        assertEquals(expected, WordRecommender.commonPercent(w1, w2), 1e-6);
    }

}
