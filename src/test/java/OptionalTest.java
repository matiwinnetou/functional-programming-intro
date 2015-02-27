import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalTest {

    @Test
    public void testOptionalPresentAndMap() {
        final String string = "hello";

        final Optional<String> maybeString = Optional.of(string);

        final Optional<Integer> maybeInt = maybeString
                .filter(s -> s.length() > 0)
                .map(str -> str.length());

        maybeInt.ifPresent(i -> System.out.println(i));
        Assert.assertTrue(maybeInt.isPresent());
    }

    @Test
    public void testOptionalFlatMap() {
        final String string = "hello";

        final Optional<String> stringOpt = Optional.of(string).flatMap(str -> optStr());
        Assert.assertFalse(stringOpt.isPresent());
    }

    @Test
    public void testPerson_Address_AndFlatMap() {
        final Map<String, Person> people = people();

        final Optional<Integer> postCodeOpt = Optional.ofNullable(people.get("jan"))
                .flatMap(p -> p.getAddress())
                .map(address -> address.getPostCode());

        Assert.assertFalse(postCodeOpt.isPresent());
    }

    @Test
    public void testPerson_Address_AndFlatMap2() {
        final Map<String, Person> people = people();

        final Optional<Integer> postCodeOpt = Optional.ofNullable(people.get("Jan"))
                .flatMap(p -> p.getAddress())
                .map(address -> address.getPostCode());

        Assert.assertTrue(postCodeOpt.isPresent());
    }

    @Test
    //flatmap hell
    public void testOptionalZipWithFlatMap() {
        final Optional<String> nameOpt = Optional.ofNullable(null);
        final Optional<Integer> postCodeOpt = Optional.ofNullable(12167);

        final Optional<String> fullNameOpt = nameOpt
                .flatMap(name -> postCodeOpt
                        .map(postCode -> String.format("name:%s,postcode:%d", name, postCode)));

        Assert.assertFalse(fullNameOpt.isPresent());
    }

    @Test
    //flatmap hell
    public void testOptionalZipWithFlatMap2() {
        final Optional<String> nameOpt = Optional.ofNullable(null);
        final Optional<String> surnameOpt = Optional.ofNullable("Koziol");
        final Optional<Integer> postCodeOpt = Optional.ofNullable(12167);

        final Optional<String> fullNameOpt = nameOpt
                .flatMap(name -> surnameOpt.flatMap(surname -> postCodeOpt
                        .map(postCode -> String.format("name:%s,surname:%s,postcode:%d", name, surname, postCode))));

        Assert.assertFalse(fullNameOpt.isPresent());
    }

    @Test
    //flatmap hell
    public void testOptionalZip_WithPlainZip() {
        final Optional<String> nameOpt = Optional.ofNullable("Jan");
        final Optional<String> surnameOpt = Optional.ofNullable("Koziol");
        final Optional<Integer> postCodeOpt = Optional.ofNullable(12167);

        final Optional<String> fullNameOpt = MoreOptionals
                .zip(nameOpt, surnameOpt, postCodeOpt, (name, surname, postCode)
                        -> String.format("name:%s,surname:%s,postcode:%d", name, surname, postCode));

        Assert.assertTrue(fullNameOpt.isPresent());
    }

    @Test
    public void testOptionalZip_WithStream() {
        final Optional<String> nameOpt = Optional.ofNullable("Jan");
        final Optional<String> surnameOpt = Optional.ofNullable("Koziol");
        final Optional<String> postCodeOpt = Optional.ofNullable(12167).map(i -> i.toString());

        final Optional<String> fullNameOpt = MoreOptionals
                .zip(nameOpt, surnameOpt, postCodeOpt, (name, surname, postCode) -> Stream.of(name, surname, postCode)
                        .reduce("", (prev, next) -> prev + next));

        Assert.assertEquals("JanKoziol12167", fullNameOpt.orElse("?"));
    }

    @Test
    public void testOptional_Generic_Zip() {
        final Optional<String> nameOpt = Optional.ofNullable(null);
        final Optional<String> surnameOpt = Optional.ofNullable("Koziol");
        final Optional<String> postCodeOpt = Optional.ofNullable(12167).map(i -> i.toString());

        final Optional<String> fullNameOpt = MoreOptionals.sequence(nameOpt, surnameOpt, postCodeOpt)
                .map(list -> list.stream()
                        .collect(Collectors.joining(",")));


        Assert.assertEquals("Koziol,12167", fullNameOpt.orElse("?"));
    }

    @Test
    public void testOptionalZip_With_JoiningCollector() {
        final Optional<String> nameOpt = Optional.ofNullable("Jan");
        final Optional<String> surnameOpt = Optional.ofNullable("Koziol");
        final Optional<String> postCodeOpt = Optional.ofNullable(12167).map(i -> i.toString());

        final Optional<String> fullNameOpt = MoreOptionals
                .zip(nameOpt, surnameOpt, postCodeOpt, (name, surname, postCode) -> Stream.of(name, surname, postCode)
                        .collect(Collectors.joining(",")));

        Assert.assertTrue(fullNameOpt.isPresent());
        Assert.assertEquals("Jan,Koziol,12167", fullNameOpt.orElse("?"));
    }

    private Map<String, Person> people() {
        final Person p1 = new Person("Jan", Person.Gender.MALE, 172, 80, Optional.of(new Person.Address("Albert-Einstein", 1, 14345)));
        final Person p2 = new Person("Kathy", Person.Gender.FEMALE, 160, 16, Optional.empty());

        return Stream.of(p1, p2).collect(Collectors.toMap(p -> p.getName(), p -> p));
    }


//    public static <T1, T2, R> Optional<R> zip(final Optional<? extends T1> a,
//                                              final Optional<? extends T2> b,
//                                              final BiFunction<? super T1, ? super T2, ? extends R> zipFunction) {
//        return a.flatMap(aa -> b.map(bb -> zipFunction.apply(aa, bb)));
//    }

    private Optional<String> optStr() {
        return Optional.ofNullable(null);
    }

}
