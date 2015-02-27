import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class MemoizerTest {

    @Test
    public void testMemoization() {
        Function<Integer, Integer> square = x -> x * x;
        Function<Integer, Integer> memoized = Memoizer.memoize(square);

        assertEquals(Integer.valueOf(100), square.apply(10));

        assertEquals(Integer.valueOf(100), memoized.apply(10));
        assertEquals(Integer.valueOf(100), memoized.apply(10));
    }

}
