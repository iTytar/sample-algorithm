package net.tyt.sample.search;

/**
 *
 * @author 69TytarIA
 */
public class NearestSearchAlgorithmLinearTest extends NearestSearchAlgorithmTest {
    @Override
    protected NearestSearchAlgorithm newAlgorithm() {
        return new NearestSearchAlgorithmLinear();
    }
}
