package monoid;

import java.util.function.BinaryOperator;

public class MultiplicationIntMonoid implements Monoid<Integer> {

    @Override
    public Integer zero() {
        return 1;
    }

    @Override
    public BinaryOperator<Integer> op() {
        return (prev, next) -> prev * next;
    }

}
