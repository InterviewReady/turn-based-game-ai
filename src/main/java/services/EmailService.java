package services;

import commands.implementations.EmailCommand;
import game.User;

public class EmailService {
    private void sendEmail(User user, String message) {
        //todo: mail is sent here
    }

    public void send(EmailCommand command) {
        sendEmail(command.getReceiver(), command.getMessage());
    }
}
