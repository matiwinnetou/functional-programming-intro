package monoid;

import java.util.function.BinaryOperator;

public class AdditionIntMonoid implements Monoid<Integer> {

    @Override
    public Integer zero() {
        return 0;
    }

    @Override
    public BinaryOperator<Integer> op() {
        return (prev, next) -> prev + next;
    }

}
