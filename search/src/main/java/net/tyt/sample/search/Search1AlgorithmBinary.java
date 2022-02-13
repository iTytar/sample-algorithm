package net.tyt.sample.search;

import java.util.Arrays;

/**
 *
 * @author 69TytarIA
 */
public class Search1AlgorithmBinary implements Search1Algorithm {

    @Override
    public int search(final int[] array, final int v) {
        Tuple2[] ta = new Tuple2[array.length];
        for(int i=0; i<array.length; i++) {
            ta[i] = new Tuple2(i,array[i]);
        }
        Arrays.sort(ta);
        int i = Arrays.binarySearch(ta, new Tuple2(-1, v));
        if (i < 0) return -1;
        return ta[i].val1;
    }
    
    class Tuple2 implements Comparable<Tuple2> {
        final int val1;
        final int val2;
        
        public Tuple2(int val1, int val2) {
            this.val1 = val1;
            this.val2 = val2;
        }

        @Override
        public int compareTo(Tuple2 t) {
            return val2 - t.val2;
        }
        
        public String toString() {
            return "["+val1+","+val2+"]";
        }
    }
}
