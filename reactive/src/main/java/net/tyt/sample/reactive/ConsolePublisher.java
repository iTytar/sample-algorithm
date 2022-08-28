package net.tyt.sample.reactive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/**
 *
 * @author Igor Tytar <tytar.mail.ru>
 */
public class ConsolePublisher implements Publisher<String> {

    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        ConsoleReader cr = new ConsoleReader(subscriber);
        cr.start();
    }

    private class ConsoleReader implements Runnable {

        private final Subscriber<? super String> subscriber;

        public ConsoleReader(Subscriber<? super String> subscriber) {
            this.subscriber = subscriber;
        }

        public void start() {
            Thread t = new Thread(this);
            t.start();
        }

        @Override
        public void run() {
            try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.print(">");
                String line = buffReader.readLine();
                while (!"exit".equalsIgnoreCase(line)) {
                    subscriber.onNext(line);
                    System.out.print(">");
                    line = buffReader.readLine();
                }
                subscriber.onComplete();
            } catch (Throwable t) {
                subscriber.onError(t);
            }
        }
    }
}
