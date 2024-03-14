package commands.implementations;

import events.Event;
import game.User;

public class EmailCommand {
    NotificationDetails details;
    String link;
    String templateId;
    String templateString;

    public EmailCommand(Event event) {
        this.details = new NotificationDetails(event.getUser(), event.getMessage());
        this.link = event.getLink();
    }

    public User getReceiver() {
        return details.getReceiver();
    }

    public String getMessage() {
        return details.getMessage();
    }
}
