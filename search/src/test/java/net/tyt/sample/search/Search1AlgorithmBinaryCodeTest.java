package net.tyt.sample.search;

import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author 69TytarIA
 */
@DisplayName("A search binary algorithm test")
public class Search1AlgorithmBinaryCodeTest extends Search1AlgorithmTest {
    @Override
    protected Search1Algorithm newAlgorithm() {
        return new Search1AlgorithmBinaryCode();
    }
}
