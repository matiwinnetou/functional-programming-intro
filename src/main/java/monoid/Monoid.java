package monoid;

import java.util.function.BinaryOperator;

public interface Monoid<T> {

    StringMonoid STRING_MONOID = new StringMonoid();

    AdditionIntMonoid ADDITION_INT_MONOID = new AdditionIntMonoid();

    MultiplicationIntMonoid MULTIPLICATION_INT_MONOID = new MultiplicationIntMonoid();

    T zero();

    BinaryOperator<T> operation();

}
