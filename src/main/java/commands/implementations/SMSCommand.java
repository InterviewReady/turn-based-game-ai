package commands.implementations;

import game.User;

public class SMSCommand {
    NotificationDetails details;
    String link;
    String templateId;
    String templateString;

    public SMSCommand(NotificationDetails details) {
        this.details = details;
    }

    public NotificationDetails getDetails() {
        return details;
    }
}
