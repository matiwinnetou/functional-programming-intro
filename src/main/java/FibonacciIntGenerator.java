import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class FibonacciIntGenerator implements PrimitiveIterator.OfInt {

    private int beforePrevious = 0;
    private int previous = 1;
    private final IntPredicate predicate;

    protected FibonacciIntGenerator(final IntPredicate predicate) {
        this.predicate = predicate;
    }

    protected FibonacciIntGenerator() {
        this(i -> true);
    }

    @Override
    public boolean hasNext() {
        return predicate.test(previous);
    }

    @Override
    public int nextInt() {
        int result = beforePrevious + previous;
        beforePrevious = previous;
        previous = result;

        return result;
    }

    public static IntStream finiteStream(final IntPredicate predicate) {
        final FibonacciIntGenerator generator = new FibonacciIntGenerator(predicate);
        final Spliterator.OfInt spliterator = Spliterators.spliteratorUnknownSize(generator, Spliterator.ORDERED);

        return StreamSupport.intStream(spliterator, false);
    }

    public static IntStream infiniteStream() {
        final FibonacciIntGenerator generator = new FibonacciIntGenerator();
        final Spliterator.OfInt spliterator = Spliterators.spliteratorUnknownSize(generator, Spliterator.ORDERED);

        return StreamSupport.intStream(spliterator, false);
    }

}