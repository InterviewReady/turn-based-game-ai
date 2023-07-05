import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import boards.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine = new AIEngine();
        RuleEngine ruleEngine = new RuleEngine();
        Board board = gameEngine.start("TicTacToe");
        //make moves in a loop
        int row, col;
        Scanner scanner = new Scanner(System.in);
        while (!ruleEngine.getState(board).isOver()) {
            Player computer = new Player("O"), human = new Player("X");
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
        System.out.println("GameResult: " + ruleEngine.getState(board));
        System.out.println(board);
    }
}
