package commands.implementations;

import events.Event;
import game.User;

public class SMSCommand {
    NotificationDetails details;
    String link;
    String templateId;
    String templateString;

    public SMSCommand(Event event) {
        this.details = new NotificationDetails(event.getUser(),event.getMessage());
    }

    public String getMessage() {
        return details.getMessage();
    }

    public User getReceiver() {
        return details.getReceiver();
    }
}
