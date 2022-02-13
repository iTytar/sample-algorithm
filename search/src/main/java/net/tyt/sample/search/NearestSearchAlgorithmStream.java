package net.tyt.sample.search;

import static java.lang.Math.abs;
import java.util.Arrays;

/**
 *
 * @author 69TytarIA
 */
public class NearestSearchAlgorithmStream implements NearestSearchAlgorithm {

    @Override
    public int search(final int[] array, final int val) {
        return Arrays.stream(array)
                .mapToObj(i -> new Tuple2(i,i-val))
                .sorted()
                .findFirst().orElseThrow().value;
    }
    
    class Tuple2 implements Comparable<Tuple2>{
        private final int value;
        private final int delta;
        
        public Tuple2(int value, int delta) {
            this.value = value;
            this.delta = delta;
        }

        @Override
        public int compareTo(Tuple2 t) {
            int c = abs(delta) - abs(t.delta);
            return c != 0 ? c : t.delta - delta;
        }
    }
}
