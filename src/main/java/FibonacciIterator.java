import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class FibonacciIterator implements PrimitiveIterator.OfInt {

    private int beforePrevious = 0;
    private int previous = 1;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public int nextInt() {
        int result = beforePrevious + previous;
        beforePrevious = previous;
        previous = result;

        return result;
    }

    public static IntStream infiniteStream() {
        final FibonacciIterator generator = new FibonacciIterator();
        final Spliterator.OfInt spliterator = Spliterators.spliteratorUnknownSize(generator, Spliterator.ORDERED);

        return StreamSupport.intStream(spliterator, false);
    }

}