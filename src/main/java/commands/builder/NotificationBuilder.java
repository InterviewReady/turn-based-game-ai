package commands.builder;

import commands.implementations.NotificationDetails;
import game.User;

public class NotificationBuilder {
    User user;
    String message;

    public NotificationBuilder user(User user) {
        this.user = user;
        return this;
    }

    public NotificationBuilder message(String message) {
        this.message = message;
        return this;
    }

    public NotificationDetails build() {
        return new NotificationDetails(user, message);
    }
}
