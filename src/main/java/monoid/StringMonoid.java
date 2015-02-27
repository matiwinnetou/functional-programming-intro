package monoid;

import java.util.function.BinaryOperator;

public class StringMonoid implements Monoid<String> {

    public static final StringMonoid INSTANCE = new StringMonoid();
    
    @Override
    public String zero() {
        return "";
    }

    @Override
    public BinaryOperator<String> operation() {
        return (prev, next) -> prev + next;
    }

}
