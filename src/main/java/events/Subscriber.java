package events;

import java.util.function.Consumer;

public class Subscriber {
    private final Consumer<Event> consumer;

    public Subscriber(Consumer<Event> consumer) {
        this.consumer = consumer;
    }
}
