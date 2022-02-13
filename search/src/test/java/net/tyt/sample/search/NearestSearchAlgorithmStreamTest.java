package net.tyt.sample.search;

/**
 *
 * @author 69TytarIA
 */
public class NearestSearchAlgorithmStreamTest extends NearestSearchAlgorithmTest {
    @Override
    protected NearestSearchAlgorithm newAlgorithm() {
        return new NearestSearchAlgorithmStream();
    }
}
