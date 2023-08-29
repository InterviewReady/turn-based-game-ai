package events;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class EventBus {
    private List<Event> events = new ArrayList<>();
    private List<Subscriber> subscribers = new ArrayList<>();

    public void publish(Event event) {
        events.add(event);
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }
}

