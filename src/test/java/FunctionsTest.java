import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionsTest {

    @Test
    //addOne function
    public void testApply() {
        final Function<Integer, Integer> addOne = i -> i + 1;

        Assert.assertEquals(Integer.valueOf(2), addOne.apply(1));
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
    //square(addOne)
    public void testSquare() {
        final Function<Integer, Integer> addOne = i -> i + 1;
        final Function<Integer, Integer> square = i -> i * i;

        final Function<Integer, Integer> composed = square.compose(addOne);

        final List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5)
                .stream()
                .map(composed)
                .collect(Collectors.toList());

        Assert.assertEquals(Lists.newArrayList(4, 9, 16, 25, 36), ints);
    }

}
