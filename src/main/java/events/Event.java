package events;

import game.User;

public class Event {
    private User user;
    private String message;
    private String link;

    private String type;

    public Event(User user, String message, String link, String type) {
        this.user = user;
        this.message = message;
        this.link = link;
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public String getLink() {
        return link;
    }

    public String getType() {
        return type;
    }
}
