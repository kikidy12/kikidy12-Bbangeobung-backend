package com.example.bbangeobung.sse;

import org.springframework.context.ApplicationListener;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;


@Component
public class CommentAddedEventListener implements ApplicationListener<CommentAddedEvent>, Flow.Publisher<ServerSentEvent<String>> {
    private final List<Flow.Subscriber<? super ServerSentEvent<String>>> subscribers = new ArrayList<>();

    @Override
    public void onApplicationEvent(CommentAddedEvent event) {
        String data = String.format("New comment added to post %d with ID %d", event.getStoreId(), event.getCommentId());
        ServerSentEvent<String> sseData = ServerSentEvent.builder(data).build();
        subscribers.forEach(subscriber -> subscriber.onNext(sseData));
    }

    @Override
    public void subscribe(Flow.Subscriber<? super ServerSentEvent<String>> subscriber) {
        subscribers.add(subscriber);
        subscriber.onSubscribe(new Flow.Subscription() {
            @Override
            public void request(long n) {}

            @Override
            public void cancel() {
                subscribers.remove(subscriber);
            }
        });
    }
}

