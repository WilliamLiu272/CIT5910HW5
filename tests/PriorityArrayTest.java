import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PriorityArrayTest {

    @Test
    public void testEmptyReturnsEmptyList() {
        PriorityArray pa = new PriorityArray(4);
        assertEquals(List.of(), pa.getTopN());
    }

    @Test
    public void testAddSingleWord() {
        PriorityArray pa = new PriorityArray(4);
        pa.add("cart", 2.5);
        assertEquals(List.of("cart"), pa.getTopN());
    }

    @Test
    public void testOrdersFromHighestToLowest() {
        // scores deliberately added out of order, so a misaligned word/score pair would show up
        PriorityArray pa = new PriorityArray(4);
        pa.add("low", 1.0);
        pa.add("high", 3.0);
        pa.add("mid", 2.0);
        assertEquals(List.of("high", "mid", "low"), pa.getTopN());
    }

    @Test
    public void testTruncatesToTopN() {
        PriorityArray pa = new PriorityArray(2);
        pa.add("a", 1.0);
        pa.add("b", 4.0);
        pa.add("c", 3.0);
        pa.add("d", 2.0);
        assertEquals(List.of("b", "c"), pa.getTopN());
    }

    @Test
    public void testFewerWordsThanTopN() {
        PriorityArray pa = new PriorityArray(10);
        pa.add("a", 1.0);
        pa.add("b", 2.0);
        assertEquals(List.of("b", "a"), pa.getTopN());
    }

    @Test
    public void testTopNZero() {
        PriorityArray pa = new PriorityArray(0);
        pa.add("a", 1.0);
        assertEquals(List.of(), pa.getTopN());
    }

    @Test
    public void testZeroScoreIsStillSelected() {
        PriorityArray pa = new PriorityArray(4);
        pa.add("zero", 0.0);
        pa.add("one", 1.0);
        assertEquals(List.of("one", "zero"), pa.getTopN());
    }

    @Test
    public void testDuplicateWordsBothReturned() {
        PriorityArray pa = new PriorityArray(4);
        pa.add("cart", 2.0);
        pa.add("cart", 1.5);
        assertEquals(List.of("cart", "cart"), pa.getTopN());
    }

    @Test
    public void testGetTopNDoesNotChangeState() {
        PriorityArray pa = new PriorityArray(2);
        pa.add("a", 1.0);
        pa.add("b", 3.0);
        pa.add("c", 2.0);
        List<String> first = pa.getTopN();
        List<String> second = pa.getTopN();
        assertEquals(List.of("b", "c"), first);
        assertEquals(first, second);
    }

    @Test
    public void testAddAfterGetTopN() {
        PriorityArray pa = new PriorityArray(2);
        pa.add("a", 1.0);
        pa.getTopN();
        pa.add("b", 5.0);
        assertEquals(List.of("b", "a"), pa.getTopN());
    }
}
