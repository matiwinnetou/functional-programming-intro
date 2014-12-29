import com.google.common.collect.ImmutableList;
import monoid.Monoid;
import org.junit.Test;

import java.util.Optional;
import java.util.OptionalDouble;

import static org.junit.Assert.assertEquals;

public class Stream2Test {

    @Test
    public void average() {
        final OptionalDouble average = people().stream()
                .filter(p -> p.getGender() == Person.MALE)
                .mapToDouble(p -> p.getHeight())
                .average();

        assertEquals(175.0D, average.getAsDouble(), 0.1D);
    }

    @Test
    public void testStringMonoid() {
        final String reduced = people().stream()
                .map(p -> p.getName())
                .reduce(Monoid.STRING_MONOID.zero(), Monoid.STRING_MONOID.op());

        assertEquals("PatrickStefanJulia", reduced);
    }

    @Test
    public void testAdditionMonoid() {
        final int reduced = people().stream()
                .map(p -> p.getAge())
                .reduce(Monoid.ADDITION_INT_MONOID.zero(), Monoid.ADDITION_INT_MONOID.op());

        assertEquals(90, reduced);
    }

    @Test
    public void testMultiplicationMonoid() {
        final int reduced = people().stream()
                .map(p -> p.getAge())
                .reduce(Monoid.MULTIPLICATION_INT_MONOID.zero(), Monoid.MULTIPLICATION_INT_MONOID.op());

        assertEquals(26250, reduced);
    }

    private ImmutableList<Person> people() {
        return ImmutableList.of(
                new Person("Patrick", 'M', 170, 30, Optional.empty()),
                new Person("Stefan", 'M', 180, 35, Optional.empty()),
                new Person("Julia", 'F', 161, 25, Optional.empty()));
    }

}
