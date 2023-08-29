package commands.builder;

import commands.implementations.SMSCommand;
import events.Event;
import game.User;

public class SMSCommandBuilder {
    NotificationBuilder notificationBuilder = new NotificationBuilder();
    String link;
    String templateId;
    String templateString;

    public SMSCommand build() {
        //todo: complex logic
        return new SMSCommand(notificationBuilder.build());
    }

    public SMSCommandBuilder user(User user) {
        notificationBuilder.user(user);
        return this;
    }

    public SMSCommandBuilder message(String message) {
        notificationBuilder.message(message);
        return this;
    }

    public SMSCommandBuilder event(Event event) {
        return this;
    }
}
