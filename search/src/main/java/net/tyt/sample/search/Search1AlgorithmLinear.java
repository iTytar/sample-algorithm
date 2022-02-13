package net.tyt.sample.search;

/**
 *
 * @author 69TytarIA
 */
public class Search1AlgorithmLinear implements Search1Algorithm {

    @Override
    public int search(final int[] array, final int v) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == v) {
                return i;
            }
        }
        return -1;
    }

}
