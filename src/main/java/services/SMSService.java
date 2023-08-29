package services;

import commands.implementations.SMSCommand;
import game.User;

public class SMSService {
    private void sendSMS(User user, String message) {
        //todo: mail is sent here
    }

    public void send(SMSCommand command) {
        sendSMS(command.getDetails().getReceiver(), command.getDetails().getMessage());
    }
}
