import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import monoid.Monoid;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;
import static org.junit.Assert.assertEquals;

public class Stream2Test {

    @Test
    public void average() {
        final OptionalDouble average = people().stream()
                .filter(p -> p.getGender() == Person.Gender.MALE)
                .mapToDouble(p -> p.getHeight())
                .average();

        assertEquals(175.0D, average.getAsDouble(), 0.1D);
    }

    @Test
    public void testStringMonoid() {
        final String reduced = people().stream()
                .map(p -> p.getName())
                .reduce(Monoid.STRING_MONOID.zero(), Monoid.STRING_MONOID.operation());

        assertEquals("PatrickStefanJulia", reduced);
    }

    @Test
    public void testAdditionMonoid() {
        final int reduced = reduceAge(people(), Monoid.ADDITION_INT_MONOID);

        assertEquals(72, reduced);
    }

    @Test
    public void testMultiplicationMonoid() {
        final int reduced = reduceAge(people(), Monoid.MULTIPLICATION_INT_MONOID);

        assertEquals(12750, reduced);
    }

    @Test
    public void testGrouping() {
        final Map<Person.Gender, List<Person>> peopleByGender = people().stream().collect(Collectors.groupingBy(t -> t.getGender()));

        final ImmutableMap<Person.Gender, ImmutableList<Object>> expectedPeopleByGender = ImmutableMap.of(
                Person.Gender.MALE, ImmutableList.of(patrick(), stefan()),
                Person.Gender.FEMALE, ImmutableList.of(julia())
        );

        assertEquals(expectedPeopleByGender, peopleByGender);
    }

    @Test
    public void testPartitioning() {
        final Map<Boolean, List<Person>> peopleByMaturity = people().stream().collect(Collectors.partitioningBy(p -> p.getAge() >==] 18));

        final ImmutableMap<Boolean, ImmutableList<Object>> expectedPeopleByGender = ImmutableMap.of(
                Boolean.TRUE, ImmutableList.of(patrick(), julia()),
                Boolean.FALSE, ImmutableList.of(stefan())
        );

        assertEquals(expectedPeopleByGender, peopleByMaturity);
    }

    @Test
    public void testIntStreamRange() {
        IntStream.range(0, 100).forEach(System.out::println);
    }

    private Integer reduceAge(final ImmutableList<Person> people, final Monoid<Integer> monoid) {
        return fold(people, p -> p.getAge(), monoid);
    }

    //polymorphic java fold with a monoid
    private <T> T fold(final ImmutableList<Person> people, final Function<Person, T> mapping, final Monoid<T> monoid) {
        return people.stream().map(mapping).reduce(monoid.zero(), monoid.operation());
    }

    private ImmutableList<Person> people() {
        return ImmutableList.of(
                patrick(),
                stefan(),
                julia()
        );
    }

    private Person patrick() {
        return new Person("Patrick", Person.Gender.MALE, 170, 30, Optional.empty());
    }

    private Person stefan() {
        return new Person("Stefan", Person.Gender.MALE, 180, 17, Optional.empty());
    }

    private Person julia() {
        return new Person("Julia", Person.Gender.FEMALE, 161, 25, Optional.empty());
    }

}
