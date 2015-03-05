import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    public void testGenerateSequenceOnes() {
        final List<Integer> ints = Stream.generate(() -> 1)
                .limit(10)
                .collect(Collectors.toList());

        Assert.assertEquals(Lists.newArrayList(1,1,1,1,1,1,1,1,1,1), ints);
    }

    @Test
    public void testGenerateSequenceInc() {
        final List<Integer> ints = Stream.iterate(0, i -> i + 1)
                .limit(10)
                .collect(Collectors.toList());

        Assert.assertEquals(Lists.newArrayList(0,1,2,3,4,5,6,7,8,9), ints);
    }

    @Test
    public void testConcat() {
        final Stream<Integer> r1 = Stream.of(1, 2, 3);
        final Stream<Integer> r2 = Stream.of(3, 4, 5);

        final List<Integer> collect = Stream.concat(r1, r2)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        Assert.assertEquals(ImmutableList.of(5, 4, 3, 2, 1), collect);
    }

    @Test
    public void testConcatAndDistinctAndReverseComparator() {
        final Stream<Integer> r1 = Stream.of(1, 2, 3);
        final Stream<Integer> r2 = Stream.of(3, 4, 5);

        final List<Integer> collect = Stream.concat(r1, r2)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        Assert.assertEquals(ImmutableList.of(5, 4, 3, 2, 1), collect);
    }

    @Test
    public void testFibonacciInfinite_WithPeek() {
        final IntStream intStream = FibonacciIterator.infiniteStream();

        final long sum = intStream
                .peek(i -> System.out.println(i))
                .limit(10)
                .sum();

        Assert.assertEquals(231, sum);
    }

    @Test
    public void testListForEachWithMethodReference() {
        final Optional<String> strOpt = Lists.newArrayList("a", "b", "c", " cc ", "d").stream()
                .filter(str -> str.length() > 1)
                .findAny();

        final Optional<Integer> maybeInt = strOpt
                .map(String::trim)
                .map(str -> str.length());

        Assert.assertEquals("should be 2", 2, maybeInt.get().intValue());
    }

    @Test
    public void testReduceWithProduct() {
        final long result = Stream.of(1, 2, 3)
                .filter(i -> i >= 2)
                .reduce(1, (prev, next) -> prev * next);

        Assert.assertEquals("result should be 6", 6, result);
    }

    @Test
    public void testFilter() {
        final List<Integer> ints = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());

        Assert.assertEquals(ImmutableList.of(2, 4, 6, 8), ints);
    }

    @Test
    //fold
    public void testReduceGenerateRandomIds_With_Max_Reduce() {
        Stream.generate(() -> UUID.randomUUID().toString())
                .limit(10)
                .map(str -> str.split("-"))
                .map(strs -> strs[0])
                .map(str -> Long.parseLong(str, 16))
                .max(Comparator.comparingLong(i -> i))
                .ifPresent(max -> System.out.println("max:" + max));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testOwnCollector() {
        final ImmutableList<String> collect = Stream
                .generate(() -> UUID.randomUUID().toString())
                .limit(100)
                .collect(new ImmutableListCollector<>());

        collect.add("1");
    }

}
