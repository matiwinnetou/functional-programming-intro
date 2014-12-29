package monoid;

import java.util.function.BinaryOperator;

public class StringMonoid implements Monoid<String> {

    @Override
    public String zero() {
        return "";
    }

    @Override
    public BinaryOperator<String> op() {
        return (prev,next) -> prev + next;
    }

}
