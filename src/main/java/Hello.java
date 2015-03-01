import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class Hello {

    static Predicate<Person> isAdult() {
        return p -> p.getAge() > 18;
    }

    static void printWeight(double weightInKg, Function<Double, String> scale) {
        System.out.println("Weight: " + scale.apply(weightInKg));
    }

    private static ImmutableList<Person> people() {
        final Person p1 = new Person("Jan", Person.Gender.MALE, 172, 80, Optional.of(new Person.Address("Albert-Einstein", 1, 14345)));
        final Person p2 = new Person("Kathy", Person.Gender.FEMALE, 160, 16);

        return ImmutableList.of(p1, p2);
    }

    public static final Function<Double, String> KG_TO_STONES = weight -> String.format("%f stones", weight * 0.157473);

    public static void main(String[] args) {
    }

}
