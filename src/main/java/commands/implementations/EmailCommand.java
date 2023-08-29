package commands.implementations;

import game.User;

public class EmailCommand {
    NotificationDetails details;
    String link;
    String templateId;
    String templateString;

    public EmailCommand(NotificationDetails details, String link) {
        this.details = details;
        this.link = link;
    }

    public NotificationDetails getDetails() {
        return details;
    }
}
