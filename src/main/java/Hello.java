import java.util.function.Function;
import java.util.function.Predicate;

public class Hello {

    static Predicate<Person> isAdult() {
        return p -> p.getAge() > 18;
    }

    static void printWeight(double weightInKg, Function<Double, String> scale) {
        System.out.println("Weight: " + scale.apply(weightInKg));
    }

    public static final Function<Double, String> KG_TO_STONES = weight -> String.format("%f stones", weight * 0.157473);

    public static void main(String[] args) {
        Function<Integer, Integer> f = x -> x * 2;
    }

}
