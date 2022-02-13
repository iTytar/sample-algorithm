package net.tyt.sample.search;

/**
 *
 * @author 69TytarIA
 */
public class NearestSearchAlgorithmLinear implements NearestSearchAlgorithm {

    @Override
    public int search(final int[] array, final int val) {
        int v = array[0];
        int delta = Math.abs(val-v);
        for (int i = 0; i < array.length; i++) {
            if (array[i] == val) {
                return array[i];
            }
            int d = Math.abs(val - array[i]);
            if (delta > d || (delta == d && val < array[i])) {
                delta = d;
                v = array[i];
            }
        }
        return v;
    }
}
