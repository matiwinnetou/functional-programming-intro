import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class MoreOptionals {

    public static <T1, T2, T3, R> Optional<R> zip(final Optional<? extends T1> a,
                                                  final Optional<? extends T2> b,
                                                  final Optional<? extends T3> c,
                                                  final TriFunction<? super T1, ? super T2, ? super T3, ? extends R> zipFunction) {
        return a.flatMap(aa -> b.flatMap(bb -> c.map(cc -> zipFunction.apply(aa, bb, cc))));
    }

    public static <T> Optional<List<T>> sequence(final Optional<T>... array) {
        return sequence(Arrays.asList(array));
    }

    public static <T> Optional<List<T>> sequence(final List<Optional<T>> inputList) {
        final List<T> collect = inputList.stream()
                .filter(opt -> opt.isPresent())
                .map(opt -> opt.get())
                .collect(Collectors.toList());

        return Optional.of(collect).filter(list -> list.size() > 0);
    }

    public static <T1, T2, R> Optional<R> zip(final Optional<? extends T1> a,
                                              final Optional<? extends T2> b,
                                              final BiFunction<? super T1, ? super T2, ? extends R> zipFunction) {
        return a.flatMap(aa -> b.map(bb -> zipFunction.apply(aa, bb)));
    }


}
