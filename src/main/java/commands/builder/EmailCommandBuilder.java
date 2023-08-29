package commands.builder;

import commands.implementations.EmailCommand;
import events.Event;
import game.User;

public class EmailCommandBuilder {
    NotificationBuilder notificationBuilder;
    String link;
    String templateId;
    String templateString;

    public EmailCommandBuilder link(String link) {
        this.link = link;
        return this;
    }

    public EmailCommand build() {
        //todo: complex logic
        return new EmailCommand(notificationBuilder.build(), link);
    }

    public EmailCommandBuilder user(User user) {
        notificationBuilder.user(user);
        return this;
    }

    public EmailCommandBuilder message(String message) {
        notificationBuilder.message(message);
        return this;
    }

    public EmailCommandBuilder event(Event event) {
        return this;
    }
}
