import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionsTest {

    @Test
    public void testSquareFunction() {
        final Function<Integer, Integer> squareFun = i -> i * i;

        Assert.assertEquals(Integer.valueOf(4), squareFun.apply(2));
    }

    @Test
    public void testAddOneFunction() {
        final Function<Integer, Integer> addOne = i -> i + 1;

        Assert.assertEquals(Integer.valueOf(3), addOne.apply(2));
    }

    @Test
    //square(addOne)
    public void testAndThenOnesAndSquare() {
       final Function<Integer, Integer> addOne = i -> i + 1;
       final Function<Integer, Integer> square = i -> i * i;

       final Function<Integer, Integer> composed = addOne.andThen(square);

       final List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5)
                .stream()
                .map(composed)
                .collect(Collectors.toList());

        Assert.assertEquals(Lists.newArrayList(4, 9, 16, 25, 36), ints);
    }

    @Test
    public void testSquare() {
        final Function<Integer, Integer> addOne = i -> i + 1;
        final Function<Integer, Integer> square = i -> i * i;

        final Function<Integer, Integer> composed = square.compose(addOne);

        final List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5).stream()
                .map(composed)
                .collect(Collectors.toList());

        Assert.assertEquals(Lists.newArrayList(4, 9, 16, 25, 36), ints);
    }

}
