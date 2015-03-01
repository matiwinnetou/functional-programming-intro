import java.util.function.BiFunction;
import java.util.function.Function;

public class Curry {

//  def curry[A,B,C](f: (A, B) => C): A => B => C = a => b => f(a, b)
    public static <A,B,C> Function<A, Function<B, C>> curry(final BiFunction<A,B,C> fun) {
        return a -> b -> fun.apply(a, b);
    }

//  def uncurry[A,B,C](f: A => B => C): (A, B) => C = (a, b) => f(a)(b)
    public static <A,B,C> BiFunction<A,B,C> uncurry(Function<A, Function<B, C>> fun) {
        return (a, b) -> fun.apply(a).apply(b);
    }

}
