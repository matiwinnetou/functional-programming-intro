import java.util.function.Function;

public interface MapFunctor<A, B> {

    B map(A a, Function<A,B> func);

}