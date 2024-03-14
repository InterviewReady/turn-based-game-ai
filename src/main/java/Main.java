import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import boards.Board;
import commands.implementations.EmailCommand;
import commands.implementations.SMSCommand;
import events.*;
import game.*;
import services.EmailService;
import services.SMSService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine = new AIEngine();
        RuleEngine ruleEngine = new RuleEngine();
        EmailService emailService = new EmailService();
        SMSService smsService = new SMSService();
        Board board = gameEngine.start("TicTacToe");
        EventBus eventBus = new EventBus();
        eventBus.subscribe(new Subscriber((event -> emailService.send(new EmailCommand(event)))));
        eventBus.subscribe(new Subscriber((event -> smsService.send(new SMSCommand(event)))));
        //make moves in a loop
        int row, col;
        Scanner scanner = new Scanner(System.in);
        GameFactory gameFactory = new GameFactory();
        Game game = gameFactory.createGame();
        Player computer = new Player("O"), human = new Player("X");

        if (human.getUser().activeAfter(10, TimeUnit.DAYS)) {
            eventBus.publish(new Event(human.getUser(), "Welcome Back!", "https://interviewready.io", "ACTIVITY"));
        }

        while (!ruleEngine.getState(board).isOver()) {
            System.out.println("Make your move!");
            System.out.println(board);
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move oppMove = new Move(Cell.getCell(row, col), human);
            gameEngine.move(board, oppMove);
            System.out.println(board);
            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
        }
        if (ruleEngine.getState(board).getWinner().equals(human.symbol())) {
            eventBus.publish(new Event(human.getUser(), "Congratulations!", "https://interviewready.io/learn", "WIN"));
        }
        System.out.println("GameResult: " + ruleEngine.getState(board));
        System.out.println(board);
    }
}
