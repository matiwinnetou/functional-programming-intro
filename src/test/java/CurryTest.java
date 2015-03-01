import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class CurryTest {

    @Test
    public void curryAdderTest() {
        final BiFunction<Integer, Integer, Integer> adder = (a, b) -> a + b;
        final Integer normal = adder.apply(1, 2);

        assertEquals(Integer.valueOf(3), normal);

        final Function<Integer, Function<Integer, Integer>> curried = Curry.curry(adder);
        Function<Integer, Integer> fun = curried.apply(1);
        final Integer applied = fun.apply(2);

        assertEquals(Integer.valueOf(3), applied);
    }
    
}
