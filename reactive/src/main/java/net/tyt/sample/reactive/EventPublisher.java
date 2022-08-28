package net.tyt.sample.reactive;

import java.util.Optional;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/**
 *
 * @author Igor Tytar <tytar.mail.ru>
 * @param <T>
 */
public class EventPublisher<T> implements Publisher<T> {
    private volatile Subscriber<? super T> subscriber;

    @Override
    public void subscribe(Subscriber<? super T> subscriber) {
        System.out.println("subscribe("+subscriber+")...");
        this.subscriber = subscriber;
    }

    public Optional<Subscriber<? super T>> getSubscriber() {
        return Optional.ofNullable(subscriber);
    }
    
    
}
