 package net.tyt.sample.search;

import java.util.Arrays;

/**
 *
 * @author 69TytarIA
 */
public class Search1AlgorithmBinaryCode implements Search1Algorithm {

    @Override
    public int search(final int[] array, final int val) {
        Tuple2[] ta = new Tuple2[array.length];
        for(int i=0; i<array.length; i++) {
            ta[i] = new Tuple2(i,array[i]);
        }
        Arrays.sort(ta);
        int i = searchTuple2(ta, val, ta.length/2,0,ta.length-1);
        if (i < 0) return -1;
        return ta[i].val1;
    }

    private int searchTuple2(final Tuple2[] array, final int v, int i, int start, int end) {
//        System.out.println("searchTuple2 array="+Arrays.toString(array)+" v="+v+" i="+i+" start="+start+" end="+end);
        if (array[i].val2 == v) return i;
        if (start == end) return -1;
        if (array[i].val2 < v) start = (end - start) / 2;
        else end = (end - start) / 2;
        i = start + (end - start) / 2;
        return searchTuple2(array, v, i, start, end); 
    }

    private int searchInt(final int[] array, final int v, int i, int start, int end) {
//        System.out.println("searchInt array="+Arrays.toString(array)+" v="+v+" i="+i+" start="+start+" end="+end);
        if (array[i] == v) return i;
        if (start == end) return -1;
        if (array[i] < v) start = (end - start) / 2;
        else end = (end - start) / 2;
        i = start + (end - start) / 2;
        return searchInt(array, v, i, start, end); 
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
