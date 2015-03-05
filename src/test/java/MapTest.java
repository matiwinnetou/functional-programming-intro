import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class MapTest {

    @Test
    public void mapTest() {
        final List<Integer> ints = ImmutableList.of("1", "2", "3").stream()
                .map(str -> Integer.parseInt(str))
                .collect(Collectors.toList());

        Assert.assertEquals(ImmutableList.of(1, 2, 3), ints);
    }

    @Test
    public void flatMapTest() {
        final List<Integer> ints = ImmutableList.of("1", "2", "3").stream()
                .map(str -> Integer.parseInt(str))
                .collect(Collectors.toList());

        Assert.assertEquals(ImmutableList.of(1, 2, 3), ints);
    }

}
