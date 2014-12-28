
import com.google.common.collect.ImmutableList;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ImmutableListCollector<T> implements Collector<T, ImmutableList.Builder<T>, ImmutableList<T>> {

    //supplier - creates instance
    //accumulator - how to add one element
    //combiner - how to add left and right builders together
    //finisher - once data is added - how to assemble it together

    @Override
    public Supplier<ImmutableList.Builder<T>> supplier() {
        return () -> ImmutableList.builder();
    }

    @Override
    public BiConsumer<ImmutableList.Builder<T>, T> accumulator() {
        return (builder, t) -> builder.add(t);
    }

    @Override
    public BinaryOperator<ImmutableList.Builder<T>> combiner() {
        return (left, right) -> {
            left.addAll(right.build());
            return left;
        };
    }

    @Override
    public Function<ImmutableList.Builder<T>, ImmutableList<T>> finisher() {
        return ImmutableList.Builder::build;
    }

    @Override
    public Set<Collector.Characteristics> characteristics() {
        return EnumSet.of(Collector.Characteristics.UNORDERED);
    }

}