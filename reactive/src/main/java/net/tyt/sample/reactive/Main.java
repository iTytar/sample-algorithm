package net.tyt.sample.reactive;

import reactor.core.publisher.Flux;

/**
 *
 * @author 69TytarIA
 */
public class Main {
    public static void main(String... args) {
        Flux.from(new ConsolePublisher()).toStream()
                .map(String::toUpperCase)
                .forEach(System.out::print);
    }
}
