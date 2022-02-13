package net.tyt.sample.search;

import java.util.Arrays;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author 69TytarIA
 */
public abstract class Search1AlgorithmTest {

    private final Search1Algorithm algorithm = newAlgorithm();

    /**
     * Test of search method, of instance Search1Algorithm.
     */
    @ParameterizedTest(name = "#{index} - Test with array: {0}")
    @MethodSource("arraysStream")
    public void testSearch(int[] array) {
        System.out.println("test search '" + algorithm.getClass().getName() +"' "+Arrays.toString(array) +"...");
        int v = 7;
        int expected = 5;
        int result = algorithm.search(array, v);
        assertEquals(expected, result);
    }
    
    static Stream<int[]> arraysStream() {
        return Stream.of(
                new int[]{1, 5, 3, 4, 2, 7, 8, 4, 9},
                new int[]{6, 8, 1, 9, 4, 7, 0, 2, 5}
        );
    } 

    abstract protected Search1Algorithm newAlgorithm();

}
