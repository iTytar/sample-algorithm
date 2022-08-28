package net.tyt.sample.reactive;

import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

/**
 *
 * @author 69TytarIA
 */
public class ProduserTest {

    /**
     * Test of search method, of instance Search1Algorithm.
     */
    @ParameterizedTest(name = "#{index} - Test with array: {0}")
    @MethodSource("strings")
    public void testEventProduser(List<String> strings) {
        System.out.println("test EventProduser "+strings+"...");
        List<String> expected = strings.stream()
                .map(s -> s.toUpperCase())
                .toList();
        EventProduser produser = new EventProduser(strings);
        Flux<String> flux = Flux.create(produser::consume);
        List<String> result = flux.take(strings.size())
                .toStream()
                .map(s -> s.toUpperCase())
                .toList();
        assertLinesMatch(expected, result);
    }

    /**
     * Test of search method, of instance Search1Algorithm.
     */
    @ParameterizedTest(name = "#{index} - Test with array: {0}")
    @MethodSource("strings")
    public void testEventPublisher(List<String> strings) {
        System.out.println("test EventPublisher "+strings+"...");
        List<String> expected = strings.stream()
                .map(String::toUpperCase)
                .toList();
        EventPublisher<String> eventPublisher = new EventPublisher();
        (new EventProduser2(strings, eventPublisher)).start();
        List<String> result = Flux.from(eventPublisher)
                .toStream()
                .map(String::toUpperCase)
                .toList();
        assertLinesMatch(expected, result);
    }

    public static Stream<List<String>> strings() {
        return Stream.of(
                List.of("aaa", "bbbb", "cccccc", "123"),
                List.of("BBB", "AAAAAA", "cccccc", "123as", "qwretqwertSDFGERW123")
        );
    }

    private class EventProduser<T> implements Runnable {

        private final List<T> events;
        private Consumer<T> eventConsumer;

        public EventProduser(List<T> events) {
            this.events = events;
        }

        public void consume(final FluxSink fs) {
            eventConsumer = e -> fs.next(e);
            Thread t = new Thread(this, "produserThread");
            t.start();
        }

        @Override
        public void run() {
            events.forEach(eventConsumer);
        }

    }

    private class EventProduser2<T> implements Runnable {

        private final List<T> events;
        private final EventPublisher<T> eventConsumer;

        public EventProduser2(List<T> events, EventPublisher<T> eventConsumer) {
            this.events = events;
            this.eventConsumer = eventConsumer;
        }

        public void start() {
            Thread t = new Thread(this, "produserThread");
            t.start();
        }

        @Override
        public void run() {
            while(eventConsumer.getSubscriber().isEmpty()) {
                try {
                    System.out.println("waiting...");
                    Thread.sleep(500L);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            Subscriber sbs = eventConsumer.getSubscriber().orElseThrow();
            events.forEach(e -> sbs.onNext(e));
            sbs.onComplete();
        }

    }

}
